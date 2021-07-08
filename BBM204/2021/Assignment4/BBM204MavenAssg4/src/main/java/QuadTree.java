import java.util.*;


public class QuadTree {
    public QTreeNode root;
    private String imageRoot;

    public QuadTree(String imageRoot) {
        // Instantiate the root element of the tree with depth 0
        // Use the ROOT_ULLAT, ROOT_ULLON, ROOT_LRLAT, ROOT_LRLON static variables of MapServer class
        // Call the build method with depth 1
        // Save the imageRoot value to the instance variable
        /* Code here */

        root = new QTreeNode("", MapServer.ROOT_ULLAT, MapServer.ROOT_ULLON, MapServer.ROOT_LRLAT, MapServer.ROOT_LRLON, 0);
        build(root, 1);
        this.imageRoot = imageRoot;

    }

    /*Latitude is the Y axis, longitude is the X axis*/
    public void build(QTreeNode subTreeRoot, int depth) {
        // Recursive method to build the tree as instructed
        /* Code here */
        if (depth == 7) {
            return;
        }
        double ULLAT, ULLON, LRLAT, LRLON;
        /* 1: Upper left QTreeNode: same ULLON & ULLAT, diff LRLON & LRLAT */
        ULLAT = subTreeRoot.getUpperLeftLatitude();
        ULLON = subTreeRoot.getUpperLeftLongtitude();

        LRLAT = ((subTreeRoot.getUpperLeftLatitude() - subTreeRoot.getLowerRightLatitude()) / 2) + subTreeRoot.getLowerRightLatitude();
        LRLON = ((subTreeRoot.getLowerRightLongtitude() - subTreeRoot.getUpperLeftLongtitude()) / 2) + subTreeRoot.getUpperLeftLongtitude();
        subTreeRoot.NW = new QTreeNode(subTreeRoot.getName() + "1", ULLAT, ULLON, LRLAT, LRLON, depth);
        build(subTreeRoot.NW, depth + 1);

        /* 2: Upper right QTreeNode: same ULLAT & LRLON, diff ULLON & LRLAT*/
        ULLAT = subTreeRoot.getUpperLeftLatitude();
        ULLON = ((subTreeRoot.getLowerRightLongtitude() - subTreeRoot.getUpperLeftLongtitude()) / 2) + subTreeRoot.getUpperLeftLongtitude();

        LRLAT = ((subTreeRoot.getUpperLeftLatitude() - subTreeRoot.getLowerRightLatitude()) / 2) + subTreeRoot.getLowerRightLatitude();
        LRLON = subTreeRoot.getLowerRightLongtitude();


        subTreeRoot.NE = new QTreeNode(subTreeRoot.getName() + "2", ULLAT, ULLON, LRLAT, LRLON, depth);
        build(subTreeRoot.NE, depth + 1);

        /* 3: Lower left QTreeNode: same ULLON & LRLAT, diff ULLAT & LRLON*/
        ULLAT = ((subTreeRoot.getUpperLeftLatitude() - subTreeRoot.getLowerRightLatitude()) / 2) + subTreeRoot.getLowerRightLatitude();
        ULLON = subTreeRoot.getUpperLeftLongtitude();

        LRLAT = subTreeRoot.getLowerRightLatitude();
        LRLON = ((subTreeRoot.getLowerRightLongtitude() - subTreeRoot.getUpperLeftLongtitude()) / 2) + subTreeRoot.getUpperLeftLongtitude();

        subTreeRoot.SW = new QTreeNode(subTreeRoot.getName() + "3", ULLAT, ULLON, LRLAT, LRLON, depth);
        build(subTreeRoot.SW, depth + 1);

        /* 4: Lower right QTreeNode: same LRLON & LRLAT, diff ULLON & ULLAT */
        ULLAT = ((subTreeRoot.getUpperLeftLatitude() - subTreeRoot.getLowerRightLatitude()) / 2) + subTreeRoot.getLowerRightLatitude();
        ULLON = ((subTreeRoot.getLowerRightLongtitude() - subTreeRoot.getUpperLeftLongtitude()) / 2) + subTreeRoot.getUpperLeftLongtitude();
        LRLAT = subTreeRoot.getLowerRightLatitude();
        LRLON = subTreeRoot.getLowerRightLongtitude();

        subTreeRoot.SE = new QTreeNode(subTreeRoot.getName() + "4", ULLAT, ULLON, LRLAT, LRLON, depth);
        build(subTreeRoot.SE, depth + 1);


    }

    public Map<String, Object> search(Map<String, Double> params) {
        Map<String, Object> result = new HashMap<>();
        /*
         * Parameters are:
         * "ullat": Upper left latitude of the query box
         * "ullon": Upper left longitude of the query box
         * "lrlat": Lower right latitude of the query box
         * "lrlon": Lower right longitude of the query box
         * */
        double query_ullon = params.get("ullon");
        double query_ullat = params.get("ullat");
        double query_lrlon = params.get("lrlon");
        double query_lrlat = params.get("lrlat");
        QTreeNode query_node = new QTreeNode("target",query_ullat,query_ullon,query_lrlat,query_lrlon,0);
        // Instantiate a QTreeNode to represent the query box defined by the parameters
        // Calculate the lonDpp value of the query box
        Double width = params.get("w");
        double lonDpp = Math.abs(query_lrlon - query_ullon) / width;
        // Call the search() method with the query box and the lonDpp value
        ArrayList<QTreeNode> list = new ArrayList<>();
        this.search(query_node,this.root,lonDpp,list);

        // Call and return the result of the getMap() method to return the acquired nodes in an appropriate way
        /* Code here */
        result = this.getMap(list);

        return result;
    }

