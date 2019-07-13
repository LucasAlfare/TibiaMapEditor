package com4;

import misc.C;

import javax.swing.*;

public class Main extends JFrame {

    public Main() {
        int s = 20;
        setSize((s * C.TS) + 1, (s * C.TS) + 1);
        setDefaultCloseOperation(3);
        setLocationRelativeTo(null);

        MCanvas mCanvas = new MCanvas(new Core(s, C.SPR));
        add(mCanvas);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}