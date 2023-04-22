package index;


import java.util.Hashtable;

public class Octree {
    private Node root;
    private String col1, col2, col3;
    private int maxEntries;
    public Octree(String col1, String col2, String col3, int maxEntries){
        root = new Node(null,null,null,true,maxEntries);;
        this.col1 = col1;
        this.col2 = col2;
        this.col3 = col3;
        this.maxEntries = maxEntries;
    }
    public void insertIntoOctree(String strTableName, Hashtable<String,Object> htblColNameValue){
        Object valOfCol1 = htblColNameValue.get(col1);
        Object valOfCol2 = htblColNameValue.get(col2);
        Object valOfCol3 = htblColNameValue.get(col3);
        root.insertIntoNode(valOfCol1,valOfCol2,valOfCol3);
    }
    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public String getCol1() {
        return col1;
    }

    public void setCol1(String col1) {
        this.col1 = col1;
    }

    public String getCol2() {
        return col2;
    }

    public void setCol2(String col2) {
        this.col2 = col2;
    }

    public String getCol3() {
        return col3;
    }

    public void setCol3(String col3) {
        this.col3 = col3;
    }

    public int getMaxEntries() {
        return maxEntries;
    }

    public void setMaxEntries(int maxEntries) {
        this.maxEntries = maxEntries;
    }
}
