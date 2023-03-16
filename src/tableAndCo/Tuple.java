package tableAndCo;

import java.io.*;
import java.util.Date;
import java.util.Hashtable;

public class Tuple implements Serializable {
    private Hashtable<String , Object> tupleAttributes ;
    private String clusteringKey;

    public Tuple(Hashtable<String , Object> tupleAttributes,String clusteringKey){
        this.tupleAttributes=tupleAttributes;
        this.clusteringKey =clusteringKey;
    }
}
