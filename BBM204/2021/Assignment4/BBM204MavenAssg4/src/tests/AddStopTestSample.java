import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class AddStopTestSample {

    static class StopTest {
        @JsonProperty("route")
        ShortestPathTestSample.Route route;

        @JsonProperty("stops")
        List<Stop> stops;
    }

    static class Stop {
        @JsonProperty("input")
        StopInput input;

        @JsonProperty("output")
        List<Long> output;
    }

    static class StopInput {
        @JsonProperty("lon")
        Double lon;

        @JsonProperty("lat")
        Double lat;
    }

    public static void main(String[] args) {
        GraphDB graph = new GraphDB("berkeley.osm");
        ObjectMapper mapper = new ObjectMapper();
        Gson gson = new Gson();
        try {
            StopTest[] entries = mapper.readValue(new File("stop_sample.json"), StopTest[].class);
            for (StopTest test : entries) {
                List<Long> testRoute = Router.shortestPath(graph, test.route.input.stlon, test.route.input.stlat, test.route.input.destlon, test.route.input.destlat);

                if (!testRoute.equals(test.route.output)) {
                    System.out.println("Expected Route: '" + gson.toJson(test.route.output) + "'\nGot Route: '" + gson.toJson(test) + "'");
                    System.out.println("For Route: '" + gson.toJson(test.route.input) + "'\n");
                    TestUtils.fail();
                }

                for (Stop s : test.stops) {
                    List<Long> test_val = Router.addStop(graph, s.input.lat, s.input.lon);
                    if (!test_val.equals(s.output)) {
                        System.out.println("Expected: '" + gson.toJson(s.output) + "'\nGot: '" + gson.toJson(test_val) + "'");
                        System.out.println("For: '" + gson.toJson(test) + "'\n");
                        TestUtils.fail();
                    }
                }
                Router.clearRoute();
            }
            TestUtils.pass();
        } catch (IOException e) {
            e.printStackTrace();
            TestUtils.fail();
        }
    }
}