    private Map<String, Object> getMap(ArrayList<QTreeNode> list) {
        Map<String, Object> map = new HashMap<>();

        // Check if the root intersects with the given query box
        // I dont need this part
        // if (/* Replace *//* Code here */) {
        //    map.put("query_success", false);
        //    return map;
        //}

        // Use the get2D() method to get organized images in a 2D array
        String[][] images_2D = this.get2D(list);
        map.put("render_grid", images_2D/* Replace *//* Code here */);

        // Upper left latitude of the retrieved grid (Imagine the
        // 2D string array you have constructed as a big picture)
        map.put("raster_ul_lat", list.get(0).getUpperLeftLatitude()/* Replace *//* Code here */);

        // Upper left longitude of the retrieved grid (Imagine the
        // 2D string array you have constructed as a big picture)
        map.put("raster_ul_lon", list.get(0).getUpperLeftLongtitude()/* Replace *//* Code here */);

        // Upper lower right latitude of the retrieved grid (Imagine the
        // 2D string array you have constructed as a big picture)
        map.put("raster_lr_lat", list.get(list.size()-1).getLowerRightLatitude()/* Replace *//* Code here */);

        // Upper lower right longitude of the retrieved grid (Imagine the
        // 2D string array you have constructed as a big picture)
        map.put("raster_lr_lon", list.get(list.size()-1).getLowerRightLongtitude()/* Replace *//* Code here */);

        // Depth of the grid (can be thought as the depth of a single image)
        map.put("depth", list.get(0).getDepth()/* Replace *//* Code here */);

        map.put("query_success", true);
        return map;
    }

    private String[][] get2D(ArrayList<QTreeNode> list) {
        Set<Double> keys = new HashSet<>();
        for (QTreeNode x : list) {
            if (!keys.contains(x.getUpperLeftLatitude())) {
                keys.add(x.getUpperLeftLatitude());
            }
        }

        Map<Double,ArrayList<String>> mapping = new LinkedHashMap<>();
        for (QTreeNode x : list) {
            if(mapping.containsKey(x.getUpperLeftLatitude())) {
                mapping.get(x.getUpperLeftLatitude()).add(x.getName());
            } else {
                ArrayList<String> l = new ArrayList<>();
                l.add(x.getName());
                mapping.put(x.getUpperLeftLatitude(),l);
            }
        }
        // After you retrieve the list of images using the recursive search mechanism described above, you
        // should order them as a grid. This grid is nothing more than a 2D array of file names. To order
        // the images, you should determine correct row and column for each image (node) in the retrieved
        // list. As a hint, you should consider the latitude values of images to place them in the row, and
        // the file names of the images to place them in a column.
        /* Code here */


        String[][] images = new String[keys.size()][];
        int i = 0;
        for (double y : mapping.keySet()) {
            images[i] = new String[(mapping.get(y)).size()];
            for (int j = 0; j < mapping.get(y).size(); j++) {
                if (mapping.get(y).get(j).equals("")){
                    System.out.println("stop");
                    images[i][j] = this.imageRoot +"root.png";
                }
                images[i][j] = this.imageRoot + mapping.get(y).get(j) +".png";
            }
            i += 1;
        }
        return images;
    }

    public void search(QTreeNode queryBox, QTreeNode tile, double lonDpp, ArrayList<QTreeNode> list) {
        // The first part includes a recursive search in the tree. This process should consider both the
        // lonDPP property (discussed above) and if the images in the tree intersect with the query box.
        // (To check the intersection of two tiles, you should use the checkIntersection() method)
        // To achieve this, you should retrieve the first depth (zoom level) of the images which intersect
        // with the query box and have a lower lonDPP than the query box.
        // This method should fill the list given by the "ArrayList<QTreeNode> list" parameter
        /* Code here */
        if (checkIntersection(tile,queryBox)) {
            if (tile.getLonDPP() <= lonDpp || tile.getDepth() > 6) {
                list.add(tile);
            } else {
                this.search(queryBox,tile.NW,lonDpp,list);
                this.search(queryBox,tile.NE,lonDpp,list);
                this.search(queryBox,tile.SW,lonDpp,list);
                this.search(queryBox,tile.SE,lonDpp,list);

            }
        }
    }

    public boolean checkIntersection(QTreeNode tile, QTreeNode queryBox) {
        // Return true if two tiles are intersecting with each other
        /* Code here */
        if (tile.getUpperLeftLongtitude() >= queryBox.getLowerRightLongtitude()
                || tile.getLowerRightLongtitude() <= queryBox.getUpperLeftLongtitude()
                || tile.getLowerRightLatitude() >= queryBox.getUpperLeftLatitude()
                || tile.getUpperLeftLatitude() <= queryBox.getLowerRightLatitude()) {
            return false;
        }
        return true;
    }
}