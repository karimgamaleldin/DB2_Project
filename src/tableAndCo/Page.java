package tableAndCo;
import java.io.*;
import java.util.*;


public class Page implements Serializable{
    // if a field is not serializable it is marked as transient
    private int maxSizePerPage;
    private String file_path;
    private Vector<Tuple> pageTuples;
    private Object minVal;
    private Object maxVal;

    public Object getMinVal() {
        return minVal;
    }

    public void setMinVal(Object minVal) {
        this.minVal = minVal;
    }

    public Object getMaxVal() {
        return maxVal;
    }

    public void setMaxVal(Object maxVal) {
        this.maxVal = maxVal;
    }

    public Page(String path , int maxSizePerPage) {
        this.pageTuples = new Vector<>();
        this.maxSizePerPage = maxSizePerPage;
        this.file_path = path + ".class" ;
    }

    // some helper methods
    public boolean isPageFull(){
        return this.pageTuples.size() == this.maxSizePerPage;
    }
    public boolean isPageEmpty(){
        return this.pageTuples.isEmpty();
    }
    public int sizeOfPage() {
        return this.pageTuples.size();
    }
    public void insertIntoPage(Hashtable<String,String> tuple) {
        for (Integer i = 0; i < pageTuples.size(); i++)
        {
            //adjust the sorting to insert the tuple in its correct position
        }
        //pageTuples.add(tuple);
    }
    public void deleteFromPage(Hashtable<String,String> tuple) {
        pageTuples.remove(tuple);
    }

    public int getMaxSizePerPage() {
        return maxSizePerPage;
    }

    public String getFile_path() {
        return file_path;
    }

    public Vector<Tuple> getPageTuples() {
        return pageTuples;
    }

    public void setMaxSizePerPage(int maxSizePerPage) {
        this.maxSizePerPage = maxSizePerPage;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public void setPageTuples(Vector<Tuple> pageTuples) {
        this.pageTuples = pageTuples;
    }
}
