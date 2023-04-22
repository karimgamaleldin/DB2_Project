package index;

import java.util.Hashtable;
import java.util.Vector;

public class Node {
    private Vector<Node> childPoints;
    private Object x, y, z;
    boolean isLeaf;
    private int maxEntries;

    public Node(Object x, Object y, Object z, boolean isLeaf, int maxEntries) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.childPoints = new Vector<Node>();
        this.isLeaf = isLeaf;
        this.maxEntries = maxEntries;
    }
    public void insertIntoNode(Object x, Object y, Object z){
        if(this.childPoints.size()<maxEntries){
            //insert in right sorted place in childPoints
        }else {
            //divide x, y, z into halves and do it recursively according
        }
    }
    public Vector<Node> getChildPoints() {
        return childPoints;
    }

    public void setChildPoints(Vector<Node> childPoints) {
        this.childPoints = childPoints;
    }

    public Object getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public Object getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Object getZ() {
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
    public void setX(Object x) {
        this.x = x;
    }

    public void setY(Object y) {
        this.y = y;
    }

    public void setZ(Object z) {
        this.z = z;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }
}
