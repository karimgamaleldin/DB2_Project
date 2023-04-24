package index;

import helpers.Comparison;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Vector;

public class Cube {
    private Point center;
//    private Object halfWidth, halfHeight, halfLength;
    private Object minWidth, maxWidth, minLength, maxLength, minHeight, maxHeight; //boundaries of cube
    public Cube(Object minWidth, Object maxWidth,
                Object minLength, Object maxLength, Object minHeight, Object maxHeight) throws ParseException {
        this.minWidth = minWidth;
        this.maxWidth = maxWidth;
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        Object centerX = this.getMiddleObject(minWidth,maxWidth);
        Object centerY = this.getMiddleObject(minLength,maxLength);
        Object centerZ = this.getMiddleObject(minHeight,maxHeight);
        this.center = new Point(centerX,centerY,centerZ, null);
    }
    public boolean pointInRange(Point p){
        boolean xInsideCube = Comparison.compareTo(p.getWidth(),minWidth,null)>=0&&Comparison.compareTo(p.getWidth(),maxWidth,null)<0;
        boolean yInsideCube = Comparison.compareTo(p.getLength(),minLength,null)>=0&&Comparison.compareTo(p.getLength(),maxLength,null)<0;
        boolean zInsideCube = Comparison.compareTo(p.getHeight(),minHeight,null)>=0&&Comparison.compareTo(p.getHeight(),maxHeight,null)<0;
        return xInsideCube && yInsideCube && zInsideCube;
    }

    public Object getMiddleObject(Object o1, Object o2) throws ParseException {
        String type = ((""+o1.getClass())
                .replaceAll("class",""))
                .replaceAll(" ","");
        if (type.equals("java.lang.Integer")) {
            return this.getMiddleInteger((Integer) o1,(Integer) o2);
        } else if (type.equals("java.lang.String")) {
            return this.getMiddleString(((String) o1).toLowerCase(),((String) o2).toLowerCase());
        } else if (type.equals("java.lang.Double")) {
            return this.getMiddleDouble((Double) o1,(Double) o2);
        } else if (type.equals("java.util.Date")) {
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
        Long diff = Math.abs(d1.getTime() - d2.getTime()) / 2;
        Date median = new SimpleDateFormat("yyyy-MM-dd").parse(diff.toString());
        return median;
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
