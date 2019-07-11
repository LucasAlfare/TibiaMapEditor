package com4;

import com2.C;
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
        core.markTile(clickX, clickY);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        Point p = e.getPoint();
        clickX = (p.x / C.TS) + core.currX;
        clickY = (p.y / C.TS) + core.currY;
        D.d(getClass(), "Coordenadas clique: [" + clickX + ", " + clickY + "]");

        core.markTile(clickX, clickY);
        mCanvas.repaint();
    }

    public Tile getClickedTile() {
        return core.tiles[clickX][clickY];
    }
}
