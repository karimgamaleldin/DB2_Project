package helpers;

import tableAndCo.Page;
import tableAndCo.Table;

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
            System.out.println(e.getMessage());
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
        if (dir.mkdir() == true) {
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
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
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
                return loadedPage;
            }
            catch (EOFException e) {
                System.out.println("error:"+e.getMessage());
            }
        }
        return null;
    }

    public static Table loadTable(String filepath) throws IOException, ClassNotFoundException {
        FileInputStream file = new FileInputStream(filepath);
        if(file.available()!=0){
            ObjectInputStream on = new ObjectInputStream(file);
            //System.out.println(((Vector<Tuple>) on.readObject()).get(0).getClusteringKey());
            try {
                Table loadedTable = (Table) on.readObject();
                return loadedTable;
            }
            catch (EOFException e) {
                System.out.println("error:"+e.getMessage());
            }
        }
        return null;
    }

    public static void loadFilesFromDirectory(String dirpath, Vector<String> names) {
        File dir = new File(dirpath);
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                names.add(dirpath+child.getName());
            }
        }
    }

    public static int readFromConfig(String cfgPath){
        Properties prop = new Properties();
        String fileName = "src/resources/DBApp.config";
        try{
            FileInputStream fis = new FileInputStream(fileName);
            prop.load(fis);

        }
        catch(FileNotFoundException ex){
            System.out.println(ex);
        }
        catch(IOException io){
            System.out.println(io);
        }
        return Integer.parseInt(prop.getProperty(cfgPath));
    }
}
