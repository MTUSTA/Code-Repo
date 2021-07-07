
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class Digraph {

    private final int V;           // number of vertices in this digraph
    private int E;                 // number of edges in this digraph
    private Bag<Integer>[] adj;    // adj[v] = adjacency list for vertex v
    private int[] indegree;        // indegree[v] = indegree of vertex v


    //I need converter to easy use Digraph
    HashMap<Integer, Airport> Cities = new HashMap<Integer, Airport>();
    // reversed
    HashMap<Airport, Integer> CitiesR = new HashMap<Airport, Integer>();
    /* store flight like a object -> string is unique id -> IST int value is 3 , SOF int value is 0 -> combine them 30
     * 30 = arraylist object (TK8739 IST->SOF 30/04/2020 06:00 Thu	02:00 145)
     */
    HashMap<String, ArrayList<Flight>> flights = new HashMap<String, ArrayList<Flight>>();

    /**
     * Initializes an empty digraph with <em>V</em> vertices.
     *
     * @param V vertices arraylist
     */
    public Digraph(ArrayList<String[]> V) {

        this.V = V.size();
        this.E = 0;
        indegree = new int[V.size()];
        adj = (Bag<Integer>[]) new Bag[this.V];
        for (int v = 0; v < V.size(); v++) {
            adj[v] = new Bag<Integer>();
            Airport a = new Airport(V.get(v));

            this.Cities.put(v, a);
            this.CitiesR.put(a, v);
        }
    }


    /**
     * Returns the number of vertices in this digraph.
     *
     * @return the number of vertices in this digraph
     */
    public int V() {
        return V;
    }

    /**
     * Returns the number of edges in this digraph.
     *
     * @return the number of edges in this digraph
     */
    public int E() {
        return E;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }

    /**
     * Adds the directed edge v→w to this digraph.
     *
     * @param v the tail vertex
     * @param w the head vertex
     * @throws IllegalArgumentException unless both {@code 0 <= v < V} and {@code 0 <= w < V}
     */
    public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        adj[v].add(w);
        indegree[w]++;
        E++;
    }

    /**
     * Returns the vertices adjacent from vertex {@code v} in this digraph.
     *
     * @param v the vertex
     * @return the vertices adjacent from vertex {@code v} in this digraph, as an iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    /**
     * Returns the number of directed edges incident from vertex {@code v}.
     * This is known as the <em>outdegree</em> of vertex {@code v}.
     *
     * @param v the vertex
     * @return the outdegree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int outdegree(int v) {
        validateVertex(v);
        return adj[v].size();
    }

    /**
     * Returns the number of directed edges incident to vertex {@code v}.
     * This is known as the <em>indegree</em> of vertex {@code v}.
     *
     * @param v the vertex
     * @return the indegree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int indegree(int v) {
        validateVertex(v);
        return indegree[v];
    }

    /**
     * Returns a string representation of the graph.
     *
     * @return the number of vertices <em>V</em>, followed by the number of edges <em>E</em>,
     * followed by the <em>V</em> adjacency lists
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges\n");
        for (int v = 0; v < V; v++) {
            s.append(String.format("%d: ", v));
            for (int w : adj[v]) {
                s.append(String.format("%d ", w));
            }
            s.append("\n");
        }
        return s.toString();
    }

    public void create_flight(ArrayList<String[]> flightList) {
        for (String[] elements : flightList) {
            String[] src_to_dest = elements[1].split("->");
            Flight f = new Flight(elements[0], src_to_dest[0], src_to_dest[1], elements[2], elements[3], elements[4]);

            int src_index = -1;
            int dest_index = -1;
            // airport alias to integer conversation
            for (Airport a : CitiesR.keySet()) {
                int src_ind = a.alias.indexOf(src_to_dest[0]);
                int target_ind = a.alias.indexOf(src_to_dest[1]);
                if (src_ind != -1) {
                    src_index = CitiesR.get(a);
                }
                if (target_ind != -1) {
                    dest_index = CitiesR.get(a);
                }
                // early stopper
                if (src_index != -1 && dest_index != -1) {
                    break;
                }
            }
            ArrayList<Flight> exist = this.flights.get(Integer.toString(src_index) + Integer.toString(dest_index));
            if (exist == null) {
                ArrayList<Flight> arrayList = new ArrayList<Flight>();
                arrayList.add(f);
                this.flights.put(Integer.toString(src_index) + Integer.toString(dest_index), arrayList);
            } else {
                exist.add(f);
            }

            // any index equals -1, program will give an error
            this.addEdge(src_index, dest_index);
        }
    }

    public int[] searcher(String source, String target) {
        int[] arr1 = {-1, -1};
        for (Airport a : CitiesR.keySet()) {
            if (source.equals(a.name)) {
                arr1[0] = CitiesR.get(a);
            }
            if (target.equals(a.name)) {
                arr1[1] = CitiesR.get(a);
            }
            // early stopper
            if (arr1[0] != -1 && arr1[1] != -1) {
                break;
            }
        }
        return arr1;
    }


    // Prints all paths from 's' to 'd'
    public void AllPaths(int s, int d) {
        ArrayList<Flight> result_path = new ArrayList<Flight>();
        Date time = null;

        boolean[] isVisited = new boolean[this.V];
        ArrayList<Integer> pathList = new ArrayList<Integer>();
        // add source to path[]
        pathList.add(s);

        // Call recursive utility
        AllPathsUtil(s, d, isVisited, time, pathList, result_path);

    }

    // global variable
    Calendar cal = Calendar.getInstance();

    // A recursive function to print all paths from 'u' to 'd'. isVisited[] keeps track of vertices in current path.
    // localPathList<> stores actual vertices in the current path
    private void AllPathsUtil(Integer s, Integer d, boolean[] isVisited, Date time, ArrayList<Integer> localPathList, ArrayList<Flight> flight_path) {

        if (s.equals(d)) {
            String result_path_ids = "";
            for (Flight result_path_flight : flight_path) {
                result_path_ids = result_path_ids.concat(result_path_flight.getFlight_id());
            }

            boolean not_exist = true;
            for (ArrayList<Flight> f_path : Reader.listAll) {
                String id = "";
                for (Flight temp : f_path) {
                    id = id.concat(temp.getFlight_id());
                }
                if(id.compareTo(result_path_ids)==0){
                    not_exist = false;
                }
            }
            if(not_exist) {
                // copy arraylist
                ArrayList<Flight> clone = new ArrayList<Flight>(flight_path);
                Reader.listAll.add(clone);
            }
            return;
        }

        // Mark the current node
        isVisited[s] = true;

        // Recur for all the vertices adjacent to current vertex
        for (Integer i : this.adj(s)) {
            if (!isVisited[i]) {
                /*1 den fazla node olduğu icin localPathList ve result_path -> ArrayList<Flight> e cevir böylelikle sadece uygun ucusları kaydetmis olacaksın
                 * flight f ise arraylist olduğu icin orayi for loop a at -> 1 node olmassa diğer node dan devam eder.
                 *  */
                ArrayList<Flight> f = this.flights.get(Integer.toString(s) + Integer.toString(i));
                for (int fli = 0; fli < f.size(); fli++) {
                    String[] splittedflight_duration = f.get(fli).getFlight_duration().split(":");
                    // time is null set time or localPathList.size() == 1 means at the beginning reset loaded time
                    if (time == null) {

                        time = f.get(fli).getDeparture_date();
                        cal.setTime(time);
                        cal.add(Calendar.HOUR_OF_DAY, Integer.parseInt(splittedflight_duration[0]));
                        cal.add(Calendar.MINUTE, Integer.parseInt(splittedflight_duration[1]));
                        time = cal.getTime();
                    } else if (time.compareTo(f.get(fli).getDeparture_date()) > 0) {
                        return;
                    } else {
                        time = f.get(fli).getDeparture_date();
                        cal.setTime(time);
                        cal.add(Calendar.HOUR_OF_DAY, Integer.parseInt(splittedflight_duration[0]));
                        cal.add(Calendar.MINUTE, Integer.parseInt(splittedflight_duration[1]));
                        time = cal.getTime();
                    }
                    flight_path.add(f.get(fli));
                    // store current node in path[]
                    localPathList.add(i);
                    AllPathsUtil(i, d, isVisited, time, localPathList, flight_path);

                    // remove current node in path[]

                    localPathList.remove(i);
                    flight_path.remove(f.get(fli));
                    // Mark the current node
                    if (flight_path.size() != 0) {
                        time = flight_path.get(flight_path.size() - 1).getDeparture_date();
                        String[] call_back_flight_duration = flight_path.get(flight_path.size() - 1).getFlight_duration().split(":");
                        cal.setTime(time);
                        cal.add(Calendar.HOUR_OF_DAY, Integer.parseInt(call_back_flight_duration[0]));
                        cal.add(Calendar.MINUTE, Integer.parseInt(call_back_flight_duration[1]));
                        time = cal.getTime();
                    } else {
                        time = null;
                    }
                    // Mark the current node
                    isVisited[i] = false;

                }
            }
        }

    }

}
