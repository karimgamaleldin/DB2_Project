package tableAndCo;
import exceptions.DBAppException;

import java.io.*;
import java.util.*;


public class Page implements Serializable{
    // if a field is not serializable it is marked as transient
    private int pageID;
    private int maxSizePerPage;
    private String filepath;
    private Vector<Tuple> pageTuples;
    private Tuple minVal;
    private Tuple maxVal;
    private String clusteringKey;
    private String tableName;

    public Page(int pageID, String filepath , int maxSizePerPage,String clusteringKey, String tableName) {
        this.pageID = pageID;
        this.pageTuples = new Vector<Tuple>();
        this.maxSizePerPage = maxSizePerPage;
        this.filepath = "data/"+tableName+"/"+filepath+".class" ;
        this.minVal = null;
        this.maxVal = null;
        this.clusteringKey = clusteringKey;
        this.tableName = tableName;
        createPageFile();
    }
    public void createPageFile(){
        try{
            File pageFile = new File(this.getFilepath());
            if (pageFile.createNewFile()) {
                System.out.println("File created: " + pageFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public Tuple getMinVal() {
        return minVal;
    }

    public void setMinVal(Tuple minVal) {
        this.minVal = minVal;
    }

    public Tuple getMaxVal() {
        return maxVal;
    }

    public void setMaxVal(Tuple maxVal) {
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
        if(file.available()!=0){
            ObjectInputStream on = new ObjectInputStream(file);
            //System.out.println(((Vector<Tuple>) on.readObject()).get(0).getClusteringKey());
            try {
//                System.out.println(on);
                Vector<Tuple> tmp = (Vector<Tuple>) on.readObject();
//                System.out.println(tmp.size());
                this.setPageTuples(tmp);
            }
            catch (EOFException e) {
                System.out.println("error:"+e.getMessage());
            }
        };

    }

    public void saveIntoPageFilepath() throws IOException {
        //save the newly inserted tuple in the file itself
        File f = new File(filepath);
        FileOutputStream file = new FileOutputStream(this.getFilepath());
        ObjectOutputStream out = new ObjectOutputStream(file);
        out.writeObject(this.getPageTuples());
        out.close();
        file.close();
        this.setPageTuples(null);//may give error
    }
    public Tuple insertIntoPage(Hashtable<String,Object> tuple) throws IOException, ClassNotFoundException {
        loadIntoPage();
//        System.out.println("in page");
        boolean wasFull = this.isPageFull();
//        System.out.println("before is full");
        Tuple lastTuple = wasFull ? this.pageTuples.remove(this.getSizeOfPage() - 1) : null ;
//        System.out.println("after is full");
        Tuple insertedTuple = new Tuple(tuple, clusteringKey);
//        System.out.println("after tuple");
        //System.out.println(this.pageTuples.get(0).getTupleAttributes().get(this.pageTuples.get(0).getClusteringKey()));
        if(this.isPageEmpty()){
//            System.out.println("in if");
            this.pageTuples.add(insertedTuple);
            saveIntoPageFilepath();
            return null;
        }
        int start = 0 ;
        int end = this.getSizeOfPage() - 1;
        if(insertedTuple.compareTo(this.pageTuples.get(0)) < 0){
            this.pageTuples.add(0, insertedTuple);
        }
       else
        {
            while (start <= end) {
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
                    int temp = mid+1;
                    if(temp>=this.getSizeOfPage()){
                        this.pageTuples.add(insertedTuple);
                        break;
                    }
                    Tuple nextTuple = this.pageTuples.get(temp);
                    if(insertedTuple.compareTo(nextTuple)<=0){
                        this.pageTuples.add(temp, insertedTuple);
                        break;
                    }
                    start = mid + 1;
                } else if (insertedTuple.compareTo(currentTuple) < 0) {
//                if ( mid == this.getSizeOfPage() - 1 ) {
//                    this.pageTuples.add(mid,insertedTuple);
//                }
//                else if (insertedTuple.compareTo(this.pageTuples.get(mid + 1)) > 0) {
//                    this.pageTuples.add(mid+1,insertedTuple);
//                }
//                else {
//                    end = mid ;
//                }
//                boolean isInserted = false;
//                for(int i=mid-1;i>=start;i--){
//                    currentTuple = this.pageTuples.get(i);
//                    if(insertedTuple.compareTo(currentTuple)>0){
//                        this.pageTuples.add(i+1,insertedTuple);
//                        isInserted=true;
//                        break;
//                    }
//                }
//                if(!isInserted){
//                    this.pageTuples.add(start-1,insertedTuple);
//                    break;
//                }
                    int temp = mid-1;
                    if(temp<0){
                        this.pageTuples.add(0,insertedTuple);
                        break;
                    }
                    Tuple nextTuple = this.pageTuples.get(temp);
                    if(insertedTuple.compareTo(nextTuple)>=0){
                        this.pageTuples.add(temp, insertedTuple);
                        break;
                    }
                    end = mid - 1;
                } else {
                    this.pageTuples.add(mid, insertedTuple);
                    break;
                }
            }
        }
//        if(start>end) {
//            if(start==this.getSizeOfPage()){
//                this.pageTuples.add(insertedTuple);
//            }else {
//                this.pageTuples.add(start,insertedTuple);
//            }
//        }

        if(minVal==null||insertedTuple.compareTo(minVal)<0){
            this.setMinVal(insertedTuple);
        }
        if(maxVal==null||insertedTuple.compareTo(maxVal)>0){
            this.setMaxVal(insertedTuple);
        }
        //System.out.println(insertedTupleValueOfKey);
//        System.out.println(this.pageTuples.size());
        saveIntoPageFilepath();
        return lastTuple;
    }
    public boolean deleteFromPage(Hashtable<String,Object> t) throws DBAppException{
        int indexDeleted = getIndexBinarySearch(t);
        if(indexDeleted == -1){
            throw new DBAppException("The tuple you specified is not present");
        }
        Tuple tuple = new Tuple(t,this.getClusteringKey());
        pageTuples.remove(indexDeleted);
        if(tuple.compareTo(minVal)==0) {
            minVal = pageTuples.get(0);
        }
        else if (tuple.compareTo(maxVal)==0){
            maxVal = pageTuples.get(this.getSizeOfPage()-1);


        }
        return true;
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

    public int getIndexBinarySearch(Hashtable<String,Object> tuple){
        Tuple t = new Tuple(tuple , this.clusteringKey);
        int start = 0;
        int end = this.pageTuples.size() - 1;
        while(start <= end){
            int mid = (start + end) / 2 ;
            if(t.compareTo(this.pageTuples.get(mid)) == 0){
                return mid;
            }
            else if (t.compareTo(this.pageTuples.get(mid)) < 0){
                end = mid - 1;
            }
            else {
                start = mid + 1;
            }
        }
        return -1;
    }
}
