import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import java.util.ArrayList;
import java.util.HashSet;

public class GraphDB {

    public Graph graph = new Graph();
    public TST<Vertex> tst = new TST<>();

    public GraphDB(String dbPath) {
        try {
            File inputFile = new File(dbPath);
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            GraphBuildingHandler gbh = new GraphBuildingHandler(this);
            saxParser.parse(inputFile, gbh);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        clean();
    }

    static String normalizeString(String s) {
        // Should match all strings that are not alphabetical
        String regex = "[^a-zA-Z]"/* Replace *//* Code here */;
        return s.replaceAll(regex, "").toLowerCase();
    }

    private void clean() {
        // Remove the vertices with no incoming and outgoing connections from your graph
        /* Code here */
        HashSet<Long> removedItemIDs = new HashSet<>();
        for (Long v : this.graph.long_to_vertices.keySet()) {
            // if nothing is connecting to this vertex, then delete it from the map
            if (this.graph.adj.get(v) != null && this.graph.adj.get(v).size() == 0) {
                removedItemIDs.add(v);

            } else if (this.graph.adj.get(v) == null) {
                removedItemIDs.add(v);

            }
        }
        this.graph.long_to_vertices.keySet().removeAll(removedItemIDs);
    }

    public double distance(Vertex v1, Vertex v2) {
        // Return the euclidean distance between two vertices
        /* Code here */
        double x1_minus_x2 = v1.getLat() - v2.getLat();
        double y1_minus_y2 = v1.getLng() - v2.getLng();
        // sqrt (x1_minus_x2 power 2 + y1_minus_y2 power 2)
        return Math.sqrt(Math.pow(x1_minus_x2, 2) + Math.pow(y1_minus_y2, 2));
    }


    public long closest(double lon, double lat) {
        // Returns the closest vertex to the given latitude and longitude values
        /* Code here */

        // temp vertex
        Vertex temp = new Vertex(lat, lon, Long.MAX_VALUE);
        // copy vertices id
        ArrayList<Long> vertices_id = new ArrayList<>(this.graph.long_to_vertices.keySet());
        Vertex min_id = this.graph.long_to_vertices.get(vertices_id.get(0));
        double init_distance = this.distance(min_id, temp);

        for (int i = 1; i < vertices_id.size(); i++) {
            Vertex taken = this.graph.long_to_vertices.get(vertices_id.get(i));
            double dist_result = this.distance(temp, taken);
            if (dist_result < init_distance) {
                min_id = taken;
            }
        }
        return min_id.getId();
    }

    double lon(long v) {
        // Returns the longitude of the given vertex, v is the vertex id
        /* Code here */
        Vertex given_v = this.graph.long_to_vertices.get(v);
        return given_v.getLng();
    }


    double lat(long v) {
        // Returns the latitude of the given vertex, v is the vertex id
        /* Code here */
        Vertex given_v = this.graph.long_to_vertices.get(v);
        return given_v.getLat();
    }
}
