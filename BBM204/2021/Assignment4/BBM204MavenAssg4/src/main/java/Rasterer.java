import java.util.Map;


public class Rasterer {
    private final QuadTree quadTree;

    public Rasterer(String imgRoot) {
        quadTree = new QuadTree(imgRoot);
    }

    public Map<String, Object> getMapRaster(Map<String, Double> params) {
        return quadTree.search(params);
    }

}
