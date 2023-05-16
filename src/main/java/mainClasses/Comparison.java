package mainClasses;

import java.util.Date;

public abstract class Comparison {
    public static int compareTo(Object o1, Object o2, String type) {
        // return 0 if equal , returns -ve if o1 object is less than o2 else +ve
        if((o1==null && o2==null)||(o1 instanceof DBAppNull && o2 instanceof DBAppNull)||
                (o1==null && o2 instanceof DBAppNull)|| (o1 instanceof DBAppNull && o2==null)) {
            return 0;
        }
        else if(o1==null||o2==null||o1 instanceof DBAppNull || o2 instanceof DBAppNull){
            return -1;
        }
        if(type==null){
            String type1 = o1.getClass().getName();
            String type2 = o2.getClass().getName();
            type = o1!=null?type1:type2;
            if(o1 instanceof Integer && o2 instanceof Double){
                type = "java.lang.Double";
            }
        }
        if (type.equalsIgnoreCase("java.lang.Integer")) {
            return ((Integer) o1).compareTo((Integer) o2);
        } else if (type.equalsIgnoreCase("java.lang.String")) {
            return (((String) o1).toLowerCase()).compareTo(((String) o2).toLowerCase());
        } else if (type.equalsIgnoreCase("java.lang.Double")) {
            o1 = Double.parseDouble(o1+"");
            o2 = Double.parseDouble(o2+"");
            return ((Double) o1).compareTo((Double) o2);
        } else if (type.equalsIgnoreCase("java.util.Date")) {
            return ((Date) o1).compareTo((Date) o2);
        } else {
            return 0;
        }
    }
    public static int compareToOctree(Object o1, Object o2, String type) {
        // return 0 if equal , returns -ve if o1 object is less than o2 else +ve

        if(o1==null || o1 instanceof DBAppNull) {
            return 0;
        }
        if(o2==null || o2 instanceof DBAppNull) {
            return -1;
        }
        if(type==null){
            type = o1.getClass().getName();
        }

        if (type.equalsIgnoreCase("java.lang.Integer")) {
            return ((Integer) o1).compareTo((Integer) o2);
        } else if (type.equalsIgnoreCase("java.lang.String")) {
            return (((String) o1).toLowerCase()).compareTo(((String) o2).toLowerCase());
        } else if (type.equalsIgnoreCase("java.lang.Double")) {
            o1 = Double.parseDouble(o1+"");
            o2 = Double.parseDouble(o2+"");
            return ((Double) o1).compareTo((Double) o2);
        } else if (type.equalsIgnoreCase("java.util.Date")) {
            return ((Date) o1).compareTo((Date) o2);
        } else {
            return 0;
        }
    }
}
