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

    public Hashtable<String, Object> getTupleAttributes() {
        return tupleAttributes;
    }

    public String getClusteringKey() {
        return clusteringKey;
    }

    public int compareTo(Tuple o) {
        // return 0 if equal , returns -ve if instance object is less than the argument else +ve
        Object t1 = this.getTupleAttributes().get(this.getClusteringKey());
        Object t2 = o.getTupleAttributes().get(o.getClusteringKey());
        String type = t1.getClass().getName();
        if (type.equals("java.lang.Integer")) {
            return ((Integer) t1).compareTo((Integer) t2);
        } else if (type.equals("java.lang.String")) {
            return ((String) t1).compareTo((String) t2);
        } else if (type.equals("java.lang.Double")) {
            return ((Double) t1).compareTo((Double) t2);
        } else if (type.equals("java.util.Date")) {
            return ((Date) t1).compareTo((Date) t2);
        } else {
            return 0;
        }
    }



}
