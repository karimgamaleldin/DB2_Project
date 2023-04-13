package helpers;

import tableAndCo.Page;

import java.io.*;

public abstract class FileManipulation {
    public static void createSerFile(String filepath){
        try{
            File pageFile = new File(filepath);
            if (pageFile.createNewFile()) {
                System.out.println("File created: " + pageFile.getName());
            } else {
                System.out.println("File already exists.");
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
            System.out.println("Directory has been created successfully");
        }
        else {
            System.out.println("Directory cannot be created");
        }
    }


}
