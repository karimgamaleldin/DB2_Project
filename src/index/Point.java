package index;

import main.java.DBAppException;
import main.java.Comparison;
import main.java.FileManipulation;
import main.java.Page;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;

public class Point {
    private Object width,length,height;
    private Vector<String> references;
    private String pageName;

    public Point(Object width , Object length, Object height, String ref) {
        this.width = width;
        this.length = length;
        this.height = height;
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
        boolean xEqual = Comparison.compareTo(this.width,p.getWidth(),null)==0;
        boolean yEqual = Comparison.compareTo(this.length,p.getLength(),null)==0;
        boolean zEqual = Comparison.compareTo(this.height,p.getHeight(),null)==0;
        return xEqual && yEqual && zEqual;
    }

    @Override
    public String toString() {
        return "(" +
                "width=" + width +
                ", length=" + length +
                ", height=" + height +
                ", ref="+references+
                ')';
    }

    public void removeDataWithOctree(Hashtable<String, Object> htblColNameValue) throws IOException, ClassNotFoundException , DBAppException {
        for(int i = 0 ; i < references.size() ; i++){
            Page p = FileManipulation.loadPage(references.get(i));
            boolean deleted = p.deleteFromPage(htblColNameValue);
            // todo serilize
        }
    }
}
