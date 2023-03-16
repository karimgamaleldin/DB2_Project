package tableAndCo;
import java.io.*;
import java.util.*;


public class Page implements Serializable{
    // if a field is not serializable it is marked as transient
    protected int pageID;
    protected int maxSizePerPage;
    protected String filepath;
    private Vector<Tuple> pageTuples;
    protected Object minVal;
    protected Object maxVal;
    protected String clusteringKey;

    public Page(int pageID, String filepath , int maxSizePerPage) {
        this.pageID = pageID;
//        this.pageTuples = new Vector<Tuple>();
        this.maxSizePerPage = maxSizePerPage;
        this.filepath = filepath + ".class" ;
        this.minVal = null;
        this.maxVal = null;
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
    public void loadIntoPage() throws IOException, ClassNotFoundException {
        FileInputStream file = new FileInputStream(this.getFilepath());
        ObjectInputStream on = new ObjectInputStream(file);
        this.setPageTuples((Vector<Tuple>) on.readObject());
    }

    public void saveIntoPageFilepath() throws IOException {
        //save the newly inserted tuple in the file itself
        FileOutputStream file = new FileOutputStream(this.getFilepath());
        ObjectOutputStream out = new ObjectOutputStream(file);
        out.writeObject(this.getPageTuples());
        out.close();
        file.close();
        this.setPageTuples(null);
    }
    public Tuple insertIntoPage(Hashtable<String,Object> tuple) throws IOException, ClassNotFoundException {
        loadIntoPage();
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
//                if ( mid == this.getSizeOfPage() - 1 ) {
//                    this.pageTuples.add(mid,insertedTuple);
//                }
//                else if (insertedTuple.compareTo(this.pageTuples.get(mid + 1)) > 0) {
//                    this.pageTuples.add(mid+1,insertedTuple);
//                }
//                else {
//                    end = mid ;
//                }
                boolean isInserted = false;
                for(int i=mid;i>=start;i--){
                    currentTuple = this.pageTuples.get(i);
                    if(insertedTuple.compareTo(currentTuple)>0){
                        this.pageTuples.add(i+1,insertedTuple);
                        isInserted=true;
                        break;
                    }
                }
                if(!isInserted){
                    this.pageTuples.add(start-1,insertedTuple);
                }
            }else {
                this.pageTuples.add(mid,insertedTuple);
            }
        }
        if(start>end) {
            this.pageTuples.add(insertedTuple);
        }

        String insertedTupleKey = insertedTuple.getClusteringKey();
        Object insertedTupleValueOfKey = insertedTuple.getTupleAttributes().get(insertedTupleKey);
        if(minVal==null||insertedTuple.compareTo(minVal)<0){
            minVal = insertedTupleValueOfKey;
        }
        if(maxVal==null||insertedTuple.compareTo(maxVal)>0){
            maxVal = insertedTupleValueOfKey;
        }
        saveIntoPageFilepath();
        return lastTuple;
    }
    public void deleteFromPage(Hashtable<String,String> tuple) {
        pageTuples.remove(tuple);
    }

    public int getMaxSizePerPage() {
        return maxSizePerPage;
    }

    public String getFilepath() {
        return filepath;
    }

    public Vector<Tuple> getPageTuples() {
        return pageTuples;
    }

    public void setMaxSizePerPage(int maxSizePerPage) {
        this.maxSizePerPage = maxSizePerPage;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public void setPageTuples(Vector<Tuple> pageTuples) {
        this.pageTuples = pageTuples;
    }

    public int getPageID() {
        return pageID;
    }

    public String getClusteringKey() {
        return clusteringKey;
    }
}
