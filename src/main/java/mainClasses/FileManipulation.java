package mainClasses;

import index.Octree;

import java.io.*;
import java.util.Properties;
import java.util.Vector;

public abstract class FileManipulation {
    public static void createSerFile(String filepath){
        try{
            File pageFile = new File(filepath);
            if (pageFile.createNewFile()) {
//                System.out.println("File created: " + pageFile.getName());
            } else {
//                System.out.println("File already exists.");
            }
        }catch (Exception e) {
            System.out.println("createSer:"+e.getMessage());
        }
    }

    public static void saveIntoFilepath(Object writeObj, String filepath) throws IOException {
        FileOutputStream file = new FileOutputStream(filepath);
        ObjectOutputStream out = new ObjectOutputStream(file);
        out.writeObject(writeObj);
        out.close();
        file.close();
//        this.setPageTuples(null);//may give error
    }

    public static void createDirectory(String dirpath) {
        File dir = new File(dirpath);
        // check if the directory can be created
        // using the specified path name
        if (dir.mkdir()) {
//            System.out.println("Directory has been created successfully");
        }
        else {
//            System.out.println("Directory cannot be created");
        }
    }

    public static void deleteEntireFile(String filepath)
    {
        try {
            File f= new File(filepath);           //file to be delete
            if(f.delete()) { //returns boolean value
                System.out.println(f.getName() + " deleted");   //getting and printing the file name
            }
            else {
                System.out.println("failed");
            }
            f = null;
            System.gc();
        }
        catch(Exception e) {
            System.out.println("deleteFile:"+e.getMessage());
        }
    }

    public static Page loadPage(String filepath) throws IOException, ClassNotFoundException {
        FileInputStream file = new FileInputStream(filepath);
        if(file.available()!=0){
            ObjectInputStream on = new ObjectInputStream(file);
            try {
                Page loadedPage = (Page) on.readObject();
//                System.out.println("page "+loadedPage.getPageID());
//                for(int i=0;i<loadedPage.getPageTuples().size();i++){
//                    System.out.println(loadedPage.getPageTuples().get(i).getTupleAttributes().toString());
//                }
//                System.out.println("-------------------");
                on.close();
                file.close();
                return loadedPage;
            }
            catch (EOFException e) {
                System.out.println("error:"+e.getMessage());
            }
        }
        return null;
    }

    public static Table loadTable(String dirPath,String fileName) throws IOException, ClassNotFoundException {
        FileInputStream file = new FileInputStream(dirPath + fileName + ".class");
        if(file.available()!=0){
            ObjectInputStream on = new ObjectInputStream(file);
            //System.out.println(((Vector<Tuple>) on.readObject()).get(0).getClusteringKey());
            try {
                Table loadedTable = (Table) on.readObject();
                Vector<String> octrees = FileManipulation.loadFilesFromDirectory("src/main/resources/data/indices/"+loadedTable.getTableName()+"/");
                loadedTable.setOctrees(octrees);
                return loadedTable;
            }
            catch (EOFException e) {
                System.out.println("error:"+e.getMessage());
            }
        }
        return null;
    }

    public static Octree loadOctree(String dirPath, String fileName) throws IOException, ClassNotFoundException {
        FileInputStream file = new FileInputStream(dirPath + fileName + ".class");
        if(file.available()!=0){
            ObjectInputStream on = new ObjectInputStream(file);
            //System.out.println(((Vector<Tuple>) on.readObject()).get(0).getClusteringKey());
            try {
                Octree loadedOctree = (Octree) on.readObject();
                return loadedOctree;
            }
            catch (EOFException e) {
                System.out.println("error:"+e.getMessage());
            }
        }
        return null;
    }

    public static Vector<String> loadFilesFromDirectory(String dirpath) {
        Vector<String> names = new Vector<String>();
        File dir = new File(dirpath);
        File[] directoryListing = dir.listFiles();

        if (directoryListing != null) {
            for (File child : directoryListing) {
                String name = child.getName().replaceAll(".class","");
//                System.out.println(name);
                names.add(name);
            }
        }
        return names;
    }

    public static int readFromConfig(String cfgPath){
        Properties prop = new Properties();
        // "src/resources/java.DBApp.config"
        String fileName = "src/main/resources/DBApp.config";
        try{
            FileInputStream fis = new FileInputStream(fileName);
            prop.load(fis);
            fis.close();
        }
        catch(FileNotFoundException ex){
            System.out.println("ex:"+ex);
        }
        catch(IOException io){
            System.out.println("io: "+io);
        }
//        int value = Integer.parseInt(prop.getProperty(cfgPath));

//        System.out.println("value"+Integer.parseInt(prop.getProperty(cfgPath)));
        return Integer.parseInt(prop.getProperty(cfgPath));
    }


}
