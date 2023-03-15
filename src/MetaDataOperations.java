import java.io.File;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.Map.Entry;
import java.util.Set;

public class MetaDataOperations {
    public static void writeMetaData(String filePath,
                                           String strTableName,
                                           String strClusteringKeyColumn,
                                           Hashtable<String,String> htblColNameType,
                                           Hashtable<String,String> htblColNameMin,
                                           Hashtable<String,String> htblColNameMax)
    {
        try {
            PrintWriter pw= new PrintWriter(new File("./"+filePath));
            StringBuilder sb= new StringBuilder();
            //Table Name, Column Name, Column Type, ClusteringKey, IndexName,IndexType, min, max
            sb.append("Table Name");
            sb.append(",");
            sb.append("Column Name");
            sb.append(",");
            sb.append("Column Type");
            sb.append(",");
            sb.append("ClusteringKey");
            sb.append(",");
            sb.append("IndexName");
            sb.append(",");
            sb.append("IndexType");
            sb.append(",");
            sb.append("min");
            sb.append(",");
            sb.append("max");
            sb.append("\r\n");
            Set<Entry<String, String> > entrySet = htblColNameType.entrySet();
            for (Entry<String, String> entry : entrySet) {
                String columnName = entry.getKey();
                String dataType = entry.getValue();
                String isClusteringKeyStr = columnName.equals(strClusteringKeyColumn) ? "True" : "False";
                String minColValue = htblColNameMin.get(columnName);
                String maxColValue = htblColNameMax.get(columnName);
                sb.append(strTableName);
                sb.append(",");
                sb.append(columnName);
                sb.append(",");
                sb.append(dataType);
                sb.append(",");
                sb.append(isClusteringKeyStr);
                sb.append(",");
                sb.append("null");
                sb.append(",");
                sb.append("null");
                sb.append(",");
                sb.append(minColValue);
                sb.append(",");
                sb.append(maxColValue);
                sb.append("\r\n");
            }
            pw.write(sb.toString());
            pw.close();
            System.out.println("finished");
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
