package tableAndCo;
import java.io.*;
import java.util.*;


public class Page implements Serializable{
    // if a field is not serializable it is marked as transient
    private int pageID;
    private int maxSizePerPage;
    private String file_path;
    private Vector<Tuple> pageTuples;
    private Object minVal;
    private Object maxVal;
    private String clusteringKey;

    public Page(int pageID, String path , int maxSizePerPage) {
        this.pageID = pageID;
        this.pageTuples = new Vector<>();
        this.maxSizePerPage = maxSizePerPage;
        this.file_path = path + ".class" ;
    }

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


    // some helper methods
    public int getSizeOfPage(){
        return this.pageTuples.size();
    }
    public boolean isPageFull(){
        return this.getSizeOfPage() == this.maxSizePerPage;
    }
    public boolean isPageEmpty(){
        return this.pageTuples.isEmpty();
    }
    public int sizeOfPage() {
        return this.pageTuples.size();
    }
    public Tuple insertIntoPage(Hashtable<String,Object> tuple) {
        boolean wasFull = this.isPageFull();
        Tuple lastTuple = wasFull ? this.pageTuples.remove(this.getSizeOfPage() - 1) : null ;
        Tuple insertedTuple = new Tuple(tuple, clusteringKey);
        int start = 0 ;
        int end = this.getSizeOfPage() - 1;
        while(start <= end)
        {
            //adjust the sorting to insert the tuple in its correct position
//            Tuple currentTuple = this.pageTuples.get(i);
//            if(insertedTuple.compareTo(currentTuple) < 0){
//                this.pageTuples.add(i , insertedTuple);
//                break;
//            } else if (i == this.getSizeOfPage() - 1){
//                this.pageTuples.add(insertedTuple);
//            }
            int mid = (start + end) / 2;
            Tuple currentTuple = this.pageTuples.get(mid);
            if (insertedTuple.compareTo(currentTuple) > 0) {
                start = mid + 1;
            }
            else if (insertedTuple.compareTo(currentTuple) < 0){
                if ( mid == this.getSizeOfPage() - 1 ) {
                    this.pageTuples.add(insertedTuple);
                }
                else if (insertedTuple.compareTo(this.pageTuples.get(mid + 1)) > 0) {
                    this.pageTuples.add(mid+1,insertedTuple);
                }
                else {
                    end = mid ;
                }
            }
        }
        return lastTuple;
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
