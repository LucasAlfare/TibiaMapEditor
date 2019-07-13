package com5;

import misc.C;
import misc.D;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MMouseEvents extends MouseAdapter {

    public MCanvas mCanvas;
    public int clickX, clickY;

    public MMouseEvents(MCanvas mCanvas) {
        this.mCanvas = mCanvas;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        Point p = e.getPoint();
        clickX = (p.x / C.TS) + mCanvas.floor.currX;
        clickY = (p.y / C.TS) + mCanvas.floor.currY;
        D.d(getClass(), "Coordenadas clique (mouseReleased): [" + clickX + ", " + clickY + "]");
        System.out.println(getClickedTileElement());

        mCanvas.repaint();
    }

    public TileElement getClickedTileElement() {
        if (!mCanvas.floor.objectLayer.tileElements[clickX][clickY].isEmpty()) {
            return mCanvas.floor.objectLayer.tileElements[clickX][clickY];
        } else if (mCanvas.floor.groundLayer.tileElements[clickX][clickY].size() == 1) {
            return mCanvas.floor.groundLayer.tileElements[clickX][clickY];
        }

        return null; //nao há elementos, está "preto", "vazio"...
    }
}
