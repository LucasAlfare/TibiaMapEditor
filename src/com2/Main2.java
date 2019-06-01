package com2;

import javax.swing.*;
import java.io.IOException;

public class Main2 extends JFrame {

    public Main2() throws IOException {
        setSize(800, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(3);

        Canvaskk kk = new Canvaskk(5, 5);
        add(kk);
        setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        new Main2();
    }
}
