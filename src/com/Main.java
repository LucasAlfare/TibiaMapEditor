package com;

import com.misc.G;

import javax.swing.*;

public class Main extends JFrame {

    public Main() {
        int size = 20;
        setSize((size * G.TS) + 1, (size * G.TS) + 1);
        setDefaultCloseOperation(3);
        setLocationRelativeTo(null);

        MCanvas mCanvas = new MCanvas();
        add(mCanvas);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
//        int[][] arr = {
//                {0,1,2,0},
//                {4,5,6,0},
//                {7,8,9,0},
//                {9,9,9,0}
//        };
//
//        for (int i = 0; i < arr.length; i++) {
//            System.out.println(arr[arr.length - 1][i]);
//        }
//        System.out.println();
//        for (int i = 0; i < arr.length; i++) {
//            System.out.print(arr[i][arr.length - 1] + " ");
//        }
    }
}
