package com4;

import misc.C;
import misc.D;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MMouseEvents extends MouseAdapter {

    public MCanvas mCanvas;
    public Core core;
    public int clickX, clickY;

    public MMouseEvents(MCanvas mCanvas) {
        this.mCanvas = mCanvas;
        core = this.mCanvas.getCore();
        //core.markTile(cX, cY);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        Point p = e.getPoint();
        clickX = (p.x / C.TS) + core.currX;
        clickY = (p.y / C.TS) + core.currY;
        D.d(getClass(), "Coordenadas clique: [" + clickX + ", " + clickY + "]");

        //core.markTile(cX, cY);
        System.out.println(getClickedTile().get(0));
        mCanvas.repaint();
    }

    public Element getClickedTile() {
        return core.elements[clickX][clickY];
    }
}
