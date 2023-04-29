package index;


import main.java.DBAppException;

import java.io.IOException;
import java.text.ParseException;
import java.util.Hashtable;
import java.util.Vector;

public class Octree {
    private Cube cube;
//    private String col1, col2, col3;
    private int maxEntriesPerCube;
    private boolean isDivided;
    private Vector<Point> points;
    private Octree firstOctant, secondOctant, thirdOctant, fourthOctant, fifthOctant, sixthOctant, seventhOctant, eighthOctant;
    public Octree(Object minWidth, Object maxWidth,
                  Object minLength, Object maxLength, Object minHeight, Object maxHeight, int maxEntriesPerCube) throws ParseException {
        this.cube = new Cube(minWidth,maxWidth,minLength,maxLength,minHeight,maxHeight);
        this.maxEntriesPerCube = maxEntriesPerCube;
        this.isDivided = false;
        this.points = new Vector<Point>();
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
        if(valOfCol1==null || valOfCol2==null || valOfCol3==null) {
            throw new DBAppException("one of the values inserted in octree is null");
        }
    }
    public Octree searchForOctree(Point p){
        if(!isDivided){
            return this;
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
    public boolean insertIntoOctree(Object valOfCol1, Object valOfCol2, Object valOfCol3, String ref) throws DBAppException, ParseException {
        checkInsertedValues(valOfCol1,valOfCol2,valOfCol3);
        Point insertedPoint = new Point(valOfCol1,valOfCol2,valOfCol3, ref);
        Octree octreeToBeInsertedIn = searchForOctree(insertedPoint);
        int indexOfPoint = octreeToBeInsertedIn.containsPoint(insertedPoint);
        if(indexOfPoint!=-1){
            Point currPoint = octreeToBeInsertedIn.points.get(indexOfPoint);
            currPoint.insertDups(insertedPoint);
            return true;
        }else if(octreeToBeInsertedIn.points.size()<octreeToBeInsertedIn.maxEntriesPerCube){
            octreeToBeInsertedIn.points.add(insertedPoint);
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
    public void deleteFromOctree(Object valOfCol1, Object valOfCol2, Object valOfCol3, Hashtable<String, Object> htblColNameValue, String ref) throws DBAppException , IOException, ClassNotFoundException {
        checkInsertedValues(valOfCol1,valOfCol2,valOfCol3);
        Point tobeDeletedPoint = new Point(valOfCol1,valOfCol2,valOfCol3, ref);
        Octree octreeToBeDeletedFrom = searchForOctree(tobeDeletedPoint);
        int indexOfPoint = octreeToBeDeletedFrom.containsPoint(tobeDeletedPoint);
        if(indexOfPoint==-1){
            throw new DBAppException("point to be deleted is not in the Octree");
        }
        else{
            octreeToBeDeletedFrom.points.get(indexOfPoint).removeDataWithOctree(htblColNameValue);
        }
    }
    public void divide() throws DBAppException, ParseException {
        // calculate boundaries of each octant
        this.isDivided = true;
        Point center = this.cube.getCenter();
        this.firstOctant = new Octree(this.cube.getMinWidth() , center.getWidth(), this.cube.getMinLength() , center.getLength() , this.cube.getMinHeight() , center.getHeight() , this.maxEntriesPerCube);
        this.secondOctant = new Octree(center.getWidth(), this.cube.getMaxWidth() ,this.cube.getMinLength() , center.getLength() , this.cube.getMinHeight() , center.getHeight() , this.maxEntriesPerCube);
        this.thirdOctant = new Octree(this.cube.getMinWidth() , center.getWidth(), center.getLength() , this.cube.getMaxLength() , this.cube.getMinHeight() , center.getHeight() , this.maxEntriesPerCube);
        this.fourthOctant = new Octree(center.getWidth(), this.cube.getMaxWidth(), center.getLength() , this.cube.getMaxLength() , this.cube.getMinHeight() , center.getHeight() , this.maxEntriesPerCube);
        this.fifthOctant = new Octree(this.cube.getMinWidth() , center.getWidth(), this.cube.getMinLength() , center.getLength() , center.getHeight() , this.cube.getMaxHeight() ,this.maxEntriesPerCube);
        this.sixthOctant = new Octree(center.getWidth(), this.cube.getMaxWidth(), this.cube.getMinLength() , center.getLength() , center.getHeight() , this.cube.getMaxHeight() , this.maxEntriesPerCube);
        this.seventhOctant = new Octree(this.cube.getMinWidth() , center.getWidth(), center.getLength() , this.cube.getMaxLength() , center.getHeight() , this.cube.getMaxHeight() , this.maxEntriesPerCube);
        this.eighthOctant = new Octree(center.getWidth(), this.cube.getMaxWidth(), center.getLength() , this.cube.getMaxLength() , center.getHeight() , this.cube.getMaxHeight() , this.maxEntriesPerCube);
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
}
