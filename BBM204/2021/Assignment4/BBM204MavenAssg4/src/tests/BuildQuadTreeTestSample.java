import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class BuildQuadTreeTestSample {
    public static void main(String[] args) {
        QuadTree quadTree = new QuadTree("img/");
        quadTree.root = new QTreeNode("root", MapServer.ROOT_ULLAT, MapServer.ROOT_ULLON,
                MapServer.ROOT_LRLAT, MapServer.ROOT_LRLON, 2);
        quadTree.build(quadTree.root, 3);
        Gson gson = new Gson();

        try {
            JsonReader reader = new JsonReader(new FileReader("quadtree_sample.json"));
            QTreeNode correctRoot = gson.fromJson(reader, QTreeNode.class);
            if (!equalsTree(2, quadTree.root, correctRoot)) {
                TestUtils.fail();
            }
            TestUtils.pass();
        } catch (Exception e) {e.printStackTrace();TestUtils.fail();}
    }

    public static boolean equalsTree(int depth, QTreeNode root1, QTreeNode root2) {
        if (depth > 7) {
            return true;
        }
        if (root1.getName().equals(root2.getName())) {

            return equalsTree(depth+1, root1.NW, root2.NW) &&
            equalsTree(depth+1, root1.NE, root2.NE) &&
            equalsTree(depth+1, root1.SW, root2.SW) &&
            equalsTree(depth+1, root1.SE, root2.SE);
        } else {
            System.out.println("At depth: " + depth);
            System.out.println("Expected: '" + root2.getName() + "'");
            System.out.println("Got: '" + root1.getName() + "'");
            return false;
        }
    }
}