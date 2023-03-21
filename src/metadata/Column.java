package metadata;

public class Column {
    private String tableName;
    private String columnName;
    private String columnType;
    private boolean isClusteringKey;
    private Object min;
    private Object max;

    public Column(String tableName , String columnName , String columnType , boolean isClusteringKey , Object min, Object max){
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

    public Object getMin() {
        return min;
    }

    public Object getMax() {
        return max;
    }
}