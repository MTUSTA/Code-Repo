import java.util.*;


public class Router {

    private static List<Vertex> stops = new ArrayList<>();
    private static Vertex start, end;

    public static LinkedList<Long> shortestPath(GraphDB g, double stlon, double stlat, double destlon, double destlat) {
        // Return the shortest path between start and end points
        // Use g.closest() to get start and end vertices
        long source = g.closest(stlon, stlat);
        long dest = g.closest(destlon, destlat);

        start = g.graph.long_to_vertices.get(source);
        end = g.graph.long_to_vertices.get(dest);
        // Return ids of vertices as a linked list

        Map<Long, Double> distanceSoFar = new HashMap<>();
        Map<Vertex, Vertex> pathToGoal = new HashMap<>();


        /* Code here */
        return new LinkedList<Long>();
    }

    public static LinkedList<Long> addStop(GraphDB g, double lat, double lon) {
        // Find the closest vertex to the stop coordinates using g.closest()
        long stop_coord_id = g.closest(lon,lat);
        // Add the stop to the stop list
        Vertex v = g.graph.long_to_vertices.get(stop_coord_id);
        stops.add(v);
        // Recalculate your route when a stop is added and return the new route
        LinkedList<Long> new_route = shortestPath(g, start.getLng(),start.getLat(),end.getLng(),end.getLat());
        /* Code here */
        return new_route;
    }

    public static void clearRoute() {
        start = null;
        end = null;
        stops = new ArrayList<>();
    }
}
