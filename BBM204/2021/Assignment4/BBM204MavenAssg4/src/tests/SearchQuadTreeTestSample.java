import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.io.File;
import java.util.Map;

public class SearchQuadTreeTestSample {
    static class SearchEntry {
        @JsonProperty("request")
        Map<String, Double> request;

        @JsonProperty("response")
        Map<String, Object> response;
    }
    public static void main(String[] args) {
        QuadTree tree = new QuadTree("img/");
        ObjectMapper mapper = new ObjectMapper();
        Gson gson = new Gson();

        try {
            SearchEntry[] entries = mapper.readValue(new File("searchtree_sample.json"), SearchEntry[].class);
            for (SearchEntry e : entries) {
                Map<String,Object> testMap = tree.search(e.request);
                String correctJSON = gson.toJson(e.response);
                String testJSON = gson.toJson(testMap);
                if (!correctJSON.equals(testJSON)){
                    System.out.println("Expected: '" + gson.toJson(e.response) + "'\nGot: '" + gson.toJson(testMap) + "'");
                    System.out.println("For: '" + gson.toJson(e.request) + "'\n");
                    TestUtils.fail();
                }
            }
            TestUtils.pass();
        } catch (Exception e) {e.printStackTrace();TestUtils.fail();}

    }
}