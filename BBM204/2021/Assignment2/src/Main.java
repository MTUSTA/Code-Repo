import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;

public class Main {
    /**
     * Propogated {@link IOException} here
     * {@link #readFile} and {@link #writeOutput} methods should be called here
     * A {@link Scheduler} instance must be instantiated here
     */
    public static void main(String[] args) throws IOException {
        // step 1 Reading the Input File with GSON
        Assignment[] assignments = readFile(args[0]);
        // step 2 Sorting the Array
        Arrays.sort(assignments);
        // creating schedular
        Scheduler scheduler = new Scheduler(assignments);
        // creating output for dynamic and greedy
        writeOutput("solution_dynamic.json",scheduler.scheduleDynamic());
        writeOutput("solution_greedy.json",scheduler.scheduleGreedy());
    }

    /**
     * @param filename json filename to read
     * @return Returns a list of {@link Assignment}s obtained by reading the given json file
     * @throws FileNotFoundException If the given file does not exist
     */
    private static Assignment[] readFile(String filename) throws FileNotFoundException {
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader(filename));
        Assignment[] assignments = gson.fromJson(reader,Assignment[].class);

        return assignments;
    }

    /**
     * @param filename  json filename to write
     * @param arrayList a list of {@link Assignment}s to write into the file
     * @throws IOException If something goes wrong with file creation
     */
    private static void writeOutput(String filename, ArrayList<Assignment> arrayList) throws IOException {
        try (Writer writer = new FileWriter(filename)) {
            GsonBuilder builder = new GsonBuilder();
            builder.disableHtmlEscaping();
            builder.setPrettyPrinting();
            Gson gson = builder.create();
            gson.toJson(arrayList.clone(), writer);
        }
    }
}
