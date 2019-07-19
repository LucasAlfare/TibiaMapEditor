package com;

import java.awt.event.KeyEvent;

import static com.misc.G.*;

public class MCoordinate {

    public int x, y;

    public MCoordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move(int keyDirection) {
        switch (keyDirection) {
            case KeyEvent.VK_UP:
                targetX = 0;
                y--;
                break;
            case KeyEvent.VK_RIGHT:
                targetY = 1;
                x++;
                break;
            case KeyEvent.VK_U:
                targetY = 0;
                x--;
                break;
            case KeyEvent.VK_DOWN:
                targetX = 1;
                y++;
                break;
        }

        x = x < 0 ? 0 : (x > viewSize ? viewSize : x);
        y = y < 0 ? 0 : (y > viewSize ? viewSize : y);
    }

    @Override
    public String toString() {
        return "[" + x + ", " + y + "]";
    }
}
