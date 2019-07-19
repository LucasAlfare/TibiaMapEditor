package com;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static com.misc.G.currentViewCoordinate;

public class MKeyEvents extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        currentViewCoordinate.move(e.getKeyCode());
    }
}
