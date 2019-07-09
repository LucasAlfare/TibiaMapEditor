package com3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MCanvas extends JComponent {

    private Core core;

    public MCanvas(Core core) {
        this.core = core;
        this.setFocusable(true);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyReleased(e);

                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    System.out.println("Left");
                    MCanvas.this.core.setCurrView(--MCanvas.this.core.currX, MCanvas.this.core.currY);
                    repaint();
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    System.out.println("Right");
                    MCanvas.this.core.setCurrView(++MCanvas.this.core.currX, MCanvas.this.core.currY);
                    repaint();
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    System.out.println("UP");
                    MCanvas.this.core.setCurrView(MCanvas.this.core.currX, --MCanvas.this.core.currY);
                    repaint();
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    System.out.println("DOWN");
                    MCanvas.this.core.setCurrView(MCanvas.this.core.currX, ++MCanvas.this.core.currY);
                    repaint();
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        long i = System.currentTimeMillis();
        g.drawImage(core.mainViewImg, 0, 0, this);
        System.out.println("Demorou " + (System.currentTimeMillis() - i) + "ms!");

        g.dispose();
    }
}
