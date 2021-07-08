import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.stream.Collectors;

import com.google.gson.Gson;

import static spark.Spark.*;


public class MapServer {

    public static final double ROOT_ULLAT = 37.892195547244356, ROOT_ULLON = -122.2998046875,
            ROOT_LRLAT = 37.82280243352756, ROOT_LRLON = -122.2119140625;

    public static final int TILE_SIZE = 256;

    private static final int HALT_RESPONSE = 403;

    public static final float ROUTE_STROKE_WIDTH_PX = 5.0f;

    public static final Color ROUTE_STROKE_COLOR = new Color(108, 181, 230, 200);

    private static final String IMG_ROOT = "img/";

    private static final String OSM_DB_PATH = "berkeley.osm";

    private static final String[] REQUIRED_RASTER_REQUEST_PARAMS = {"ullat", "ullon", "lrlat",
            "lrlon", "w", "h"};

    private static final String[] REQUIRED_ROUTE_REQUEST_PARAMS = {"start_lat", "start_lon",
            "end_lat", "end_lon"};

    private static final String[] REQUIRED_STOP_REQUEST_PARAMS = {"lat", "lon"};

    private static final String[] REQUIRED_RASTER_RESULT_PARAMS = {"render_grid", "raster_ul_lon",
            "raster_ul_lat", "raster_lr_lon", "raster_lr_lat", "depth", "query_success"};

    private static Rasterer rasterer;
    private static GraphDB graph;
    private static LinkedList<Long> route = new LinkedList<>();


    public static void initialize() {
        graph = new GraphDB(OSM_DB_PATH);
        rasterer = new Rasterer(IMG_ROOT);
    }

