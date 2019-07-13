package com;

import com.misc.C;

import javax.swing.*;

public class Main extends JFrame {

    public Main() {
        int size = 10;
        setSize((size * C.TS) + 1, (size * C.TS) + 1);
        setDefaultCloseOperation(3);
        setLocationRelativeTo(null);

        MCanvas mCanvas = new MCanvas();
        add(mCanvas);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}
