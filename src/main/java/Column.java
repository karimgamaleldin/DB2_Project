package main.java;

import java.text.SimpleDateFormat;

public class Column {
    private String tableName;
    private String columnName;
    private String columnType;
    private boolean isClusteringKey;
    private String min;
    private String max;

    public Column(String tableName , String columnName , String columnType , boolean isClusteringKey , String min, String max){
        this.tableName = tableName;
        this.columnName = columnName;
        this.columnType = columnType;
        this.isClusteringKey = isClusteringKey;
        this.min = min;
        this.max = max;
    }
    public String toString() {
        return tableName + "," + columnName + "," + columnType + "," + (isClusteringKey ? "True" : "False") + "," + min.toString()+ ","+max.toString();
    }

    public String getTableName() {
        return tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public String getColumnType() {
        return columnType;
    }

    public boolean isClusteringKey() {
        return isClusteringKey;
    }

    public String getMin() {
        return min;
    }

    public String getMax() {
        return max;
    }

    public static Object adjustDataType(String key,String type) throws Exception {
        if (type.equals("java.lang.Integer")) {
            return Integer.parseInt(key);
        } else if (type.equals("java.lang.String")) {
            return key;
        } else if (type.equals("java.lang.Double")) {
            return Double.parseDouble(key);
        } else if (type.equals("java.util.Date")) {
            //YYYY-MM-DD
            SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
            return formatter.parse(key);
        }
        return null;
    }
}