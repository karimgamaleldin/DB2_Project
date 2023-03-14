package pagesAndRelated;
import java.io.*;
import java.util.*;


public class Page implements Serializable{
    // if a field is not serializable it is marked as transistent
    private int maxSizePerPage;
    private Vector<Tuple> pageTuples;

    public Page(int maxSizePerPage) {
        this.pageTuples = new Vector<>();
        this.maxSizePerPage = maxSizePerPage;
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
