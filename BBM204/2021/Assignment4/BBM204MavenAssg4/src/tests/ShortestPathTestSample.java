import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ShortestPathTestSample {

    static class Route {
        @JsonProperty("output")
        List<Long> output;

        @JsonProperty("input")
        RouteInput input;
    }

    static class RouteInput {
        @JsonProperty("stlon")
        Double stlon;

        @JsonProperty("stlat")
        Double stlat;

        @JsonProperty("destlon")
        Double destlon;

        @JsonProperty("destlat")
        Double destlat;
    }


    public static void main(String[] args) {
        GraphDB graph = new GraphDB("berkeley.osm");
        ObjectMapper mapper = new ObjectMapper();
        Gson gson = new Gson();
        try {
            Route[] entries = mapper.readValue(new File("routing_sample.json"), Route[].class);
            for (Route r : entries) {
                List<Long> test = Router.shortestPath(graph, r.input.stlon, r.input.stlat, r.input.destlon, r.input.destlat);
                if (!test.equals(r.output)) {
                    System.out.println("Expected: '" + gson.toJson(r.output) + "'\nGot: '" + gson.toJson(test) + "'");
                    System.out.println("For: '" + gson.toJson(r.input) + "'\n");
                    TestUtils.fail();
                }
            }
            TestUtils.pass();
        } catch (IOException e) {
            e.printStackTrace();
            TestUtils.fail();
        }

    }
}
