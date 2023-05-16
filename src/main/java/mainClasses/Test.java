package mainClasses;

import index.Octree;
import sqlterm.SQLTerm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;

public class Test {
    public static void main(String[] args) throws Exception {

        Hashtable<String, Object> tuple1 = new Hashtable();
        tuple1.put("age", 1);
        tuple1.put("name", "Kimo2");
        tuple1.put("gpa", 5.0);
        tuple1.put("dob", (new SimpleDateFormat("dd-MM-yyyy")).parse("05-07-2002"));
        Hashtable<String, Object> tuple2 = new Hashtable();
        tuple2.put("age", 2);
        tuple2.put("name", "Omar");
        tuple2.put("gpa", 4);
        tuple2.put("dob", (new SimpleDateFormat("dd-MM-yyyy")).parse("31-12-1956"));
        Hashtable<String, Object> tuple3 = new Hashtable();
        tuple3.put("age", 3);
        tuple3.put("name", "Ahmed");
        tuple3.put("gpa", 0.9);
        tuple3.put("dob", (new SimpleDateFormat("dd-MM-yyyy")).parse("05-12-1999"));
        Hashtable<String, Object> tuple4 = new Hashtable();
        tuple4.put("age", 4);
        tuple4.put("name", "biso");
        tuple4.put("gpa", 2.3);
        tuple4.put("dob", (new SimpleDateFormat("dd-MM-yyyy")).parse("07-04-2022"));
        Hashtable<String, Object> tuple5 = new Hashtable();
        tuple5.put("age", 5);
        tuple5.put("name", "Menna");
        tuple5.put("gpa", 0.8);
        tuple5.put("job", "eng");
        tuple5.put("dob", (new SimpleDateFormat("dd-MM-yyyy")).parse("01-01-2004"));
        Hashtable<String, Object> tuple6 = new Hashtable();
        tuple6.put("age", 6);
        tuple6.put("name", "Lobna");
        tuple6.put("gpa", 1.4);
        Hashtable<String, Object> tuple7 = new Hashtable();
        tuple7.put("age", 7);
        tuple7.put("name", "boni");
        tuple7.put("gpa", 3.2);
        tuple7.put("dob", (new SimpleDateFormat("dd-MM-yyyy")).parse("01-03-2002"));
        Hashtable<String, Object> tuple8 = new Hashtable();
        tuple8.put("age", 8);
        tuple8.put("name", "nada");
        tuple8.put("gpa", 2.5);
        tuple8.put("job", "eng");
        Hashtable<String, Object> tuple9 = new Hashtable();
        tuple9.put("age", 9);
        tuple9.put("name", "noura");
        tuple9.put("gpa", 3.4);
        tuple9.put("job", "doc");
        Hashtable<String, Object> tuple10 = new Hashtable();
        tuple10.put("age", 10);
        tuple10.put("name", "nada");
        tuple10.put("gpa", 0.9);
        tuple10.put("job", "eng");
        Hashtable<String, Object> tuple11 = new Hashtable();
        tuple11.put("age", 11);
        tuple11.put("name", "nada");
        tuple11.put("gpa", 0.9);
        Hashtable<String, Object> tuple12 = new Hashtable();
        tuple12.put("age", 12);
        tuple12.put("name", "sara");
        tuple12.put("gpa", 0.9);
        Hashtable<String, Object> tuple13 = new Hashtable();
        tuple13.put("age", 13);
        tuple13.put("name", "sara");
        tuple13.put("gpa", 0.9);
        Hashtable<String, Object> tuple14 = new Hashtable();
        tuple14.put("age", 14);
        Hashtable<String, Object> tuple15 = new Hashtable();
        tuple15.put("age", 29);
        tuple15.put("name", "afterMod");
        tuple15.put("job", "eng");
        Hashtable<String, Object> tuple16 = new Hashtable();
        tuple16.put("age", 18);
        tuple16.put("name", "kimo");
        tuple15.put("gpa", 4);
        tuple15.put("job", "doc");
        Hashtable<String, Object> tuple17 = new Hashtable();
        tuple17.put("age", 19);
        tuple17.put("gpa", 3);
        tuple17.put("job", "eng");
        Hashtable<String, Object> tuple18 = new Hashtable();
        tuple18.put("age", 17);
        tuple18.put("name", "kimo");
        tuple18.put("gpa", 3.0);
        Hashtable<String, Object> tuple19 = new Hashtable();
        tuple19.put("age", 16);
        tuple19.put("name", "kimo");
        tuple19.put("gpa", 3);
        Hashtable<String, Object> tuple20 = new Hashtable();
        tuple20.put("age", 24);
        tuple20.put("name", "biso");
        tuple20.put("gpa", 2.3);
        tuple20.put("dob", (new SimpleDateFormat("dd-MM-yyyy")).parse("01-01-2020"));
        Hashtable<String, Object> tuple21 = new Hashtable();
        tuple21.put("age", 20);
        tuple21.put("name", "biso");
        tuple21.put("gpa", 2.3);
        tuple21.put("dob", (new SimpleDateFormat("dd-MM-yyyy")).parse("01-01-2020"));
        Hashtable<String, Object> tuple22 = new Hashtable();
        tuple22.put("age", 22);
        tuple22.put("name", "abbas");
        tuple22.put("gpa", 2.2);
        tuple22.put("dob", (new SimpleDateFormat("dd-MM-yyyy")).parse("01-02-2020"));
        Hashtable<String, Object> tuple23 = new Hashtable();
        tuple23.put("age", 36);
        tuple23.put("name", "ZZZZZZZZZZ");
        tuple23.put("gpa", 5.0);
        tuple23.put("dob", (new SimpleDateFormat("dd-MM-yyyy")).parse("05-07-2002"));
        Hashtable<String, Object> tuple24 = new Hashtable();
        tuple24.put("age", 27);
        tuple24.put("name", "ZZZZZZZZZZ");
        tuple24.put("gpa", 5.0);
        tuple24.put("dob", (new SimpleDateFormat("dd-MM-yyyy")).parse("31-12-2023"));
        Hashtable<String, Object> tuple25 = new Hashtable();
        tuple25.put("age", 28);
        tuple25.put("name", "ZZZZZZZZZZ");
        tuple25.put("gpa", 5.0);
        tuple25.put("dob", (new SimpleDateFormat("dd-MM-yyyy")).parse("31-12-2023"));
        Hashtable<String, Object> tuple26 = new Hashtable();
        tuple26.put("age", 31);
        tuple26.put("name", "ZZZZZZZZZZ");
        tuple26.put("gpa", 5.0);
        tuple26.put("dob", (new SimpleDateFormat("dd-MM-yyyy")).parse("31-12-2021"));
        Hashtable<String, Object> tuple27 = new Hashtable();
        tuple27.put("age", 30);
        tuple27.put("name", "ZZZZZZZZZZ");
        tuple27.put("gpa", 4.9);
        tuple27.put("dob", (new SimpleDateFormat("dd-MM-yyyy")).parse("31-12-2023"));

//        Hashtable<String, String> htblColNameType = new Hashtable();
//        htblColNameType.put("age", "java.lang.Integer");
//        htblColNameType.put("name", "java.lang.String");
//        htblColNameType.put("gpa", "java.lang.Double");
//        htblColNameType.put("dob", "java.util.Date");
//        htblColNameType.put("job", "java.lang.String");
//
//        Hashtable<String, String> htblColNameMin = new Hashtable();
//        htblColNameMin.put("age", "0");
//        htblColNameMin.put("name", "A");
//        htblColNameMin.put("gpa", "0");
//        htblColNameMin.put("dob", "1950-12-31");
//        htblColNameMin.put("job", "a");
//
//        Hashtable<String, String> htblColNameMax = new Hashtable();
//        htblColNameMax.put("age", "40");
//        htblColNameMax.put("name", "ZZZZZZZZZZ");
//        htblColNameMax.put("gpa", "5");
//        htblColNameMax.put("dob", "2023-12-31");
//        htblColNameMax.put("job", "zzzzzzzzzzzzzzzzzzzz");
//
        DBApp dbApp = new DBApp();
        dbApp.init();
//        dbApp.createTable("Students", "age", htblColNameType, htblColNameMin, htblColNameMax);
//        dbApp.createTable("Students2", "age", htblColNameType, htblColNameMin, htblColNameMax);
//        dbApp.createTable("Students3", "age", htblColNameType, htblColNameMin, htblColNameMax);
//////        dbApp.createIndex("Students",new String[]{"name","gpa","dob"});
//////    dbApp.insertIntoTable("Students", tuple0);
//         dbApp.insertIntoTable("Students", tuple1);
//         dbApp.insertIntoTable("Students", tuple2);
//         dbApp.insertIntoTable("Students", tuple6);
//         dbApp.insertIntoTable("Students", tuple7);
//         dbApp.insertIntoTable("Students", tuple8);
//         dbApp.insertIntoTable("Students", tuple3);
//         dbApp.insertIntoTable("Students", tuple5);
//         dbApp.insertIntoTable("Students", tuple4);
//         dbApp.insertIntoTable("Students", tuple9);
//         dbApp.insertIntoTable("Students", tuple11);
//         dbApp.insertIntoTable("Students", tuple10);
//         dbApp.insertIntoTable("Students", tuple12);
//         dbApp.insertIntoTable("Students", tuple13);
//         dbApp.insertIntoTable("Students", tuple14);
//         dbApp.insertIntoTable("Students", tuple15);
//         dbApp.insertIntoTable("Students", tuple16);
//         dbApp.insertIntoTable("Students", tuple17);
//         dbApp.insertIntoTable("Students", tuple18);
//         dbApp.insertIntoTable("Students", tuple19);
//         dbApp.insertIntoTable("Students", tuple20);
//         dbApp.insertIntoTable("Students", tuple21);
//         dbApp.insertIntoTable("Students", tuple22);
//         dbApp.insertIntoTable("Students", tuple23);
//         dbApp.insertIntoTable("Students", tuple24);
//         dbApp.insertIntoTable("Students", tuple25);
//         dbApp.insertIntoTable("Students", tuple26);
//         dbApp.insertIntoTable("Students", tuple27);

//        Hashtable<String, Object> updateHtbl = new Hashtable();
//        updateHtbl.put("name", "updatedName");
//        updateHtbl.put("gpa", 2);
////        updateHtbl.put("dob", (new SimpleDateFormat("dd-MM-yyyy")).parse("01-02-2019"));
//
//        Hashtable<String, Object> deletingCriteria0 = new Hashtable();
//        deletingCriteria0.put("gpa", 3);



        String tableName = "students";

        Hashtable<String, String> htblColNameType = new Hashtable<String, String>();
        htblColNameType.put("id", "java.lang.Integer");
        htblColNameType.put("first_name", "java.lang.String");
        htblColNameType.put("last_name", "java.lang.String");
        htblColNameType.put("dob", "java.util.Date");
        htblColNameType.put("gpa", "java.lang.Double");
        htblColNameType.put("job", "java.lang.String");
        htblColNameType.put("money", "java.lang.Double");

        Hashtable<String, String> minValues = new Hashtable<>();
        minValues.put("id", "0");
        minValues.put("first_name", "AAAAAA");
        minValues.put("last_name", "AAAAAA");
        minValues.put("dob", "1990-01-01");
        minValues.put("gpa", "0.7");
        minValues.put("job", "a");
        minValues.put("money", "0");

        Hashtable<String, String> maxValues = new Hashtable<>();
        maxValues.put("id", "50");
        maxValues.put("first_name", "zzzzzz");
        maxValues.put("last_name", "zzzzzz");
        maxValues.put("dob", "2000-12-31");
        maxValues.put("gpa", "5.0");
        maxValues.put("job", "ZZZZZZ");
        maxValues.put("money", "20000");

//        dbApp.createTable(tableName, "id", htblColNameType, minValues, maxValues);
//        dbApp.createIndex(tableName,new String[]{"gpa","first_name","dob"});
//        dbApp.createIndex(tableName,new String[]{"id","last_name","job"});
//        BufferedReader studentsTable = new BufferedReader(new FileReader("src/main/resources/students_table_4.csv"));
//        String record;
//
//        Hashtable<String, Object> row = new Hashtable<>();
//        while ((record = studentsTable.readLine()) != null) {
//            String[] fields = record.split(",");
//
//            row.put("id", Integer.parseInt(fields[0]));
//            row.put("first_name", fields[1]);
//            row.put("last_name", fields[2]);
//
//            int year = Integer.parseInt(fields[3].trim().substring(0, 4));
//            int month = Integer.parseInt(fields[3].trim().substring(5, 7));
//            int day = Integer.parseInt(fields[3].trim().substring(8));
//
//            Date dob = new Date(year - 1900, month - 1, day);
//            row.put("dob", dob);
//
//            double gpa = Double.parseDouble(fields[4].trim());
//
//            row.put("gpa", gpa);
//            row.put("job", fields[5]);
//            double money = Double.parseDouble(fields[6].trim());
//            row.put("money", money);
//            // long start = System.currentTimeMillis();
//            dbApp.insertIntoTable("students", row);
//            // long end = System.currentTimeMillis();
//            // long elapsedTime = end - start;
//            // System.out.println("Elapsed Time : "+ elapsedTime);
//            row.clear();
//        }
//        studentsTable.close();
//        Hashtable<String, Object> row1=new Hashtable<>();
//        row1.put("id", 3);
//        row1.put("first_name","Charlotte");
//        row1.put("last_name","abbas");
//        Date dob = new Date(1999 - 1900, 5 - 1, 10);
//        row1.put("dob", dob);
//        row1.put("gpa", 2.8);
////        dbApp.insertIntoTable("students", row1);
//
        Hashtable<String, Object> row2=new Hashtable<>();
        row2.put("id", 6);
        row2.put("first_name","Ava");
        row2.put("last_name","Eva");
        Date dob2 = new Date(1994 - 1900, 1 - 1, 05);
        row2.put("dob", dob2);
        row2.put("gpa", 0.85);
//        dbApp.insertIntoTable("students", row2);

//        Hashtable<String, Object> row3=new Hashtable<>();
//        row3.put("id", 262);
//        row3.put("first_name","ZZZZZZ");
//        row3.put("last_name","Eva");
//        Date dob3 = new Date(1994 - 1900, 1 - 1, 05);
//        row3.put("dob", dob3);
//        row3.put("gpa", 5.1);
//        dbApp.insertIntoTable("students", row3);

        Hashtable<String, Object> del=new Hashtable<>();
        del.put("id",4);
        del.put("gpa",3.19);
//        dbApp.deleteFromTable("students",del);

        Hashtable<String, Object> updateHtbl = new Hashtable();
//        updateHtbl.put("first_name", "Updated Charlotte");
        updateHtbl.put("gpa", 0.89);
        updateHtbl.put("job", "doc");
        updateHtbl.put("money", 90);
//        dbApp.updateTable(tableName,"6",updateHtbl);

        SQLTerm sqlTerm1 = new SQLTerm(tableName,"gpa","=",0.89);
        SQLTerm sqlTerm2 = new SQLTerm(tableName,"first_name","=","Ava");
        SQLTerm sqlTerm3 = new SQLTerm(tableName,"dob","=",new SimpleDateFormat("dd-MM-yyyy").parse("05-01-1994"));
        SQLTerm sqlTerm4 = new SQLTerm(tableName,"money","<=",300);
        SQLTerm sqlTerm5 = new SQLTerm(tableName,"last_name","=","Eva");
        SQLTerm sqlTerm6 = new SQLTerm(tableName,"job","=","doc");
        SQLTerm sqlTerm7 = new SQLTerm(tableName,"id","=",6);
        SQLTerm[] sqlTerms = new SQLTerm[]{sqlTerm1,sqlTerm2,sqlTerm3,sqlTerm4,sqlTerm5,sqlTerm6,sqlTerm7};
        String[] op = new String[]{"AND","AND","OR","OR","OR","OR"};
        Iterator itr = dbApp.selectFromTable(sqlTerms,op);
        while(itr.hasNext()){
            System.out.println(itr.next());
        }
        Table table = FileManipulation.loadTable(dbApp.getTablesFilepath(), tableName);
        Iterator var35 = table.getTablePages().iterator();
        while(var35.hasNext()) {
            String pageName = (String)var35.next();
            Page p = FileManipulation.loadPage(pageName);
            System.out.println("PAGE " + p.getPageID());
            System.out.println(p.getPageTuples());
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }

        System.out.println(table.getOctrees());

        for(int i = 0; i < table.getOctrees().size(); ++i) {
            Octree octree = FileManipulation.loadOctree("src/main/resources/data/indices/" + table.getTableName() + "/", (String)table.getOctrees().get(i));
            System.out.println(octree.getName());
            octree.printOctree();
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }
//        StringBuffer sb = new StringBuffer();
//        StringBuffer sbi = new StringBuffer();
//        sb.append("Create table Employee (id int primary key, name varchar , salary decimal)");
//        sbi.append("Insert into Employee (id , name , salary) values (201 , 'yoyo' , 2000)");
//        dbApp.parseSQL(sbi);

    }
}
