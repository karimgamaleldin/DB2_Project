package tableAndCo;

import helpers.Comparison;

import java.io.*;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

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
        return Comparison.compareTo(t1,t2,type);
    }

    public boolean isEqual(Tuple t) {
        Set<Map.Entry<String, Object>> entrySet = this.getTupleAttributes().entrySet();
        Hashtable<String ,Object> htbl = t.getTupleAttributes();
        for (Map.Entry<String, Object> entry : entrySet) {
            String key = entry.getKey();
            Object value = entry.getValue();
            Object value2 = htbl.get(key);
            if(!value.equals(value2)){
                return false;
            }
        }
        return true;
    }
    @Override
    public String toString() {
        String tupleInfo = "";
        Set<Map.Entry<String, Object>> entrySet = this.getTupleAttributes().entrySet();
        for (Map.Entry<String, Object> entry : entrySet) {
            String key = entry.getKey();
            Object value = entry.getValue();
            tupleInfo+=key+": "+value+",\n";
        }
        tupleInfo+="--------------------------------\n";
        return tupleInfo;
    }


}
