import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Graph {
    // Implement the graph data structure here
    // Use Edge and Vertex classes as you see fit
    /* Code here */

    // converter -> long to vertices <id,Vertex>
    public Map<Long, Vertex> long_to_vertices = new HashMap<>();
    // edges
    public Map<Long, ArrayList<Vertex>> adj = new HashMap<>();


    // add node in vertices and
    public void addVertex(double lat, double lng, Long id) {
        Vertex vertex = new Vertex(lat, lng, id);
        // save nodes
        long_to_vertices.put(id, vertex);
    }

    // remove vertex from graph
    public void removeVertex(long v) {

        for (Long n : this.long_to_vertices.keySet()) {
            removeEdge(n, v);
        }
        // remove from map
        long_to_vertices.remove(v);
    }

    // add edge to graph
    public void addEdge(long v, long w) {
        Vertex v1 = long_to_vertices.get(v);
        Vertex w2 = long_to_vertices.get(w);
        // create default empty arraylist -> blocking null error
        adj.putIfAbsent(v, new ArrayList<>());
        adj.putIfAbsent(w, new ArrayList<>());

        adj.get(v).add(w2);
        adj.get(w).add(v1);
    }

    public void removeEdge(long v, long w) {
        Vertex v1 = long_to_vertices.get(v);
        Vertex w2 = long_to_vertices.get(w);
        if (adj.get(v) != null) {
            adj.get(v).remove(v1);
        }
        if (adj.get(w) != null) {
            adj.get(w).remove(w2);
        }

    }

}
