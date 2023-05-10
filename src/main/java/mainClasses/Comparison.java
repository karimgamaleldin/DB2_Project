package mainClasses;

import java.util.Date;

public abstract class Comparison {
    public static int compareTo(Object o1, Object o2, String type) {
        // return 0 if equal , returns -ve if o1 object is less than o2 else +ve
        if((o1==null&&o2==null)||(o1 instanceof SimulatingNull && o2 instanceof SimulatingNull)) {
            return 0;
        }
        else if(o1==null||o2==null||o1 instanceof SimulatingNull || o2 instanceof SimulatingNull){
            return -1;
        }
        if(type==null){
            type = o1!=null?o1.getClass().getName():o2.getClass().getName();
        }
        if (type.equals("java.lang.Integer")) {
            return ((Integer) o1).compareTo((Integer) o2);
        } else if (type.equals("java.lang.String")) {
            return (((String) o1).toLowerCase()).compareTo(((String) o2).toLowerCase());
        } else if (type.equals("java.lang.Double")) {
            o1 = Double.parseDouble(o1+"");
            o2 = Double.parseDouble(o2+"");
            return ((Double) o1).compareTo((Double) o2);
        } else if (type.equals("java.util.Date")) {
            return ((Date) o1).compareTo((Date) o2);
        } else {
            return 0;
        }
    }
    public static int compareToOctree(Object o1, Object o2, String type) {
        // return 0 if equal , returns -ve if o1 object is less than o2 else +ve

        if(o1==null || o1 instanceof SimulatingNull) {
            return 0;
        }
        if(o2==null || o2 instanceof SimulatingNull) {
            return -1;
        }
        if(type==null){
            type = o1.getClass().getName();
        }

        if (type.equals("java.lang.Integer")) {
            return ((Integer) o1).compareTo((Integer) o2);
        } else if (type.equals("java.lang.String")) {
            return (((String) o1).toLowerCase()).compareTo(((String) o2).toLowerCase());
        } else if (type.equals("java.lang.Double")) {
            o1 = Double.parseDouble(o1+"");
            o2 = Double.parseDouble(o2+"");
            return ((Double) o1).compareTo((Double) o2);
        } else if (type.equals("java.util.Date")) {
            return ((Date) o1).compareTo((Date) o2);
        } else {
            return 0;
        }
    }
}
