package index;


import mainClasses.*;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Vector;

public class Point implements Serializable {
    private Object width,length,height;
    private Vector<String> references;
    private String pageName;
    private Octree parent;

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
    public Octree getParent() {
        return parent;
    }

    public void setParent(Octree parent) {
        this.parent = parent;
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
    public boolean isEqual(Point p) {
        boolean xEqual = Comparison.compareTo(this.width,p.getWidth(),null)==0;
        boolean yEqual = Comparison.compareTo(this.length,p.getLength(),null)==0;
        boolean zEqual = Comparison.compareTo(this.height,p.getHeight(),null)==0;
        return xEqual && yEqual && zEqual;
    }
    public boolean isPartialEqual(Point p) {
        boolean xEqual = Comparison.compareToOctree(this.width,p.getWidth(),null)==0;
        boolean yEqual = Comparison.compareToOctree(this.length,p.getLength(),null)==0;
        boolean zEqual = Comparison.compareToOctree(this.height,p.getHeight(),null)==0;
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
    public boolean checkNulls(){
        if(width==null||length==null||height==null
        || width instanceof DBAppNull || length instanceof DBAppNull || height instanceof DBAppNull) {
            return true;
        }
        return false;
    }

    public void removeDataWithOctree(String strTableName,Hashtable<String, Object> htblColNameValue, String strColWidth, String strColLength, String strColHeight) throws IOException, ClassNotFoundException , DBAppException {
        Page p;
//        HashSet<String> notDeletedPages = new HashSet<>();
        Table currTable = FileManipulation.loadTable("src/main/resources/data/tables/",strTableName);
        for(int i = 0 ; i < references.size() ; i++){
            String currPage = references.get(i);
            if(!currTable.getTablePages().contains(currPage)){
                references.remove(i);
                i--;
                continue;
            }
            p = FileManipulation.loadPage(currPage);
            boolean isDeleted = p.deleteFromPage(htblColNameValue);
            int indexOfPage = currTable.getTablePages().indexOf(currPage);
            if(isDeleted){
                currTable.getTablePages().remove(indexOfPage);
                currTable.getMaxValues().remove(indexOfPage);
                currTable.getMinValues().remove(indexOfPage);
                this.references.remove(i);
                i--;
            }else {
                currTable.updateMinMax(p,indexOfPage);
            }
        }
        this.adjustReferences(strColWidth,strColLength,strColHeight);
        currTable.saveIntoTableFilepath();
    }
    public void adjustReferences(String strColWidth, String strColLength, String strColHeight) throws IOException, ClassNotFoundException {
        HashMap<String,Integer> map = new HashMap<>();
        for(int i=0;i<this.references.size();i++){
            map.put(references.get(i),map.getOrDefault(references.get(i),0)+1);
        }
        for(String currPage: map.keySet()){
            Page p = FileManipulation.loadPage(currPage);
            int similarTuples = checkIfPointExistInPage(p,strColWidth,strColLength,strColHeight);
            for(int j=0;j<map.get(currPage)-similarTuples;j++){
                this.references.remove(currPage);
            }
        }

    }
    public Vector<Tuple> getPointsTuples() throws IOException, ClassNotFoundException {
        Vector<Tuple> tuples = new Vector<Tuple>();
        HashSet<String> hashSet = new HashSet();
        for(int i = 0 ; i < this.references.size()  ; i++){
            String pageString = this.references.get(i);
            if(hashSet.contains(pageString)) continue;
            hashSet.add(pageString);
            Page page = FileManipulation.loadPage(pageString);
            for(int j = 0 ; j < page.getPageTuples().size() ; j++){
                Tuple temp = page.getPageTuples().get(j);
                if(this.isInPoint(temp)) tuples.add(temp);
            }
        }
        return tuples;
    }
    public boolean isInPoint(Tuple t){
        Hashtable<String , Object> hash = t.getTupleAttributes();
        boolean h = Comparison.compareTo(hash.get(this.parent.getStrColHeight()),this.height,null)==0;
        boolean w = Comparison.compareTo(hash.get(this.parent.getStrColWidth()),this.width,null)==0;
        boolean l = Comparison.compareTo(hash.get(this.parent.getStrColLength()),this.length,null)==0;
        return h && w && l;
    }
    public int checkIfPointExistInPage(Page page, String strColWidth, String strColLength, String strColHeight){
        int tuples = 0;
        for(int i = 0 ; i<page.getPageTuples().size();i++){
            Tuple currentTuple = page.getPageTuples().get(i);
            //maybe simulating null will cause an error
            boolean checkWidth = Comparison.compareTo(width,currentTuple.getTupleAttributes().get(strColWidth),null)==0;
            boolean checkHeight = Comparison.compareTo(height,currentTuple.getTupleAttributes().get(strColHeight),null)==0;
            boolean checkLength = Comparison.compareTo(length,currentTuple.getTupleAttributes().get(strColLength),null)==0;
            if(checkWidth && checkLength && checkHeight) {
                tuples+=1;
            }
        }
        return tuples;
    }

}
