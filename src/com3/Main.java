package com3;

import misc.C;
import parsers.Spr;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class Main extends JFrame {

    public Main() throws IOException {
        int s = 4;

        setSize((s + 1) * 32, (s + 1) * 32);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(3);

        ArrayList<ArrayList<Integer>> mapContent = new ArrayList<>();
        for (int i = 0; i < s; i++) {
            ArrayList<Integer> curr = new ArrayList<>();
            for (int j = 0; j < s; j++) {
                curr.add(1);
            }
            mapContent.add(curr);
        }

//        for (int i = 0; i < mapContent.size(); i++) {
//            for (int j = 0; j < mapContent.get(i).size(); j++) {
//                System.out.print(mapContent.get(i).get(j) + " ");
//            }

//            System.out.println();
//        }

        MCanvas mCanvas = new MCanvas(new Core(mapContent, s, new Spr(C.SPR_PATH)));
        add(mCanvas);

        setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        new Main();
    }
}
