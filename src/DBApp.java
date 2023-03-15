import exceptions.DBAppException;
import SQLTerm.SQLTerm;
import tableAndCo.Table;

import java.io.Serializable;
import java.util.*;

public class DBApp implements Serializable {
    private Table table;

    public void init(){

    };

    public void createTable(String strTableName,
                            String strClusteringKeyColumn,
                            Hashtable<String,String> htblColNameType,
                            Hashtable<String,String> htblColNameMin,
                            Hashtable<String,String> htblColNameMax ) throws DBAppException {

        MetaDataOperations.writeMetaData(
                "metadata.csv",
                strTableName,
                strClusteringKeyColumn,
                htblColNameType,
                htblColNameMin,
                htblColNameMax
        );
    }
//    public void createIndex(String strTableName , String[] strarrColName) throws DBAppException{
//
//    }
    public void insertIntoTable(String strTableName, Hashtable<String,Object> htblColNameValue) throws DBAppException{

    }
    public void updateTable(String strTableName, String strClusteringKeyValue, Hashtable<String,Object> htblColNameValue ) throws DBAppException{

    }
    public void deleteFromTable(String strTableName, Hashtable<String,Object> htblColNameValue) throws DBAppException{

    }
//    public Iterator selectFromTable(SQLTerm[] arrSQLTerms, String[] strarrOperators) throws DBAppException {
//
//    }
    public static void main(String[] args) throws DBAppException {
        String strTableName = "CityShop";
        DBApp dbApp = new DBApp( );
        Hashtable htblColNameType = new Hashtable( );
        htblColNameType.put("ID", "java.lang.Integer");
        htblColNameType.put("Name", "java.lang.String");
        htblColNameType.put("X", "java.lang.Double");
        htblColNameType.put("Y", "java.lang.Double");
        htblColNameType.put("Z", "java.lang.Double");
        htblColNameType.put("Specialization", "java.lang.String");
        htblColNameType.put("Address", "java.lang.String");

        Hashtable htblColNameMin = new Hashtable();
        htblColNameMin.put("ID", "0");
        htblColNameMin.put("Name", "A");
        htblColNameMin.put("X", "0");
        htblColNameMin.put("Y", "0");
        htblColNameMin.put("Z", "0");
        htblColNameMin.put("Specialization", "A");
        htblColNameMin.put("Address", "A");
        Hashtable htblColNameMax = new Hashtable();
        htblColNameMax.put("ID", "10000");
        htblColNameMax.put("Name", "ZZZZZZZZZZZ");
        htblColNameMax.put("X", "10000");
        htblColNameMax.put("Y", "10000");
        htblColNameMax.put("Z", "10000");
        htblColNameMax.put("Specialization", "ZZZZZZZZZZZ");
        htblColNameMax.put("Address","ZZZZZZZZZZZ" );
        dbApp.createTable( strTableName, "ID", htblColNameType, htblColNameMin, htblColNameMax );
    }
}