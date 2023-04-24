package index;


import exceptions.DBAppException;
import helpers.Comparison;

import java.util.Hashtable;
import java.util.Vector;

public class Octree {
    private Cube root;
//    private String col1, col2, col3;
    private int maxEntriesPerCube;
    private boolean isDivided;
    private Vector<Point> points;
    private Octree firstOctant, secondOctant, thirdOctant, fourthOctant, fifthOctant, sixthOctant, seventhOctant, eightOctant;
    public Octree(Object minWidth, Object maxWidth,
                  Object minLength, Object maxLength, Object minHeight, Object maxHeight, int maxEntriesPerCube){
        this.root = new Cube(minWidth,maxWidth,minLength,maxLength,minHeight,maxHeight);;
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
    public boolean insertIntoOctree(Object valOfCol1, Object valOfCol2, Object valOfCol3, String ref){
        Point insertedPoint = new Point(valOfCol1,valOfCol2,valOfCol3, ref);
        if(!root.pointInRange(insertedPoint)){
            return false;
        }
        int indexOfPoint = this.containsPoint(insertedPoint);
        if(indexOfPoint!=-1){
            Point currPoint = this.points.get(indexOfPoint);
            currPoint.insertDups(insertedPoint);
            return true;
        }else if(points.size()<maxEntriesPerCube){
            points.add(insertedPoint);
            return true;
        }else{
            this.divide();
        }

        if(firstOctant.insertIntoOctree(valOfCol1,  valOfCol2,  valOfCol3, ref)){
            return true;
        }else if(secondOctant.insertIntoOctree( valOfCol1,  valOfCol2,  valOfCol3, ref)){
            return true;
        }else if(thirdOctant.insertIntoOctree( valOfCol1,  valOfCol2,  valOfCol3, ref)){
            return true;
        }else if(fourthOctant.insertIntoOctree( valOfCol1,  valOfCol2,  valOfCol3, ref)){
            return true;
        }else if(fifthOctant.insertIntoOctree( valOfCol1,  valOfCol2,  valOfCol3, ref)){
            return true;
        }else if(sixthOctant.insertIntoOctree( valOfCol1,  valOfCol2,  valOfCol3, ref)){
            return true;
        }else if(seventhOctant.insertIntoOctree( valOfCol1,  valOfCol2,  valOfCol3, ref)){
            return true;
        }else if(eightOctant.insertIntoOctree( valOfCol1,  valOfCol2,  valOfCol3, ref)){
            return true;
        }
        return false;
    }
    public void divide(){
        // calculate boundaries of each octant
        this.isDivided = true;
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

    public Octree getEightOctant() {
        return eightOctant;
    }

    public void setEightOctant(Octree eightOctant) {
        this.eightOctant = eightOctant;
    }

    public Cube getRoot() {
        return root;
    }

    public void setRoot(Cube root) {
        this.root = root;
    }
}
