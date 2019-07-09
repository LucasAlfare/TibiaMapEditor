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

        //mock simples pra testar
        int[][] c = new int[1000][1000];
        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j < c[i].length; j++) {
                c[i][j] = new Random().nextInt(5000);
            }
        }

        MCanvas mCanvas = new MCanvas(new Core(c, 800 / 32, new Spr(C.SPR_PATH)));
        add(mCanvas);

        setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        new Main();
    }
}