    public static void main(String[] args) {
        initialize();
        staticFileLocation("/page");

        options("/*",
                (request, response) -> {

                    String accessControlRequestHeaders = request
                            .headers("Access-Control-Request-Headers");
                    if (accessControlRequestHeaders != null) {
                        response.header("Access-Control-Allow-Headers",
                                accessControlRequestHeaders);
                    }

                    String accessControlRequestMethod = request
                            .headers("Access-Control-Request-Method");
                    if (accessControlRequestMethod != null) {
                        response.header("Access-Control-Allow-Methods",
                                accessControlRequestMethod);
                    }

                    return "OK";
                });

        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));


        get("/raster", (req, res) -> {
            HashMap<String, Double> params =
                    getRequestParams(req, REQUIRED_RASTER_REQUEST_PARAMS);

            ByteArrayOutputStream os = new ByteArrayOutputStream();

            Map<String, Object> rasteredImgParams = rasterer.getMapRaster(params);

            boolean rasterSuccess = validateRasteredImgParams(rasteredImgParams);

            if (rasterSuccess) {
                writeImagesToOutputStream(rasteredImgParams, os);
                String encodedImage = Base64.getEncoder().encodeToString(os.toByteArray());
                rasteredImgParams.put("b64_encoded_image_data", encodedImage);
            }


            Gson gson = new Gson();
            return gson.toJson(rasteredImgParams);
        });


        get("/route", (req, res) -> {
            HashMap<String, Double> params =
                    getRequestParams(req, REQUIRED_ROUTE_REQUEST_PARAMS);
            route = Router.shortestPath(graph, params.get("start_lon"), params.get("start_lat"),
                    params.get("end_lon"), params.get("end_lat"));
            Map<String, Object> response = new HashMap<>();
            response.put("directions_success", true);
            response.put("directions", route);
            Gson gson = new Gson();
            return gson.toJson(response);
        });

        get("/stop", (req, res) -> {
            Gson gson = new Gson();
            HashMap<String, Double> params =
                    getRequestParams(req, REQUIRED_STOP_REQUEST_PARAMS);
            route = Router.addStop(graph, params.get("lat"), params.get("lon"));
            Map<String, Object> response = new HashMap<>();
            response.put("directions_success", true);
            response.put("directions", route);
            return gson.toJson(response);
        });


        get("/clear_route", (req, res) -> {
            clearRoute();
            return true;
        });


        get("/search", (req, res) -> {
            Set<String> reqParams = req.queryParams();
            String term = req.queryParams("term");
            Gson gson = new Gson();

            if (reqParams.contains("full")) {
                List<Map<String, Object>> data = getLocations(term);
                return gson.toJson(data);
            } else {

                List<String> matches = getLocationsByPrefix(term);
                return gson.toJson(matches);
            }
        });


        get("/", (request, response) -> {
            response.redirect("/map.html", 301);
            return true;
        });
    }


    private static HashMap<String, Double> getRequestParams(
            spark.Request req, String[] requiredParams) {
        Set<String> reqParams = req.queryParams();
        HashMap<String, Double> params = new HashMap<>();
        for (String param : requiredParams) {
            if (!reqParams.contains(param)) {
                halt(HALT_RESPONSE, "Request failed - parameters missing.");
            } else {
                try {
                    params.put(param, Double.parseDouble(req.queryParams(param)));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    halt(HALT_RESPONSE, "Incorrect parameters - provide numbers.");
                }
            }
        }
        return params;
    }


    private static void writeImagesToOutputStream(Map<String, Object> rasteredImageParams,
                                                  ByteArrayOutputStream os) {
        String[][] renderGrid = (String[][]) rasteredImageParams.get("render_grid");
        int numVertTiles = renderGrid.length;
        int numHorizTiles = renderGrid[0].length;

        BufferedImage img = new BufferedImage(numHorizTiles * MapServer.TILE_SIZE,
                numVertTiles * MapServer.TILE_SIZE, BufferedImage.TYPE_INT_RGB);
        Graphics graphic = img.getGraphics();
        int x = 0, y = 0;

        for (int r = 0; r < numVertTiles; r += 1) {
            for (int c = 0; c < numHorizTiles; c += 1) {
                graphic.drawImage(getImage(renderGrid[r][c]), x, y, null);
                x += MapServer.TILE_SIZE;
                if (x >= img.getWidth()) {
                    x = 0;
                    y += MapServer.TILE_SIZE;
                }
            }
        }


        double ullon = (double) rasteredImageParams.get("raster_ul_lon");
        double ullat = (double) rasteredImageParams.get("raster_ul_lat");
        double lrlon = (double) rasteredImageParams.get("raster_lr_lon");
        double lrlat = (double) rasteredImageParams.get("raster_lr_lat");

        final double wdpp = (lrlon - ullon) / img.getWidth();
        final double hdpp = (ullat - lrlat) / img.getHeight();
        if (route != null && !route.isEmpty()) {
            Graphics2D g2d = (Graphics2D) graphic;
            g2d.setColor(MapServer.ROUTE_STROKE_COLOR);
            g2d.setStroke(new BasicStroke(MapServer.ROUTE_STROKE_WIDTH_PX,
                    BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            route.stream().reduce((v, w) -> {
                g2d.drawLine((int) ((graph.lon(v) - ullon) * (1 / wdpp)),
                        (int) ((ullat - graph.lat(v)) * (1 / hdpp)),
                        (int) ((graph.lon(w) - ullon) * (1 / wdpp)),
                        (int) ((ullat - graph.lat(w)) * (1 / hdpp)));
                return w;
            });
        }

        rasteredImageParams.put("raster_width", img.getWidth());
        rasteredImageParams.put("raster_height", img.getHeight());

        try {
            ImageIO.write(img, "png", os);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static BufferedImage getImage(String imgPath) {
        BufferedImage tileImg = null;
        if (tileImg == null) {
            try {
                File in = new File(imgPath);
                tileImg = ImageIO.read(in);
            } catch (IOException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        return tileImg;
    }


    public static void clearRoute() {
        Router.clearRoute();
        route = new LinkedList<Long>();
    }


    public static List<String> getLocationsByPrefix(String prefix) {
        return graph.tst.valuesWithPrefix(GraphDB.normalizeString(prefix))
                .stream().map(Vertex::getName).collect(Collectors.toList());
    }


    public static List<Map<String, Object>> getLocations(String locationName) {
        String location = GraphDB.normalizeString(locationName);
        List<Vertex> matches = graph.tst.valuesWithPrefix(location);
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (Vertex v : matches) {
            Map<String, Object> map = new HashMap<>();
            map.put("lat", v.getLat());
            map.put("lon", v.getLng());
            map.put("name", v.getName());
            map.put("id", v.getId());
            mapList.add(map);
        }
        return mapList;
    }


    private static boolean validateRasteredImgParams(Map<String, Object> rip) {
        for (String p : REQUIRED_RASTER_RESULT_PARAMS) {
            if (!rip.containsKey(p)) {
                System.out.println("Your rastering result is missing the " + p + " field.");
                return false;
            }
        }
        if (rip.containsKey("query_success")) {
            boolean success = (boolean) rip.get("query_success");
            if (!success) {
                System.out.println("query_success was reported as a failure");
                return false;
            }
        }
        return true;
    }
}
