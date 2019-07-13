package com5;

import misc.D;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MKeyEvents extends KeyAdapter {

    private MCanvas mCanvas;

    public MKeyEvents(MCanvas mCanvas) {
        this.mCanvas = mCanvas;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            D.d(getClass(), "LEFT (keyPressed)");
            mCanvas.floor.renderFloor(--mCanvas.floor.currX, mCanvas.floor.currY);
            mCanvas.repaint();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            D.d(getClass(), "RIGHT (keyPressed)");
            mCanvas.floor.renderFloor(++mCanvas.floor.currX, mCanvas.floor.currY);
            mCanvas.repaint();
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            D.d(getClass(), "UP (keyPressed)");
            mCanvas.floor.renderFloor(mCanvas.floor.currX, --mCanvas.floor.currY);
            mCanvas.repaint();
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            D.d(getClass(), "DOWN (keyPressed)");
            mCanvas.floor.renderFloor(mCanvas.floor.currX, ++mCanvas.floor.currY);
            mCanvas.repaint();
        }
    }
}
