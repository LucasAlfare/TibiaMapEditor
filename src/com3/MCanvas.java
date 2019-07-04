package com3;

import com2.C;

import javax.swing.*;
import java.awt.*;

public class MCanvas extends JComponent {

    private Core core;
    private int[][] currentView;

    public MCanvas(Core core) {
        this.core = core;
        currentView = this.core.getCurrView();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < currentView.length * C.TS; i += C.TS) {
            for (int j = 0; j < currentView[i / C.TS].length * C.TS; j += C.TS) {
                try {
                    System.out.println("i: " + i / C.TS + ", j: " + j / C.TS);
                    g.drawImage(core.getSpriteImageFrom(i / C.TS, j / C.TS), i, j, this);
                } catch (ArrayIndexOutOfBoundsException e) {
                    //e.printStackTrace();
                    //pass
                }
            }
        }
    }
}
