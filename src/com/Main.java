package com;

import javax.swing.*;

import static com.misc.G.ts;
import static com.misc.G.viewSize;

public class Main extends JFrame {

    public Main() {
        setSize(viewSize * ts, viewSize * ts);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(3);

        MCanvas c = new MCanvas();
        add(c);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}
