import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TestTSTStructureSample {

    public static void main(String[] args) {
        Gson gson = new GsonBuilder().create();
        ObjectMapper mapper = new ObjectMapper();
        try {
            String[] names = mapper.readValue(new File("tst_input_sample.json"), String[].class);
            String output = Files.readString(Path.of("tst_output_sample.json"));
            TST<Vertex> tst = new TST<>();

            for (int i = 0; i < names.length; i++) {
                tst.put(GraphDB.normalizeString(names[i]), new Vertex(1,1, (long) i));
            }
            TST.Node<Vertex> correctRoot = gson.fromJson(output, TST.Node.class);

            ArrayList<Character> correctTraversal = new ArrayList<>();
            ArrayList<Character> testTraversal = new ArrayList<>();

            postOrder(tst.root, testTraversal);
            postOrder(correctRoot, correctTraversal);


            if (!correctTraversal.equals(testTraversal)) {
                System.out.println("Expected: '" + correctTraversal + "'\nGot: '" + testTraversal + "'");
                TestUtils.fail();
            }

            TestUtils.pass();
        } catch (IOException e) {
            e.printStackTrace();
            TestUtils.fail();
        }

    }

    public static void postOrder(TST.Node<Vertex> node, List<Character> list) {
        if (node == null)
            return;

        // first recur on left subtree
        postOrder(node.left, list);

        postOrder(node.mid, list);

        // then recur on right subtree
        postOrder(node.right, list);

        // now deal with the node
        list.add(node.c);
    }
}
