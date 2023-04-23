package index;


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
    public boolean insertIntoOctree(Object valOfCol1, Object valOfCol2, Object valOfCol3){
        Point insertedPoint = new Point(valOfCol1,valOfCol2,valOfCol3);
        if(!root.containsPoint(insertedPoint)){
            return false;
        }
        if(points.size()<maxEntriesPerCube){
            points.add(insertedPoint);
            return true;
        }else{
            this.divide();
            if(firstOctant.insertIntoOctree(valOfCol1,  valOfCol2,  valOfCol3)){
              return true;
            }else if(secondOctant.insertIntoOctree( valOfCol1,  valOfCol2,  valOfCol3)){
                return true;
            }else if(thirdOctant.insertIntoOctree( valOfCol1,  valOfCol2,  valOfCol3)){
                return true;
            }else if(fourthOctant.insertIntoOctree( valOfCol1,  valOfCol2,  valOfCol3)){
                return true;
            }else if(fifthOctant.insertIntoOctree( valOfCol1,  valOfCol2,  valOfCol3)){
                return true;
            }else if(sixthOctant.insertIntoOctree( valOfCol1,  valOfCol2,  valOfCol3)){
                return true;
            }else if(seventhOctant.insertIntoOctree( valOfCol1,  valOfCol2,  valOfCol3)){
                return true;
            }else if(eightOctant.insertIntoOctree( valOfCol1,  valOfCol2,  valOfCol3)){
                return true;
            }
        }
        return false;
    }
    public void divide(){
        // calculate boundaries of each octant
    }
    public Cube getRoot() {
        return root;
    }

    public void setRoot(Cube root) {
        this.root = root;
    }
}
