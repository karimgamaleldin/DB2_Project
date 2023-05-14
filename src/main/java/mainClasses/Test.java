package mainClasses;

import index.Octree;

import java.text.SimpleDateFormat;
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

        Hashtable<String, String> htblColNameType = new Hashtable();
        htblColNameType.put("age", "java.lang.Integer");
        htblColNameType.put("name", "java.lang.String");
        htblColNameType.put("gpa", "java.lang.Double");
        htblColNameType.put("dob", "java.util.Date");
        htblColNameType.put("job", "java.lang.String");

        Hashtable<String, String> htblColNameMin = new Hashtable();
        htblColNameMin.put("age", "0");
        htblColNameMin.put("name", "A");
        htblColNameMin.put("gpa", "0");
        htblColNameMin.put("dob", "1950-12-31");
        htblColNameMin.put("job", "a");

        Hashtable<String, String> htblColNameMax = new Hashtable();
        htblColNameMax.put("age", "40");
        htblColNameMax.put("name", "ZZZZZZZZZZ");
        htblColNameMax.put("gpa", "5");
        htblColNameMax.put("dob", "2023-12-31");
        htblColNameMax.put("job", "zzzzzzzzzzzzzzzzzzzz");

        DBApp dbApp = new DBApp();
        dbApp.init();

        dbApp.createTable("Students", "age", htblColNameType, htblColNameMin, htblColNameMax);
        dbApp.createTable("Students2", "age", htblColNameType, htblColNameMin, htblColNameMax);
        dbApp.createTable("Students3", "age", htblColNameType, htblColNameMin, htblColNameMax);
        dbApp.createIndex("Students",new String[]{"name","gpa","dob"});
////    dbApp.insertIntoTable("Students", tuple0);
         dbApp.insertIntoTable("Students", tuple1);
         dbApp.insertIntoTable("Students", tuple2);
         dbApp.insertIntoTable("Students", tuple6);
         dbApp.insertIntoTable("Students", tuple7);
         dbApp.insertIntoTable("Students", tuple8);
         dbApp.insertIntoTable("Students", tuple3);
         dbApp.insertIntoTable("Students", tuple5);
         dbApp.insertIntoTable("Students", tuple4);
         dbApp.insertIntoTable("Students", tuple9);
         dbApp.insertIntoTable("Students", tuple11);
         dbApp.insertIntoTable("Students", tuple10);
         dbApp.insertIntoTable("Students", tuple12);
         dbApp.insertIntoTable("Students", tuple13);
         dbApp.insertIntoTable("Students", tuple14);
         dbApp.insertIntoTable("Students", tuple15);
         dbApp.insertIntoTable("Students", tuple16);
         dbApp.insertIntoTable("Students", tuple17);
         dbApp.insertIntoTable("Students", tuple18);
         dbApp.insertIntoTable("Students", tuple19);
         dbApp.insertIntoTable("Students", tuple20);
         dbApp.insertIntoTable("Students", tuple21);
         dbApp.insertIntoTable("Students", tuple22);
         dbApp.insertIntoTable("Students", tuple23);
         dbApp.insertIntoTable("Students", tuple24);
         dbApp.insertIntoTable("Students", tuple25);
         dbApp.insertIntoTable("Students", tuple26);
         dbApp.insertIntoTable("Students", tuple27);

        Hashtable<String, Object> updateHtbl = new Hashtable();
        updateHtbl.put("name", "updatedName");
        updateHtbl.put("gpa", 2);
//        updateHtbl.put("dob", (new SimpleDateFormat("dd-MM-yyyy")).parse("01-02-2019"));

        Hashtable<String, Object> deletingCriteria0 = new Hashtable();
        deletingCriteria0.put("gpa", 3);
        Table table = FileManipulation.loadTable(dbApp.getTablesFilepath(), (String)dbApp.getTables().get(0));
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

    }
}
