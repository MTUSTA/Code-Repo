public class Edge {
    private String name;
    private String speed;
    private double weight = 0.0;
    private Vertex source, destination;

    public double getWeight() { return weight; }

    public void setWeight(double weight) { this.weight = weight; }

    public String getName() { return name; }

    public String getSpeed() {
        return speed;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public Vertex getSource() {
        return source;
    }

    public void setSource(Vertex source) {
        this.source = source;
    }

    public Vertex getDestination() {
        return destination;
    }

    public void setDestination(Vertex destination) {
        this.destination = destination;
    }
}