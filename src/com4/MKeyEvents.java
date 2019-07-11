package com4;

import misc.D;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MKeyEvents extends KeyAdapter {

    private MCanvas mCanvas;
    private Core core;

    public MKeyEvents(MCanvas mCanvas) {
        this.mCanvas = mCanvas;
        core = this.mCanvas.getCore();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            D.d(getClass(), "LEFT (keyPressed)");
            core.setMainViewImg(--core.currX, core.currY);
            mCanvas.repaint();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            D.d(getClass(), "RIGHT (keyPressed)");
            core.setMainViewImg(++core.currX, core.currY);
            mCanvas.repaint();
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            D.d(getClass(), "UP (keyPressed)");
            core.setMainViewImg(core.currX, --core.currY);
            mCanvas.repaint();
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            D.d(getClass(), "DOWN (keyPressed)");
            core.setMainViewImg(core.currX, ++core.currY);
            mCanvas.repaint();
        }
    }
}
