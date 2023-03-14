package tableAndCo;
import java.io.*;
import java.util.*;


public class Page implements Serializable{
    // if a field is not serializable it is marked as transistent
    private int maxSizePerPage;
    private String file_path;
    private Vector<Tuple> pageTuples;


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
    public int sizeOfPage(){
        return this.pageTuples.size();
    }

}
