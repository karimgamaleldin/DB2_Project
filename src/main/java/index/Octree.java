package index;


import mainClasses.*;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

public class Octree {
    private Cube cube;
    private String strColWidth, strColLength, strColHeight;
    private int maxEntriesPerCube;
    private boolean isDivided;
    private Vector<Point> points;
    private Octree firstOctant, secondOctant, thirdOctant, fourthOctant, fifthOctant, sixthOctant, seventhOctant, eighthOctant;
    private String filepath;
    private String name;
    private Vector<Point> overflow;

    public Octree(Object minWidth, Object maxWidth,
                  Object minLength, Object maxLength, Object minHeight, Object maxHeight,
                  int maxEntriesPerCube, String strColWidth, String strColLength,
                  String strColHeight, String name) throws ParseException {
        this.cube = new Cube(minWidth,maxWidth,minLength,maxLength,minHeight,maxHeight);
        this.maxEntriesPerCube = maxEntriesPerCube;
        this.isDivided = false;
        this.points = new Vector<Point>();
        this.overflow = new Vector<Point>();
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
    public int containsPointInOverflow(Point p){
        for(int i=0;i<overflow.size();i++){
            Point currPoint = overflow.get(i);
            if(p.isEqual(currPoint)){
                return i;
            }
        }
        return -1;
    }
    public boolean checkInsertedValues(Point p) throws DBAppException, IOException {
        boolean isValOfColSimulatingNull1 = p.getWidth() instanceof SimulatingNull;
        boolean isValOfColSimulatingNull2 = p.getLength() instanceof SimulatingNull;
        boolean isValOfColSimulatingNull3 = p.getHeight() instanceof SimulatingNull;
        if(p.getWidth()==null || p.getLength()==null || p.getHeight()==null ||
                isValOfColSimulatingNull1 || isValOfColSimulatingNull2 || isValOfColSimulatingNull3) {
            int indexOfPoint = this.containsPointInOverflow(p);
            if(indexOfPoint!=-1){
                Point currPoint = this.overflow.get(indexOfPoint);
                currPoint.insertDups(p);
//                this.saveIntoOctreeFilepath();
                return true;
            }else if(this.points.size()<this.maxEntriesPerCube){
                p.setParent(this);
                this.overflow.add(p);
//                this.saveIntoOctreeFilepath();
                return true;
            }
        }
        return false;
    }
    public void searchForOctree(Point p,Vector<Octree> octrees){
        if(!isDivided){
            if(this.cube.pointInRange(p)){
                octrees.add(this);
            }
            return;
        }
        if(firstOctant.cube.pointInRange(p)){
            firstOctant.searchForOctree(p,octrees);
        }
        if(secondOctant.cube.pointInRange(p)){
            secondOctant.searchForOctree(p,octrees);
        }
        if(thirdOctant.cube.pointInRange(p)){
            thirdOctant.searchForOctree(p,octrees);
        }
        if(fourthOctant.cube.pointInRange(p)){
            fourthOctant.searchForOctree(p,octrees);
        }
        if(fifthOctant.cube.pointInRange(p)){
            fifthOctant.searchForOctree(p,octrees);
        }
        if(sixthOctant.cube.pointInRange(p)){
            sixthOctant.searchForOctree(p,octrees);
        }
        if(seventhOctant.cube.pointInRange(p)){
            seventhOctant.searchForOctree(p,octrees);
        }
        if(eighthOctant.cube.pointInRange(p)){
            eighthOctant.searchForOctree(p,octrees);
        }
    }
    public void searchInOverflow(Point p, Vector<Point> pointsFromOverflow) throws DBAppException, IOException, ClassNotFoundException {
        for(int i=0;i<this.overflow.size();i++){
            if(p.isPartialEqual(overflow.get(i))){
                pointsFromOverflow.add(overflow.get(i));
            }
        }
    }
    public Vector<Point> search(Point p) throws DBAppException, IOException, ClassNotFoundException {
        Vector<Point> pts = new Vector<>();
        searchInOverflow(p,pts);
        Vector<Octree> octrees = new Vector<>();
        searchForOctree(p,octrees);
        for(int i=0;i<octrees.size();i++){
            Octree currOctree = octrees.get(i);
            currOctree.containsPartialPoint(p,pts);
        }
        return pts;
    }
    public void containsPartialPoint(Point p,Vector<Point> pts){
        for(int i=0;i<points.size();i++){
            Point currPoint = points.get(i);
            if(p.isPartialEqual(currPoint)){
                pts.add(currPoint);
            }
        }
    }
    public void saveIntoOctreeFilepath() throws IOException {
        FileManipulation.saveIntoFilepath(this,this.getFilepath());
    }
    public boolean insertIntoOctree(Point insertedPoint) throws DBAppException, ParseException, IOException {
        if(checkInsertedValues(insertedPoint)) {
            return true;
        }
        Vector<Octree> octrees = new Vector<>();
        searchForOctree(insertedPoint,octrees);
        if(octrees.size()==0) return false;
        Octree octreeToBeInsertedIn = octrees.get(0);
        if(octreeToBeInsertedIn==null) return false;
//        System.out.println(octreeToBeInsertedIn.cube);
        int indexOfPoint = octreeToBeInsertedIn.containsPoint(insertedPoint);
//        System.out.println(indexOfPoint);
        if(indexOfPoint!=-1){
            Point currPoint = octreeToBeInsertedIn.points.get(indexOfPoint);
//            System.out.println("-->" + currPoint.getReferences());
            currPoint.insertDups(insertedPoint);
//            System.out.println("-->" + currPoint.getReferences());
//            this.saveIntoOctreeFilepath();
            return true;
        }else if(octreeToBeInsertedIn.points.size()<octreeToBeInsertedIn.maxEntriesPerCube){
            insertedPoint.setParent(octreeToBeInsertedIn);
            octreeToBeInsertedIn.points.add(insertedPoint);
//            this.saveIntoOctreeFilepath();
            return true;
        }else{
            octreeToBeInsertedIn.divide();
        }
        if(octreeToBeInsertedIn.firstOctant.insertIntoOctree(insertedPoint)){
            return true;
        }else if(octreeToBeInsertedIn.secondOctant.insertIntoOctree( insertedPoint)){
            return true;
        }else if(octreeToBeInsertedIn.thirdOctant.insertIntoOctree( insertedPoint)){
            return true;
        }else if(octreeToBeInsertedIn.fourthOctant.insertIntoOctree( insertedPoint)){
            return true;
        }else if(octreeToBeInsertedIn.fifthOctant.insertIntoOctree( insertedPoint)){
            return true;
        }else if(octreeToBeInsertedIn.sixthOctant.insertIntoOctree( insertedPoint)){
            return true;
        }else if(octreeToBeInsertedIn.seventhOctant.insertIntoOctree( insertedPoint)){
            return true;
        }else {
            return octreeToBeInsertedIn.eighthOctant.insertIntoOctree(insertedPoint);
        }
    }

    public void deleteFromOctree(Object valOfCol1, Object valOfCol2, Object valOfCol3, Hashtable<String, Object> htblColNameValue) throws DBAppException , IOException, ClassNotFoundException {
        Point tobeDeletedPoint = new Point(valOfCol1,valOfCol2,valOfCol3, null);
        Vector<Point> pointsToBeDeleted = search(tobeDeletedPoint);
        for(int i=0;i<pointsToBeDeleted.size();i++){
            Point currPoint = pointsToBeDeleted.get(i);
            currPoint.removeDataWithOctree(htblColNameValue,this.strColWidth,this.strColLength,this.strColHeight);
            if(currPoint.getReferences().size()==0){
                Octree parent = currPoint.getParent();
                if(currPoint.checkNulls()){
                    parent.overflow.remove(currPoint);
                }else {
                    parent.points.remove(currPoint);
                }
            }
        }
//        checkInsertedValues(tobeDeletedPoint);
//        Vector<Octree> octrees = new Vector<>();
//        searchForOctree(tobeDeletedPoint,octrees);
////        searchInOverflow(tobeDeletedPoint,octrees);
//        for(int i=0;i<octrees.size();i++){
//            Octree octreeToBeDeletedFrom = octrees.get(i);
//            int indexOfPoint = octreeToBeDeletedFrom.containsPoint(tobeDeletedPoint);
//            if(indexOfPoint==-1){
//                // I think no need for exception just do nothing
//                continue;
//            }
//            else{
//                Point pointToBeDeleted = octreeToBeDeletedFrom.points.get(indexOfPoint);
//                pointToBeDeleted.removeDataWithOctree(htblColNameValue,this.strColWidth,this.strColLength,this.strColHeight);
//                if(pointToBeDeleted.getReferences().size()==0){
//                    octreeToBeDeletedFrom.points.remove(indexOfPoint);
//                }
//            }
//        }
//        Vector<Point> pointsFromOverflow = new Vector<>();
//        searchInOverflow(tobeDeletedPoint,pointsFromOverflow);
//        for(int i=0;i<pointsFromOverflow.size();i++){
//            Point pointToBeDeleted = pointsFromOverflow.get(i);
//            pointToBeDeleted.removeDataWithOctree(htblColNameValue,this.strColWidth,this.strColLength,this.strColHeight);
//        }
//        clearOverflow();
        this.saveIntoOctreeFilepath();
    }
    public boolean canBeUsed(HashMap<String , Object> hashMap){
        return hashMap.containsKey(strColHeight) && hashMap.containsKey(strColLength) && hashMap.containsKey(strColWidth);
    }
    public Point pointToBeSearchedFor(HashMap<String , Object> hashMap){
        return new Point(hashMap.getOrDefault(strColWidth , null) , hashMap.getOrDefault(strColLength , null) ,hashMap.getOrDefault(strColHeight , null) , null);
    }
    public boolean partOfIndex(String colName){
        return colName.equals(this.strColLength) || colName.equals(this.strColWidth) || colName.equals(this.strColHeight);
    }
    public void clearOverflow(){
        for(int i=0;i<this.overflow.size();i++){
            Point currPoint = this.overflow.get(i);
            if(currPoint.getReferences().size()==0){
                this.overflow.remove(i);
                i--;
            }
        }
    }
    public void updateInOctree(Object oldValOfCol1, Object oldValOfCol2, Object oldValOfCol3, Hashtable<String, Object> htblColNameValue,String ref) throws DBAppException, IOException, ClassNotFoundException, ParseException {
        Point oldPoint = new Point(oldValOfCol1,oldValOfCol2,oldValOfCol3, null);
        Vector<Point> pointsToBeUpdated = search(oldPoint);
        for(int i=0;i<pointsToBeUpdated.size();i++){
            Point currPoint = pointsToBeUpdated.get(i);
            if(oldPoint.isEqual(currPoint)){
                currPoint.getReferences().remove(ref);
                break;
            }
        }
        Object newWidth = htblColNameValue.get(this.strColWidth)==null?oldValOfCol1:htblColNameValue.get(this.strColWidth);
        Object newLength = htblColNameValue.get(this.strColLength)==null?oldValOfCol2:htblColNameValue.get(this.strColLength);
        Object newHeight = htblColNameValue.get(this.strColHeight)==null?oldValOfCol3:htblColNameValue.get(this.strColHeight);
        Point newPoint = new Point(newWidth,newLength,newHeight,ref);
        if(!oldPoint.isEqual(newPoint)){
            this.insertIntoOctree(newPoint);
        }
//        checkInsertedValues(tobeUpdatedPoint);
//        Vector<Octree> octrees = new Vector<>();
//        searchForOctree(tobeUpdatedPoint,octrees);
//        Octree octreeToBeUpdated = octrees.get(0);
//        int indexOfPoint = octreeToBeUpdated.containsPoint(tobeUpdatedPoint);
//        if(indexOfPoint==-1){
//            // I think no need for exception just do nothing
//            return;
////            throw new DBAppException("point to be deleted is not in the Octree");
//        }
//        else{
//            Point updatePoint = octreeToBeUpdated.points.get(indexOfPoint);
//            updatePoint.getReferences().remove(ref);
//            if(updatePoint.getReferences().size()==0){
//                octreeToBeUpdated.points.remove(indexOfPoint);
//            }
//            Object newWidth = htblColNameValue.get(this.strColWidth);
//            Object newLength = htblColNameValue.get(this.strColLength);
//            Object newHeight = htblColNameValue.get(this.strColHeight);
//            Point newPoint = new Point(newWidth,newLength,newHeight,ref);
//            this.insertIntoOctree(newPoint);
//            // may cause an error
////            this.saveIntoOctreeFilepath();
//        }
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
            Point insertedPoint = points.get(i);
            boolean temp = firstOctant.insertIntoOctree(insertedPoint)
            || secondOctant.insertIntoOctree(insertedPoint)
            || thirdOctant.insertIntoOctree(insertedPoint)
            || fourthOctant.insertIntoOctree(insertedPoint)
            || fifthOctant.insertIntoOctree(insertedPoint)
            || sixthOctant.insertIntoOctree(insertedPoint)
            || seventhOctant.insertIntoOctree(insertedPoint)
            || eighthOctant.insertIntoOctree(insertedPoint);
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
    public void printOctree(){
       printOctreeHelper(this,0,0,true);
    }
    private void printOctreeHelper(Octree octree,int child,int shift,boolean overflow){
        Object minWidth = octree.cube.getMinWidth();
        Object maxWidth = octree.cube.getMaxWidth();
        Object minHeight = octree.cube.getMinHeight();
        Object maxHeight = octree.cube.getMaxHeight();
        Object minLength = octree.cube.getMinLength();
        Object maxLength = octree.cube.getMaxLength();
        String s="";
        for(int i=0;i<shift;i++){
            s+=" ";
        }
        s += "child "+child+": {mw:"+minWidth+",xw:"+maxWidth+",ml:"+minLength+",xl:"+maxLength+",mh:"+minHeight+",xh:"+maxHeight+"--";
        if(overflow){
            s+=this.overflow;
        }
        if(!octree.isDivided){
            s+= octree.points;
            System.out.println(s);
            return;
        }
        System.out.println(s);
        printOctreeHelper(octree.firstOctant,1,shift+2,false);
        printOctreeHelper(octree.secondOctant,2,shift+2,false);
        printOctreeHelper(octree.thirdOctant,3,shift+2,false);
        printOctreeHelper(octree.fourthOctant,4,shift+2,false);
        printOctreeHelper(octree.fifthOctant,5,shift+2,false);
        printOctreeHelper(octree.sixthOctant,6,shift+2,false);
        printOctreeHelper(octree.seventhOctant,7,shift+2,false);
        printOctreeHelper(octree.eighthOctant,8,shift+2,false);
    }
    
    

    public static void main(String[] args) throws ParseException, DBAppException, IOException, ClassNotFoundException {
        Octree octree = new Octree(0,100,0,100,0,100,3,"","","", "");
        Point p1 = new Point(10,20,20,null);
        Point p2 = new Point(12,20,30,null);
        Point p3 = new Point(5,10,20,null);
        Point p4 = new Point(6,8,20,"1");
        Point p5 = new Point(6,8,20,"2");
        Point p6 = new Point(60,8,20,"3");
        Point p7 = new Point(60,8,null,"3");
        Point p8 = new Point(60,8,null,"2");
        Point p9 = new Point(60,null,null,"2");
        Point p10 = new Point(6,8,20,"3");
        Point p11 = new Point(10,20,20,null);
        Point p12 = new Point(10,20,20,null);
        Point p13 = new Point(24,24,24,"2");
        Point p14 = new Point(6,8,20,"4");
        Point p15 = new Point(6,8,20,"5");
        Point p16 = new Point(75,32,10,"2");
        Point p17 = new Point(75,30,20,"2");
        Point p18 = new Point(75,31,19,"2");
        Point p19 = new Point(75,32,18,"2");
        Point p20 = new Point(75,32,10,"2");

        octree.insertIntoOctree(p1);
        octree.insertIntoOctree(p2);
        octree.insertIntoOctree(p3);
        octree.insertIntoOctree(p4);
        octree.insertIntoOctree(p5);
        octree.insertIntoOctree(p6);
        octree.insertIntoOctree(p7);
        octree.insertIntoOctree(p8);
        octree.insertIntoOctree(p9);
        octree.insertIntoOctree(p10);
        octree.insertIntoOctree(p11);
        octree.insertIntoOctree(p12);
        octree.insertIntoOctree(p13);
        octree.insertIntoOctree(p14);
        octree.insertIntoOctree(p15);
        octree.insertIntoOctree(p16);
        octree.insertIntoOctree(p17);
        octree.insertIntoOctree(p18);
        octree.insertIntoOctree(p19);
        octree.insertIntoOctree(p20);
        DBApp dbApp = new DBApp();
        dbApp.init();
//        octree.deleteFromOctree(6,8,20);
        octree.printOctree();
        System.out.println(octree.search(new Point(null,null,20,null)));
    }
}
