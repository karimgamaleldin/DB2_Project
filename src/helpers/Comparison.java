package helpers;

import tableAndCo.Tuple;

import java.util.Date;

public abstract class Comparison {
    public static int compareTo(Object o1, Object o2, String type) {
        // return 0 if equal , returns -ve if o1 object is less than o2 else +ve
        if (type.equals("java.lang.Integer")) {
            return ((Integer) o1).compareTo((Integer) o2);
        } else if (type.equals("java.lang.String")) {
            return ((String) o1).compareTo((String) o2);
        } else if (type.equals("java.lang.Double")) {
            return ((Double) o1).compareTo((Double) o2);
        } else if (type.equals("java.util.Date")) {
            return ((Date) o1).compareTo((Date) o2);
        } else {
            return 0;
        }
    }
}
