package index;

import mainClasses.Comparison;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

public class Cube implements Serializable {
    private Point center;
//    private Object halfWidth, halfHeight, halfLength;
    private Object minWidth, maxWidth, minLength, maxLength, minHeight, maxHeight; //boundaries of cube
    private Vector<Object> widthRange, lengthRange, heightRange;
    public Cube(Object minWidth, Object maxWidth,
                Object minLength, Object maxLength, Object minHeight, Object maxHeight,
                Vector<Object> widthRange, Vector<Object> lengthRange, Vector<Object> heightRange
    ) throws ParseException {
        this.minWidth = minWidth;
        this.maxWidth = maxWidth;
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.widthRange = widthRange;
        this.lengthRange = lengthRange;
        this.heightRange = heightRange;
        Object centerX = this.getMiddleObject(minWidth,maxWidth);
        Object centerY = this.getMiddleObject(minLength,maxLength);
        Object centerZ = this.getMiddleObject(minHeight,maxHeight);
        this.center = new Point(centerX,centerY,centerZ, null);
    }
    public boolean pointInRange(Point p){
        boolean xInsideCube = Comparison.compareToOctree(p.getWidth(),minWidth,null)>=0&&Comparison.compareTo(p.getWidth(),maxWidth,null)<0;
        boolean yInsideCube = Comparison.compareToOctree(p.getLength(),minLength,null)>=0&&Comparison.compareTo(p.getLength(),maxLength,null)<0;
        boolean zInsideCube = Comparison.compareToOctree(p.getHeight(),minHeight,null)>=0&&Comparison.compareTo(p.getHeight(),maxHeight,null)<0;
        if(checkMax(p.getWidth(),widthRange.get(1))&&checkMax(p.getWidth(),maxWidth)) xInsideCube = true;
        if(checkMax(p.getLength(),lengthRange.get(1))&&checkMax(p.getLength(),maxLength)) yInsideCube = true;
        if(checkMax(p.getHeight(),heightRange.get(1))&&checkMax(p.getHeight(),maxHeight)) zInsideCube = true;
        return xInsideCube && yInsideCube && zInsideCube;
    }

    public boolean checkMax(Object pointMax, Object max) {
        return Comparison.compareTo(pointMax,max,null)==0;
    }
    public Object getMiddleObject(Object o1, Object o2) throws ParseException {
        String type = ((""+o1.getClass())
                .replaceAll("class",""))
                .replaceAll(" ","");
        if (type.equalsIgnoreCase("java.lang.Integer")) {
            return this.getMiddleInteger((Integer) o1,(Integer) o2);
        } else if (type.equalsIgnoreCase("java.lang.String")) {
            return this.getMiddleString(((String) o1).toLowerCase(),((String) o2).toLowerCase());
        } else if (type.equalsIgnoreCase("java.lang.Double")) {
            return this.getMiddleDouble((Double) o1,(Double) o2);
        } else if (type.equalsIgnoreCase("java.util.Date")) {
            return this.getMiddleDate((Date) o1,(Date) o2);
        } else {
            return null;
        }
    }
    public Integer getMiddleInteger(Integer a1, Integer a2) {
        return (a1+a2)/2;
    }
    public Double getMiddleDouble(Double a1, Double a2) {
        return (a1+a2)/2.0;
    }
    public Date getMiddleDate(Date d1, Date d2) throws ParseException {
//        Long diff = Math.abs(d1.getTime() - d2.getTime()) / 2;
//        Date median = new SimpleDateFormat("yyyy-MM-dd").parse(diff+"");
//        return median;
        // create Calendar instances for the two dates
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(d1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(d2);

        // calculate the middle date
        Calendar middleCal = Calendar.getInstance();
        middleCal.setTimeInMillis((cal1.getTimeInMillis() + cal2.getTimeInMillis()) / 2);

        // get the middle date as a Date object
        Date middleDate = middleCal.getTime();

//        System.out.println("The middle date between " + d1 + " and " + d2 + " is " + middleDate);
        return middleDate;
    }

    @Override
    public String toString() {
        return "Cube{" +
                "center=" + center +
                ", minWidth=" + minWidth +
                ", maxWidth=" + maxWidth +
                ", minLength=" + minLength +
                ", maxLength=" + maxLength +
                ", minHeight=" + minHeight +
                ", maxHeight=" + maxHeight +
                '}';
    }

    public String getMiddleString(String S, String T)
    {
        int N = Math.max(S.length(),T.length());
        String bigStr = "";
        String lessStr = "";
        if(S.length()>T.length()){
            bigStr = S;
            lessStr = T;
        }else {
            bigStr = T;
            lessStr = S;
        }
        for(int i=lessStr.length();i<bigStr.length();i++){
            lessStr+=bigStr.charAt(i);
        }
        // Stores the base 26 digits after addition
        int[] a1 = new int[N + 1];

        for (int i = 0; i < N; i++) {
            a1[i + 1] = (int)bigStr.charAt(i) - 97 + (int)lessStr.charAt(i) - 97;
        }
        // Iterate from right to left
        // and add carry to next position
        for (int i = N; i >= 1; i--) {
            a1[i - 1] += (int)a1[i] / 26;
            a1[i] %= 26;
        }
        // Reduce the number to find the middle
        // string by dividing each position by 2
        for (int i = 0; i <= N; i++) {
            // If current value is odd,
            // carry 26 to the next index value
            if ((a1[i] & 1) != 0) {
                if (i + 1 <= N) {
                    a1[i + 1] += 26;
                }
            }
            a1[i] = (int)a1[i] / 2;
        }
        String middleString = "";
        for (int i = 1; i <= N; i++) {
            middleString += (char)(a1[i] + 97);
        }
        return middleString;
    }
    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }
    public Object getMinWidth() {
        return minWidth;
    }

    public void setMinWidth(Object minWidth) {
        this.minWidth = minWidth;
    }

    public Object getMaxWidth() {
        return maxWidth;
    }

    public void setMaxWidth(Object maxWidth) {
        this.maxWidth = maxWidth;
    }

    public Object getMinLength() {
        return minLength;
    }

    public void setMinLength(Object minLength) {
        this.minLength = minLength;
    }

    public Object getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(Object maxLength) {
        this.maxLength = maxLength;
    }

    public Object getMinHeight() {
        return minHeight;
    }

    public void setMinHeight(Object minHeight) {
        this.minHeight = minHeight;
    }

    public Object getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(Object maxHeight) {
        this.maxHeight = maxHeight;
    }
}
