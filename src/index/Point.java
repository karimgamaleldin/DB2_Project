package index;

import helpers.Comparison;

import java.util.Vector;

public class Point {
    private Object x,y,z;
    private Vector<String> references;
    private String pageName;

    public Point(Object x, Object y, Object z, String ref) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.references = new Vector<String>();
        this.references.add(ref);
        this.pageName = ref;
    }

    public Vector<String> getReferences() {
        return references;
    }

    public void setReferences(Vector<String> references) {
        this.references = references;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public Object getX() {
        return x;
    }

    public void setX(Object x) {
        this.x = x;
    }

    public Object getY() {
        return y;
    }

    public void setY(Object y) {
        this.y = y;
    }

    public Object getZ() {
        return z;
    }

    public void setZ(Object z) {
        this.z = z;
    }

    public Object getWidth() {
        return width;
    }

    public void setWidth(Object width) {
        this.width = width;
    }

    public Object getLength() {
        return length;
    }

    public void setLength(Object length) {
        this.length = length;
    }

    public Object getHeight() {
        return height;
    }

    public void setHeight(Object height) {
        this.height = height;
    }

    public void insertDups(Point p) {
        this.references.add(p.getPageName());
    }
    public boolean isEqual(Point p){
        boolean xEqual = Comparison.compareTo(this.x,p.getX(),null)==0;
        boolean yEqual = Comparison.compareTo(this.y,p.getY(),null)==0;
        boolean zEqual = Comparison.compareTo(this.z,p.getZ(),null)==0;
        return xEqual && yEqual && zEqual;
    }
}
