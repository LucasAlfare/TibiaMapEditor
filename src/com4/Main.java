package com4;

import com2.C;
import parsers.Spr;

import javax.swing.*;
import java.io.IOException;

public class Main extends JFrame {

    public Main() throws IOException {
        int s = 20;
        setSize((s * C.TS) + 1, (s * C.TS) + 1);
        setDefaultCloseOperation(3);
        setLocationRelativeTo(null);

        MCanvas mCanvas = new MCanvas(new Core(s, new Spr(C.SPR_PATH)));
        add(mCanvas);

        setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        new Main();
    }
}
