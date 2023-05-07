package index;


import mainClasses.DBApp;
import mainClasses.DBAppException;
import mainClasses.FileManipulation;
import mainClasses.SimulatingNull;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

public class Octree {
    private Cube cube;
    private String strColWidth, strColLength, strColHeight;
    private int maxEntriesPerCube;
    private boolean isDivided;
    private Vector<Point> points;
    private Octree firstOctant, secondOctant, thirdOctant, fourthOctant, fifthOctant, sixthOctant, seventhOctant, eighthOctant;
    private String filepath;
    private String name;

    public Octree(Object minWidth, Object maxWidth,
                  Object minLength, Object maxLength, Object minHeight, Object maxHeight,
                  int maxEntriesPerCube, String strColWidth, String strColLength,
                  String strColHeight, String name) throws ParseException {
        this.cube = new Cube(minWidth,maxWidth,minLength,maxLength,minHeight,maxHeight);
        this.maxEntriesPerCube = maxEntriesPerCube;
        this.isDivided = false;
        this.points = new Vector<Point>();
        this.strColWidth = strColWidth;
        this.strColLength = strColLength;
        this.strColHeight = strColHeight;
        this.filepath = "src/main/resources/data/indices/"+name+"_octree.class";
    }
    public int containsPoint(Point p){
        for(int i=0;i<points.size();i++){
            Point currPoint = points.get(i);
            if(p.isEqual(currPoint)){
                return i;
            }
        }
        return -1;
    }
    public void checkInsertedValues(Object valOfCol1, Object valOfCol2, Object valOfCol3) throws DBAppException {
        boolean isValOfColSimulatingNull1 = valOfCol1 instanceof SimulatingNull;
        boolean isValOfColSimulatingNull2 = valOfCol2 instanceof SimulatingNull;
        boolean isValOfColSimulatingNull3 = valOfCol3 instanceof SimulatingNull;
        if(valOfCol1==null || valOfCol2==null || valOfCol3==null ||
                isValOfColSimulatingNull1 || isValOfColSimulatingNull2 || isValOfColSimulatingNull3) {
            throw new DBAppException("one of the values inserted in octree is null");
        }
    }
    public Octree searchForOctree(Point p){
        if(!isDivided){
            if(this.cube.pointInRange(p)){
                return this;
            }else {
                return null;
            }
        }
        if(firstOctant.cube.pointInRange(p)){
            return firstOctant.searchForOctree(p);
        }
        else if(secondOctant.cube.pointInRange(p)){
            return secondOctant.searchForOctree(p);
        }
        else if(thirdOctant.cube.pointInRange(p)){
            return thirdOctant.searchForOctree(p);
        }
        else if(fourthOctant.cube.pointInRange(p)){
            return fourthOctant.searchForOctree(p);
        }
        else if(fifthOctant.cube.pointInRange(p)){
            return fifthOctant.searchForOctree(p);
        }
        else if(sixthOctant.cube.pointInRange(p)){
            return sixthOctant.searchForOctree(p);
        }
        else if(seventhOctant.cube.pointInRange(p)){
            return seventhOctant.searchForOctree(p);
        }
        return eighthOctant.searchForOctree(p);
    }
    public void saveIntoOctreeFilepath() throws IOException {
        FileManipulation.saveIntoFilepath(this,this.getFilepath());
    }
    public boolean insertIntoOctree(Object valOfCol1, Object valOfCol2, Object valOfCol3, String ref) throws DBAppException, ParseException, IOException {
        checkInsertedValues(valOfCol1,valOfCol2,valOfCol3);
        Point insertedPoint = new Point(valOfCol1,valOfCol2,valOfCol3, ref);
        Octree octreeToBeInsertedIn = searchForOctree(insertedPoint);
        if(octreeToBeInsertedIn==null) return false;
//        System.out.println(octreeToBeInsertedIn.cube);
        int indexOfPoint = octreeToBeInsertedIn.containsPoint(insertedPoint);
//        System.out.println(indexOfPoint);
        if(indexOfPoint!=-1){
            Point currPoint = octreeToBeInsertedIn.points.get(indexOfPoint);
            currPoint.insertDups(insertedPoint);
            this.saveIntoOctreeFilepath();
            return true;
        }else if(octreeToBeInsertedIn.points.size()<octreeToBeInsertedIn.maxEntriesPerCube){
            octreeToBeInsertedIn.points.add(insertedPoint);
            this.saveIntoOctreeFilepath();
            return true;
        }else{
            octreeToBeInsertedIn.divide();
        }
        if(octreeToBeInsertedIn.firstOctant.insertIntoOctree(valOfCol1,  valOfCol2,  valOfCol3, ref)){
            return true;
        }else if(octreeToBeInsertedIn.secondOctant.insertIntoOctree( valOfCol1,  valOfCol2,  valOfCol3, ref)){
            return true;
        }else if(octreeToBeInsertedIn.thirdOctant.insertIntoOctree( valOfCol1,  valOfCol2,  valOfCol3, ref)){
            return true;
        }else if(octreeToBeInsertedIn.fourthOctant.insertIntoOctree( valOfCol1,  valOfCol2,  valOfCol3, ref)){
            return true;
        }else if(octreeToBeInsertedIn.fifthOctant.insertIntoOctree( valOfCol1,  valOfCol2,  valOfCol3, ref)){
            return true;
        }else if(octreeToBeInsertedIn.sixthOctant.insertIntoOctree( valOfCol1,  valOfCol2,  valOfCol3, ref)){
            return true;
        }else if(octreeToBeInsertedIn.seventhOctant.insertIntoOctree( valOfCol1,  valOfCol2,  valOfCol3, ref)){
            return true;
        }else {
            return octreeToBeInsertedIn.eighthOctant.insertIntoOctree(valOfCol1, valOfCol2, valOfCol3, ref);
        }
    }

