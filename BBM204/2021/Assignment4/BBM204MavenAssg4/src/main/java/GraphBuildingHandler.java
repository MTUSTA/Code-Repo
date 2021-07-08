import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.*;


public class GraphBuildingHandler extends DefaultHandler {


    public static class Way {
        private String id;
        private String name;
        private String speed;
        private boolean isOneWay;

        private final ArrayList<Long> listOfNodes = new ArrayList<>();

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSpeed() {
            return speed;
        }

        public void setSpeed(String speed) {
            this.speed = speed;
        }

        public ArrayList<Long> getListOfNodes() {
            return listOfNodes;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setOneWay(boolean oneway) {
            this.isOneWay = oneway;
        }

        public boolean isOneWay() {
            return this.isOneWay;
        }
    }


    private static final Set<String> ALLOWED_HIGHWAY_TYPES = new HashSet<>(Arrays.asList
            ("motorway", "trunk", "primary", "secondary", "tertiary", "unclassified",
                    "residential", "living_street", "motorway_link", "trunk_link", "primary_link",
                    "secondary_link", "tertiary_link"));

    private String activeState = "";
    private final GraphDB g;
    private Way currentWay = new Way();
    private boolean flag = false;


    public GraphBuildingHandler(GraphDB g) {
        this.g = g;
    }

    long id;
    double lng;
    double lat;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (qName.equals("node")) {

            activeState = "node";

            // For the case of new nodes, you should add a new vertex to your graph and save other necessary information
            // to be used depending on your implementation (i.e. saving the last saved vertex).
            /* Code here */
            id = Long.parseLong(attributes.getValue("id"));
            lng = Double.parseDouble(attributes.getValue("lon"));
            lat = Double.parseDouble(attributes.getValue("lat"));

            this.g.graph.addVertex(lat, lng, id);


        } else if (qName.equals("way")) {

            activeState = "way";

            // The first one is encountering a new "way" start tag. In this scenario, you should instantiate
            // a new Way, while saving its id.
            /* Code here */
            long way_id = Long.parseLong(attributes.getValue("id"));
            currentWay = new Way();
            flag = false;

        } else if (activeState.equals("way") && qName.equals("nd")) {

            // The second scenario is encountering a new "nd" tag while the active state is "way". This
            // tag references a node in the current way by its node id. When this tag is encountered, you
            // should add the id to the listOfNodes in that Way.
            /* Code here */
            long ref = Long.parseLong(attributes.getValue("ref"));
            this.currentWay.listOfNodes.add(ref);

        } else if (activeState.equals("way") && qName.equals("tag")) {

            String k = attributes.getValue("k");
            String v = attributes.getValue("v");

            if (k.equals("maxspeed")) {

                // Set the speed for the current way
                /* Code here */
                this.currentWay.speed = "";
            } else if (k.equals("highway")) {

                // At this point, you should check if the parsed highway type is specified to be allowed in the
                // list ALLOWED_HIGHWAY_TYPES. If the type is allowed, you should set a flag as an
                // instance variable to specify that this way should be used to connect vertices in the graph.
                /* Code here */
                if (ALLOWED_HIGHWAY_TYPES.contains(v)) {
                    flag = true;
                }
            } else if (k.equals("name")) {

                // Set the name for the current way
                /* Code here */
                this.currentWay.name = v;
            } else if (k.equals("oneway")) {

                // Set the oneway property for the current way
                /* Code here */
                this.currentWay.isOneWay = false;
            }

        } else if (activeState.equals("node") && qName.equals("tag") && attributes.getValue("k").equals("name")){
            // For the case of encountering a "tag" tag with the attribute "name" while the active state is
            // "node", you should set the name of the current vertex, as well as insert the location name to
            // your ternary search tree. Do not forget to normalize the text by supplying the regular expression
            // given in the coding template (GraphDB.cleanString())
            // Insert the normalized text as a key along with an list which contains the Vertex if the key is not in GraphDB.locations
            // If the key is in GraphDB.locations, retrieve the list and add the Vertex to the list
            /* Code here */

            String name = attributes.getValue("v");
            String cleaned = GraphDB.normalizeString(name);
            // lat,lng,id -> assign line 72
            Vertex current_vertex = new Vertex(lat,lng,id);
            current_vertex.setName(name);
            if(cleaned.length() > 0){
                this.g.tst.put(cleaned.toLowerCase(),current_vertex);
            }

        }
    }


    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("way")) {
            // In endElement callback, you should handle ending of a way tag. If the way is marked as a valid
            // one in the third scenario described above, you should connect the vertices specified by the list
            // of references in the listOfNodes. In this step, you should be careful if the way is one-directional
            // or not. If the way you were parsing is not one-directional, you should add two edges to your
            // directed graph (in both directions).
            /* Code here */
            if (flag) {
                for (int i = 0; i < this.currentWay.listOfNodes.size() - 1; i++) {
                    this.g.graph.addEdge(this.currentWay.listOfNodes.get(i), this.currentWay.listOfNodes.get(i + 1));
                }
            }
            flag = false;
            this.currentWay = new Way();
        }
    }
}