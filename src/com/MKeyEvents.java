package com;

import com.misc.D;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static com.misc.G.mapSize;

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
            mCanvas.floor.currX = mCanvas.floor.currX <= 0 ? 0 : --mCanvas.floor.currX;
            mCanvas.floor.targetX = 0;
            mCanvas.update();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            D.d(getClass(), "RIGHT (keyPressed)");
            mCanvas.floor.currX = mCanvas.floor.currX >= mapSize ? mapSize : ++mCanvas.floor.currX;
            mCanvas.floor.targetX = 1;
            mCanvas.update();
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            D.d(getClass(), "UP (keyPressed)");
            mCanvas.floor.currY = mCanvas.floor.currY <= 0 ? 0 : --mCanvas.floor.currY;
            mCanvas.floor.targetY = 0;
            mCanvas.update();
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            D.d(getClass(), "DOWN (keyPressed)");
            mCanvas.floor.currY = mCanvas.floor.currY >= mapSize ? mapSize : ++mCanvas.floor.currY;
            mCanvas.floor.targetY = 1;
            mCanvas.update();
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
    }
}