    public void deleteFromOctree(Object valOfCol1, Object valOfCol2, Object valOfCol3, Hashtable<String, Object> htblColNameValue) throws DBAppException , IOException, ClassNotFoundException {
        checkInsertedValues(valOfCol1,valOfCol2,valOfCol3);
        Point tobeDeletedPoint = new Point(valOfCol1,valOfCol2,valOfCol3, null);
        Octree octreeToBeDeletedFrom = searchForOctree(tobeDeletedPoint);
        int indexOfPoint = octreeToBeDeletedFrom.containsPoint(tobeDeletedPoint);
        if(indexOfPoint==-1){
            // I think no need for exception just do nothing
            return;
//            throw new DBAppException("point to be deleted is not in the Octree");
        }
        else{
            octreeToBeDeletedFrom.points.get(indexOfPoint).removeDataWithOctree(htblColNameValue);
            octreeToBeDeletedFrom.points.remove(indexOfPoint);
        }
        this.saveIntoOctreeFilepath();
    }

    public void updateInOctree(Object oldValOfCol1, Object oldValOfCol2, Object oldValOfCol3, Hashtable<String, Object> htblColNameValue,String ref) throws DBAppException, IOException, ClassNotFoundException, ParseException {
        checkInsertedValues(oldValOfCol1,oldValOfCol2,oldValOfCol3);
        Point tobeUpdatedPoint = new Point(oldValOfCol1,oldValOfCol2,oldValOfCol3, null);
        Octree octreeToBeUpdated = searchForOctree(tobeUpdatedPoint);
        int indexOfPoint = octreeToBeUpdated.containsPoint(tobeUpdatedPoint);
        if(indexOfPoint==-1){
            // I think no need for exception just do nothing
            return;
//            throw new DBAppException("point to be deleted is not in the Octree");
        }
        else{
            Point updatePoint = octreeToBeUpdated.points.get(indexOfPoint);
            updatePoint.getReferences().remove(ref);
            if(updatePoint.getReferences().size()==0){
                octreeToBeUpdated.points.remove(indexOfPoint);
            }
            Object newWidth = htblColNameValue.get(this.strColWidth);
            Object newLength = htblColNameValue.get(this.strColLength);
            Object newHeight = htblColNameValue.get(this.strColHeight);
            this.insertIntoOctree(newWidth,newLength,newHeight,ref);
            // may cause an error
//            this.saveIntoOctreeFilepath();
        }
    }
//    public void updateFromOctree(String strTableName,String strClusteringKeyValue,Object valOfCol1, Object valOfCol2, Object valOfCol3, Hashtable<String, Object> htblColNameValue, String ref) throws Exception {
//        checkInsertedValues(valOfCol1,valOfCol2,valOfCol3);
//        Point tobeUpdatedPoint = new Point(valOfCol1,valOfCol2,valOfCol3, ref);
//        Octree octreeToBeDeletedFrom = searchForOctree(tobeUpdatedPoint);
//        int indexOfPoint = octreeToBeDeletedFrom.containsPoint(tobeUpdatedPoint);
//        if(indexOfPoint==-1){
//            throw new DBAppException("point to be Updated is not in the Octree");
//        }
//        else {
//            String clusterKeyDataType = Metadata.getClusterKeyDataType(strTableName);
//            Object correctValType = Column.adjustDataType(strClusteringKeyValue,clusterKeyDataType);
//            String clusteringKey= Metadata.getTableClusteringKey(strTableName);
//            htblColNameValue.put(clusteringKey,correctValType);
//            octreeToBeDeletedFrom.points.get(indexOfPoint).updateDataWithOctree(strClusteringKeyValue,htblColNameValue,clusterKeyDataType);
//        }
//    }
    public void divide() throws DBAppException, ParseException, IOException {
        // calculate boundaries of each octant
        this.isDivided = true;
        Point center = this.cube.getCenter();
        this.firstOctant = new Octree(this.cube.getMinWidth() , center.getWidth(), this.cube.getMinLength() , center.getLength() , this.cube.getMinHeight() , center.getHeight() , this.maxEntriesPerCube,this.strColWidth,this.strColLength,this.strColHeight, this.name);
        this.secondOctant = new Octree(center.getWidth(), this.cube.getMaxWidth() ,this.cube.getMinLength() , center.getLength() , this.cube.getMinHeight() , center.getHeight() , this.maxEntriesPerCube,this.strColWidth,this.strColLength,this.strColHeight, this.name);
        this.thirdOctant = new Octree(this.cube.getMinWidth() , center.getWidth(), center.getLength() , this.cube.getMaxLength() , this.cube.getMinHeight() , center.getHeight() , this.maxEntriesPerCube,this.strColWidth,this.strColLength,this.strColHeight, this.name);
        this.fourthOctant = new Octree(center.getWidth(), this.cube.getMaxWidth(), center.getLength() , this.cube.getMaxLength() , this.cube.getMinHeight() , center.getHeight() , this.maxEntriesPerCube,this.strColWidth,this.strColLength,this.strColHeight, this.name);
        this.fifthOctant = new Octree(this.cube.getMinWidth() , center.getWidth(), this.cube.getMinLength() , center.getLength() , center.getHeight() , this.cube.getMaxHeight() ,this.maxEntriesPerCube,this.strColWidth,this.strColLength,this.strColHeight, this.name);
        this.sixthOctant = new Octree(center.getWidth(), this.cube.getMaxWidth(), this.cube.getMinLength() , center.getLength() , center.getHeight() , this.cube.getMaxHeight() , this.maxEntriesPerCube,this.strColWidth,this.strColLength,this.strColHeight, this.name);
        this.seventhOctant = new Octree(this.cube.getMinWidth() , center.getWidth(), center.getLength() , this.cube.getMaxLength() , center.getHeight() , this.cube.getMaxHeight() , this.maxEntriesPerCube,this.strColWidth,this.strColLength,this.strColHeight, this.name);
        this.eighthOctant = new Octree(center.getWidth(), this.cube.getMaxWidth(), center.getLength() , this.cube.getMaxLength() , center.getHeight() , this.cube.getMaxHeight() , this.maxEntriesPerCube,this.strColWidth,this.strColLength,this.strColHeight, this.name);
        for(int i=0;i<points.size();i++){
            Object valOfCol1 = points.get(i).getWidth();
            Object valOfCol2 = points.get(i).getLength();
            Object valOfCol3 = points.get(i).getHeight();
            String ref = points.get(i).getPageName();
            boolean temp = firstOctant.insertIntoOctree(valOfCol1,  valOfCol2,  valOfCol3, ref)
            || secondOctant.insertIntoOctree(valOfCol1,  valOfCol2,  valOfCol3, ref)
            || thirdOctant.insertIntoOctree(valOfCol1,  valOfCol2,  valOfCol3, ref)
            || fourthOctant.insertIntoOctree(valOfCol1,  valOfCol2,  valOfCol3, ref)
            || fifthOctant.insertIntoOctree(valOfCol1,  valOfCol2,  valOfCol3, ref)
            || sixthOctant.insertIntoOctree(valOfCol1,  valOfCol2,  valOfCol3, ref)
            || seventhOctant.insertIntoOctree(valOfCol1,  valOfCol2,  valOfCol3, ref)
            || eighthOctant.insertIntoOctree(valOfCol1, valOfCol2, valOfCol3, ref);
        }
        this.points.clear();
    }

