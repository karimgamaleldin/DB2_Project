package main.java;

import java.io.*;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

public class Tuple implements Serializable {
    private Hashtable<String , Object> tupleAttributes ;
    private String clusteringKey;
    private String clusteringKeyDataType;

    public Tuple(Hashtable<String , Object> tupleAttributes, String clusteringKey, String clusteringKeyDataType) {
        this.tupleAttributes=tupleAttributes;
        this.clusteringKey =clusteringKey;
        this.clusteringKeyDataType = clusteringKeyDataType;
        if(this.clusteringKeyDataType.equals("java.lang.Double")){
            try{
                Object clusterKeyVal = Column.adjustDataType(""+this.tupleAttributes.get(this.clusteringKey),this.clusteringKeyDataType);
                this.tupleAttributes.put(this.clusteringKey,clusterKeyVal);
            }catch (Exception e){
//                throw new DBAppException("cannot convert cluster key in tuple");
            }

        }
    }

    public Hashtable<String, Object> getTupleAttributes() {
        return tupleAttributes;
    }

    public String getClusteringKey() {
        return clusteringKey;
    }

    public String getClusteringKeyDataType() {
        return clusteringKeyDataType;
    }

    public int compareTo(Tuple o) {
        // return 0 if equal , returns -ve if instance object is less than the argument else +ve
        Object t1 = this.getTupleAttributes().get(this.getClusteringKey());
        Object t2 = o.getTupleAttributes().get(o.getClusteringKey());
        return Comparison.compareTo(t1,t2,clusteringKeyDataType);
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
