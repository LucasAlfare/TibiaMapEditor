package misc;

import java.text.SimpleDateFormat;
import java.util.Date;

public class D {

    /*
    "d" de debugar...
     */
    public static String d(Class origin, String msg) {
        StringBuilder sb = new StringBuilder();

        sb.append(new SimpleDateFormat("hh:mm:ss.SSS").format(new Date()));
        sb.append(": [").append(origin.getName()).append("]");
        sb.append(" ").append(msg);

        System.out.println(sb.toString());
        return sb.toString();
    }
}
