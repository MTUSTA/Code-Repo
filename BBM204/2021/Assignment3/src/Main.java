
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        // reader object -> read lines from given files
        Reader reader = new Reader();
        // airport.txt lines
        ArrayList<String[]> airport = reader.ReadFile(args[0]);
        // create Digraph
        Digraph g = new Digraph(airport);
        // flightList.txt lines
        ArrayList<String[]> flightList = reader.ReadFile(args[1]);
        // create flights
        g.create_flight(flightList);
        // commandList.txt lines
        ArrayList<String[]> commands = reader.ReadFile(args[2]);
        // apply command.txt on graph
        PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
        reader.process(g, commands,writer);
        // close scanner object
        reader.scan.close();
        writer.close();
    }
}
