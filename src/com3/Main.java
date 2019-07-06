package com3;

import com2.C;
import parsers.Spr;

import javax.swing.*;
import java.io.IOException;
import java.util.Random;

public class Main extends JFrame {

    public Main() throws IOException {
        setSize(800, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(3);

        Spr spr = new Spr(C.SPR_PATH);

        int[][] mc = new int[100][100];
        Random r = new Random();
        for (int i = 0; i < mc.length; i++) {
            for (int j = 0; j < mc[i].length; j++) {
                mc[i][j] = r.nextInt(20);
            }
        }

        Core c = new Core(mc, 10, spr);
        MCanvas mCanvas = new MCanvas(c);
        add(mCanvas);

        setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        new Main();
    }
}
