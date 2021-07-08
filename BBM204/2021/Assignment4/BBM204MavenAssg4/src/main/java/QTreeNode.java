public class QTreeNode {
    private final String name;
    private final double upperLeftLatitude;
    private final double upperLeftLongtitude;

    private final double lowerRightLatitude;
    private final double lowerRightLongtitude;

    private final int depth;
    private final double lonDPP;

    QTreeNode NW, NE, SE, SW;

    QTreeNode(String name, double upperLeftLatitude, double upperLeftLongtitude, double lowerRightLatitude, double lowerRightLongtitude, int depth) {
        this.name = name;
        this.upperLeftLongtitude = upperLeftLongtitude;
        this.upperLeftLatitude = upperLeftLatitude;
        this.lowerRightLatitude = lowerRightLatitude;
        this.lowerRightLongtitude = lowerRightLongtitude;
        this.lonDPP = (lowerRightLongtitude - upperLeftLongtitude) / 256;
        this.depth = depth;
    }

    public String getName() {
        return name;
    }

    public double getUpperLeftLatitude() {
        return upperLeftLatitude;
    }

    public double getUpperLeftLongtitude() {
        return upperLeftLongtitude;
    }

    public double getLowerRightLatitude() {
        return lowerRightLatitude;
    }

    public double getLowerRightLongtitude() {
        return lowerRightLongtitude;
    }

    public int getDepth() {
        return depth;
    }

    public double getLonDPP() {
        return lonDPP;
    }
}