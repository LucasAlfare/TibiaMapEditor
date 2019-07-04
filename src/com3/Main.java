package com3;

import parsers.Spr;

import javax.swing.*;
import java.io.IOException;

public class Main extends JFrame {

    public Main() throws IOException {
        setSize(800, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(3);

        Spr spr = new Spr("src/assets/tibia-8.6.spr");

        int[][] mc = {
                {1, 2, 3, 4, 5, 6},
                {7, 8, 9, 1, 2, 3},
                {1, 1, 1, 1, 1, 2},
                {1, 1, 1, 1, 1, 2},
                {1, 1, 1, 2, 1, 2}
        };

        Core c = new Core(mc, 2, spr);
        MCanvas mCanvas = new MCanvas(c);
        add(mCanvas);

        setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        new Main();
    }
}
