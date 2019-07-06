package com3;

import com2.C;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MCanvas extends JComponent {

    private Core core;
    private int[][] currentView;

    public MCanvas(Core core) {
        this.core = core;
        currentView = this.core.getCurrView();
        this.setFocusable(true);

        System.out.println(this.core);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);

                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    System.out.println("Left");
                    MCanvas.this.core.setCurrView(--MCanvas.this.core.currX, MCanvas.this.core.currY);
                    System.out.println(MCanvas.this.core);
                    repaint();
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    System.out.println("Right");
                    MCanvas.this.core.setCurrView(++MCanvas.this.core.currX, MCanvas.this.core.currY);
                    System.out.println(MCanvas.this.core);
                    repaint();
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    System.out.println("UP");
                    MCanvas.this.core.setCurrView(MCanvas.this.core.currX, --MCanvas.this.core.currY);
                    System.out.println(MCanvas.this.core);
                    repaint();
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    System.out.println("DOWN");
                    MCanvas.this.core.setCurrView(MCanvas.this.core.currX, ++MCanvas.this.core.currY);
                    System.out.println(MCanvas.this.core);
                    repaint();
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < currentView.length * C.TS; i += C.TS) {
            for (int j = 0; j < currentView[i / C.TS].length * C.TS; j += C.TS) {
                //TODO: chamar drawPixel(int, int, Color) aqui
                try {
                    g.drawImage(core.getSpriteImageFrom(i / C.TS, j / C.TS), i, j, this);
                } catch (ArrayIndexOutOfBoundsException e) {
                    //e.printStackTrace();
                    //pass
                }
            }
        }
    }

    private void drawPixel(int x, int y, Color pixelColor) {
        //TODO: contruir isso para substituir o desenho da imagem
    }
}
