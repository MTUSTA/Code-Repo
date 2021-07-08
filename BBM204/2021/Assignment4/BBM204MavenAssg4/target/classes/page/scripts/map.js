

 $(function() {
    'use strict';
    
    const $body = $('#mapbody');
    const $routeStatus = $('#status-route');
    const $loadingStatus = $('#status-loading');
    const $errorStatus = $('#status-error');
    const $directionsText = $('#directions-text');
    const themeableElements = ['body', '.actions', '.card', '.search', '.ui-autocomplete',
                                '.status', '.settings', '.clear', '.action-icon'];
    var params = {
        ullat: 37.877357461061074,
        ullon: -122.27492774092633,
        lrlat: 37.83,
        lrlon: -122.22,
        w: $body.width(),
        h: $body.height()
    };
    const SAFE_WIDTH = 1120;
    const SAFE_HEIGHT = 800;

    var getInProgress = false;
    var route_params = {};
    var map;
    var dest;
    var tx = 0, ty = 0;
    var rtx, rty;
    var markers = [];
    var host;
    var ullon_bound, ullat_bound, lrlon_bound, lrlat_bound;
    var img_w, img_h;
    var constrain, theme;

    
    const zoom_delta = 0.04;
    const base_move_delta = 0.03;
    const max_level = 7;
    const min_level = 2;
    var wdpp = 0.00004291534423828125;
    var hdpp = 0.00003388335630702399;
    var current_level = 0;

    
    if (document.location.hostname !== 'localhost') {
        host = 'http://' + document.location.host;
    } else {
        host = 'http://localhost:4567';
    }
    const raster_server = host + '/raster';
    const route_server = host + '/route';
    const stop_server = host + '/stop';
    const clear_route = host + '/clear_route';
    const search = host + '/search';

    
    
    function real_lrlat() { return params.ullat - hdpp * params.h; }

    function real_lrlon() { return params.ullon + wdpp * params.w; }

    function shiftLeft(delta) {
        params.ullon -= delta;
        params.lrlon -= delta;
        tx -= delta * (1 / wdpp);
    }
    function shiftUp(delta) {
        params.ullat += delta;
        params.lrlat += delta;
        ty += delta * (1 / hdpp);
    }
    function shiftRight(delta) {
        params.ullon += delta;
        params.lrlon += delta;
        tx += delta * (1 / wdpp);
    }
    function shiftDown(delta) {
        params.ullat -= delta;
        params.lrlat -= delta;
        ty -= delta * (1 / hdpp);
    }

    function removeMarkers() {
        for (var i = 0; i < markers.length; i++) {
            markers[i].element.remove();
        }
        markers = [];
    }

    function updateMarkers() {
        for (var i = 0; i < markers.length; i++) {
            const marker = markers[i];
            marker.tx = (marker.lon - params.ullon) * (1 / wdpp) - 7 - tx;
            marker.ty = - (marker.lat - params.ullat) * (1 / hdpp) - 7 - ty;
        }
    }

    function updateImg(successCallback) {
        
        $loadingStatus.show();
        getInProgress = true;
        $.get({
            async: true,
            url: raster_server,
            data: params,
            success: function(data) {
                console.log(data);
                if (data.query_success) {
                    $loadingStatus.hide();
                    map.src = 'data:image/png;base64,' + data.b64_encoded_image_data;
                    console.log('Updating map with image length: ' +
                                data.b64_encoded_image_data.length);
                    ullon_bound = data.raster_ul_lon;
                    ullat_bound = data.raster_ul_lat;
                    lrlon_bound = data.raster_lr_lon;
                    lrlat_bound = data.raster_lr_lat;
                    current_level = data.depth;
                    img_w = data.raster_width;
                    img_h = data.raster_height;
                    wdpp = (lrlon_bound - ullon_bound) / img_w;
                    hdpp = (ullat_bound - lrlat_bound) / img_h;

                    tx = - (params.ullon - ullon_bound) * (1 / wdpp);
                    ty = (params.ullat - ullat_bound) * (1 / hdpp);
                    rtx = (route_params.end_lon - params.ullon) * (1 / wdpp) - dest.width / 2 - tx;
                    rty = - (route_params.end_lat - params.ullat) * (1 / hdpp) - dest.height - ty;
                    updateMarkers();
                    getInProgress = false;
                    if (successCallback) {
                        successCallback();
                    }
                } else {
                    $loadingstatus.hide();
                }
            },
            error: function() {
                getInProgress = false;
                $errorStatus.show();
                setTimeout(function() {
                    $errorStatus.fadeOut();
                }, 4000);
            },
            dataType: 'json'
        });
    }

    function updateT() {
        map.style.transform = 'translateX(' + tx + 'px) translateY(' + ty + 'px)';
        dest.style.transform = 'translateX(' + (tx+rtx) + 'px) translateY(' + (ty+rty) + 'px)';
        for (var i = 0; i < markers.length; i++) {
            const marker = markers[i];
            marker.element.css('transform', 'translateX(' + (tx+marker.tx) + 'px) translateY(' +
            (ty + marker.ty) + 'px)');
        }

        return params.ullon < ullon_bound || params.ullat > ullat_bound ||
            params.lrlon > lrlon_bound || params.lrlat < lrlat_bound;
    }

    function updateRoute(addStop=false) {
        var endpointUrl, data;

        if (addStop) {
            endpointUrl = stop_server;
            const offset = $body.offset();
            data = {};
            data.lon = params.ullon + (mousePosition.x - offset.left) * wdpp;
            data.lat = params.ullat - (mousePosition.y - offset.top) * hdpp;
        } else {
            endpointUrl = route_server;
            data = route_params;
        }
        $.get({
            async: true,
            url: endpointUrl,
            data: data,
            success: function(data) {
                data = JSON.parse(data);
                updateImg();
                if (data.directions_success) {
                    $directionsText.html(data.directions);
                } else {
                    $directionsText.html('No routing directions to display.');
                }
            },
        });
    }

    
    function update(callback) {
        if (callback) {
            updateImg(callback);
        } else {
            updateImg(updateT);
        }
    }

    function conditionalUpdate() {
        if (updateT()) {
            console.log('Update required.');
            update();
        }
    }

    function zoom(direction, level) {
        const starting_level = current_level;

        const window_ratio = params.w / params.h;

        var delta = direction * zoom_delta / (Math.pow(2, level));


        var i = 0;
        var zoomCallback = function() {
            updateT();
            if (!(i < 3 && starting_level === current_level)) {
                params.lrlon = real_lrlon();
                params.lrlat = real_lrlat();
                return;
            }
            params.ullat -= delta;
            params.ullon += delta * window_ratio;
            params.lrlat += delta;
            params.lrlon -= delta * window_ratio;
            update(zoomCallback);

            delta /= 2;
            i++;
        };
        if (!getInProgress) {
            zoomCallback();
        }
    }

    function zoomIn() {
        if (current_level === max_level) {
            return;
        }
        zoom(1, current_level);
    }

    function zoomOut() {
        if (current_level === min_level) {
            return;
        }
        zoom(-1, current_level - 1);
    }

    function handleDimensionChange() {
        params.w = $body.width();
        params.h = $body.height();
        params.lrlon = params.ullon + wdpp * params.w;
        params.lrlat = params.ullat - hdpp * params.h;
    }

    function updateConstrain() {
        if (constrain) {
            $('#mapbody').css({
                'max-height': SAFE_HEIGHT,
                'max-width': SAFE_WIDTH
            });
        } else {
            $('#mapbody').css({
                'max-height': '',
                'max-width': ''
            });
        }
        handleDimensionChange();
    }

    
    function loadCookies() {
        const allcookies = document.cookie.replace(/ /g, '').split(';');
        var foundConstrain = false;
        var foundTheme = false;
        for (var i = 0; i < allcookies.length; i++) {
            const kv = allcookies[i].split('=');
            if (kv[0] === 'constrain') {
                constrain = (kv[1] === 'true');
                foundConstrain = true;
                if (constrain === true) {
                    updateConstrain();
                }
            } else if (kv[0] === 'theme') {
                theme = kv[1];
                foundTheme = true;
            }
        }
        if (!foundConstrain) {
            document.cookie = 'constrain=false';
            constrain = false;
        }
        if (!foundTheme) {
            document.cookie = 'theme=default';
            theme = 'default';
        }
        const date = new Date();

        date.setTime(date.getTime() + 604800000);
        document.cookie = 'expires='+date.toGMTString();
    }

    function setTheme() {
        themeableElements.forEach(function(elem) {
            $(elem).removeClass('cal');
            $(elem).removeClass('solarized');
            $(elem).removeClass('eighties');
            $(elem).addClass(theme);
        });
    }

    function setCookie(key, value) {
        document.cookie = key + '=' + value.toString();
    }

    

    map = document.getElementById('map');
    dest = document.getElementById('dest');
    dest.style.visibility = 'hidden';
    params.lrlon = real_lrlon();
    params.lrlat = real_lrlat();
    loadCookies();
    setTheme();
    update();
    
    $('body').css('overflow', 'hidden');

    
    $('#tags').autocomplete({
          source: search,
          minLength: 2,
          select: function (event, ui) {
              $.get({
                  async: true,
                  url: search,
                  dataType: 'json',
                  data: { term: ui.item.value, full: true},
                  success: function(data) {
                      removeMarkers();
                      for (var i = 0; i < data.length; i++) {
                          console.log(data[i]);
                          const ele = $('<img/>', {
                              id: data[i].id,
                              src: 'round_marker.gif',
                              class: 'rmarker'
                          });
                          ele.appendTo($('#markers'));
                          markers.push({lat: data[i].lat, lon: data[i].lon,
                                        tx: 0, ty: 0, element: ele});
                      }
                      update();
                  },
              });
          }
    });
    setTheme();

    

    $('.ui-autocomplete').mouseenter(function() {
        $('.actions').addClass('active');
    }).mouseleave(function() {
        $('.actions').removeClass('active');
    });

    
    $body.on('mousedown', function(event) {
      var startX = event.pageX;
      var startY = event.pageY;
      var tmpX = startX;
      var tmpY = startY;
      var moved = false;

      $body.on('mousemove', function(event) {
        const dx = event.pageX - tmpX;
        const dy = event.pageY - tmpY;
        tx += dx;
        ty += dy;
        tmpX = event.pageX;
        tmpY = event.pageY;
        moved = true;
        updateT();
      });

      $body.on('mouseup', function(event) {
        $body.off('mousemove');
        $body.off('mouseup');
        if (moved) {
            const dx = event.pageX - startX;
            const dy = event.pageY - startY;
            params.ullon -= dx * wdpp;
            params.lrlon -= dx * wdpp;
            params.ullat += dy * hdpp;
            params.lrlat += dy * hdpp;
            conditionalUpdate();
        }
      });
    });

    $('.zoomin').click(function() {
       zoomIn();
    });

    $('.zoomout').click(function() {
       zoomOut();
    });

    $('.clear').click(function() {
        $.get({
            async: true,
            url: clear_route,
            success: function() {
                dest.style.visibility = 'hidden';
                $directionsText.html('No routing directions to display.');
                update();
            },
        });
    });

    $('.info').click(function() {
        $(this).toggleClass('active');
        $('.settings-container').removeClass('active');
    });
    $('.fa-cog').click(function() {
        $('.settings-container').addClass('active');
        if (constrain) {
            $('#constrain-input').prop('checked', true);
        }
        $('input[name=theme][value=' + theme + ']').prop('checked', true);
        $('.info').removeClass('active');
    });
    $('.close-settings').click(function() {
        $('.settings-container').removeClass('active');
    });

    $('.fa-map-signs').click(function() {
        $('.directions-container').addClass('active');
    });
    $('.close-directions').click(function() {
        $('.directions-container').removeClass('active');
    });


     var mousePosition = {x:0, y:0};
     $(document).bind('mousemove',function(mouseMoveEvent){
         mousePosition.x = mouseMoveEvent.pageX;
         mousePosition.y = mouseMoveEvent.pageY;
     });

    function addStop() {
        updateRoute(true);
        dest.style.visibility = 'visible';
        update();
    }

    $('body').dblclick(function handler(event) {
        if (route_params.start_lon && route_params.end_lon) {
            route_params = {};
        }
        const offset = $body.offset();
        if (route_params.start_lon) {
            route_params.end_lon = params.ullon + (event.pageX - offset.left) * wdpp;
            route_params.end_lat = params.ullat - (event.pageY - offset.top) * hdpp;
            $routeStatus.hide();
            updateRoute();
            dest.style.visibility = 'visible';
            update();
        } else {
            route_params.start_lon = params.ullon + (event.pageX - offset.left) * wdpp;
            route_params.start_lat = params.ullat - (event.pageY - offset.top) * hdpp;
            $routeStatus.show();
        }
    });


    
    $(window).bind('mousewheel DOMMouseScroll', function(event){
        if (event.originalEvent.wheelDelta > 0 || event.originalEvent.detail < 0) {
            zoomIn();
        } else {
            zoomOut();
        }
    });

    
    $('img').on('dragstart', function(event) { event.preventDefault(); });


    window.onresize = function() {
        handleDimensionChange();
        update();
    };

    $('#constrain-input').change(function() {
        constrain = $(this).is(':checked');
        updateConstrain();
        setCookie('constrain', constrain);
        update();
    });

    $('input[type=radio][name=theme]').change(function() {
        theme = this.value;
        setCookie('theme', this.value);
        setTheme();
    });

    
    document.onkeydown = function(e) {
        var delta = base_move_delta / (Math.pow(2, current_level));
        switch (e.keyCode) {
            case 37:
                shiftLeft(delta);
                update();
                break;
            case 38:
                shiftUp(delta);
                update();
                break;
            case 39:
                shiftRight(delta);
                update();
                break;
            case 40:
                shiftDown(delta);
                update();
                break;
            case 189:
                zoomOut();
                break;
            case 187:
                zoomIn();
                break;
            case 83:
                addStop();
                break;
        }
    };
});
