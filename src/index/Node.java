package index;

import java.util.Vector;

public class Node {
    private Vector<Node> childPoints;
    private double x, y, z;
    private int maxEntries;

    public Node(double x, double y, double z, int maxEntries) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.childPoints = new Vector<Node>();
        this.maxEntries = maxEntries;
    }
    public Vector<Node> getChildPoints() {
        return childPoints;
    }

    public void setChildPoints(Vector<Node> childPoints) {
        this.childPoints = childPoints;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public int getMaxEntries() {
        return maxEntries;
    }

    public void setMaxEntries(int maxEntries) {
        this.maxEntries = maxEntries;
    }

}
