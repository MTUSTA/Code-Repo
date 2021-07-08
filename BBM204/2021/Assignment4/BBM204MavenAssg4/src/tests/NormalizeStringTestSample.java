import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class NormalizeStringTestSample {
    static class IO {
        @JsonProperty("input")
        String input;

        @JsonProperty("output")
        String output;
    }

    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            IO[] entries = mapper.readValue(new File("normalize_sample.json"), IO[].class);
            for (IO io : entries) {
                if (!GraphDB.normalizeString(io.input).equals(io.output)) {
                    System.out.println("Expected: '" + io.output + "'\nGot: '" + GraphDB.normalizeString(io.input) + "'");
                    System.out.println("For: '" + io.input + "'\n");
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