    public String getFilepath() {
        return filepath;
    }

    public String getName() {
        return name;
    }
    public String getStrColWidth() {
        return strColWidth;
    }

    public String getStrColLength() {
        return strColLength;
    }

    public String getStrColHeight() {
        return strColHeight;
    }
    public int getMaxEntriesPerCube() {
        return maxEntriesPerCube;
    }

    public void setMaxEntriesPerCube(int maxEntriesPerCube) {
        this.maxEntriesPerCube = maxEntriesPerCube;
    }

    public boolean isDivided() {
        return isDivided;
    }

    public void setDivided(boolean divided) {
        isDivided = divided;
    }

    public Vector<Point> getPoints() {
        return points;
    }

    public void setPoints(Vector<Point> points) {
        this.points = points;
    }

    public Octree getFirstOctant() {
        return firstOctant;
    }

    public void setFirstOctant(Octree firstOctant) {
        this.firstOctant = firstOctant;
    }

    public Octree getSecondOctant() {
        return secondOctant;
    }

    public void setSecondOctant(Octree secondOctant) {
        this.secondOctant = secondOctant;
    }

    public Octree getThirdOctant() {
        return thirdOctant;
    }

    public void setThirdOctant(Octree thirdOctant) {
        this.thirdOctant = thirdOctant;
    }

