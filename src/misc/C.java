package misc;

import parsers.Spr;

import java.awt.*;
import java.io.IOException;

public class C {

    /**
     * standard Element Size
     */
    public static final int TS = 32;

    public static final String SPR_PATH = "src/assets/tibia-8.6.spr";

    public static Spr SPR;

    static {
        try {
            SPR = new Spr(C.SPR_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static final Color COLOR_SELECTED_TILE = new Color(0, 255, 150, 125);
}