    public Octree getFourthOctant() {
        return fourthOctant;
    }

    public void setFourthOctant(Octree fourthOctant) {
        this.fourthOctant = fourthOctant;
    }

    public Octree getFifthOctant() {
        return fifthOctant;
    }

    public void setFifthOctant(Octree fifthOctant) {
        this.fifthOctant = fifthOctant;
    }

    public Octree getSixthOctant() {
        return sixthOctant;
    }

    public void setSixthOctant(Octree sixthOctant) {
        this.sixthOctant = sixthOctant;
    }

    public Octree getSeventhOctant() {
        return seventhOctant;
    }

    public void setSeventhOctant(Octree seventhOctant) {
        this.seventhOctant = seventhOctant;
    }

    public Octree getEighthOctant() {
        return eighthOctant;
    }

    public void setEighthOctant(Octree eighthOctant) {
        this.eighthOctant = eighthOctant;
    }

    public Cube getCube() {
        return cube;
    }

    public void setCube(Cube root) {
        this.cube = root;
    }

    @Override
    public String toString() {
        String s = "";
        Queue<Octree> q = new LinkedList<>();
        q.add(this);
        while(!q.isEmpty()){
            int currSize = q.size();
            for(int i=0;i<currSize;i++){
                Octree currOctree = q.remove();
                Object minWidth = currOctree.cube.getMinWidth();
                Object maxWidth = currOctree.cube.getMaxWidth();
                Object minHeight = currOctree.cube.getMinHeight();
                Object maxHeight = currOctree.cube.getMaxHeight();
                Object minLength = currOctree.cube.getMinLength();
                Object maxLength = currOctree.cube.getMaxLength();
                s+= "{mw:"+minWidth+",xw:"+maxWidth+",ml:"+minLength+",xl:"+maxLength+",mh:"+minHeight+",xh:"+maxHeight;
                if(currOctree.isDivided){
                    s+=",Node}-----";
                    q.add(currOctree.firstOctant);
                    q.add(currOctree.secondOctant);
                    q.add(currOctree.thirdOctant);
                    q.add(currOctree.fourthOctant);
                    q.add(currOctree.fifthOctant);
                    q.add(currOctree.sixthOctant);
                    q.add(currOctree.seventhOctant);
                    q.add(currOctree.eighthOctant);
                }else {
                    if(currOctree.points.size()==0){
                        s+="}-----";
                    }else{
                        s+=",";
                        for(int j=0;j<currOctree.points.size();j++){
                            s+=currOctree.points.get(j)+",";
                        }
                        s+="}";
                    }

                }
            }
            s+="\n";
        }
        return s;
    }

    public static void main(String[] args) throws ParseException, DBAppException, IOException {
        Octree octree = new Octree(0,100,0,100,0,100,3,"","","", "");
        octree.insertIntoOctree(10,20,20,null);
        octree.insertIntoOctree(12,20,30,null);
        octree.insertIntoOctree(5,10,20,null);
        octree.insertIntoOctree(6,8,20,"1");
        octree.insertIntoOctree(6,8,20,"2");
        octree.insertIntoOctree(60,8,20,"3");
        System.out.println(octree);
        DBApp dbApp = new DBApp();
        dbApp.init();
//        octree.deleteFromOctree(6,8,20);
//        System.out.println(octree);
    }
}
