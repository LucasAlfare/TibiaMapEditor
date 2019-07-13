package com5;

import misc.C;
import misc.D;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class MMouseEvents extends MouseAdapter {

    public MCanvas mCanvas;
    public int cX, cY; //coordenadas de clique atuais
    public Point p;

    public MMouseEvents(MCanvas mCanvas) {
        this.mCanvas = mCanvas;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        updateClickInfo(e);
        switch (getClickedTileElement().getTileElementState()) {
            case GROUND:
                mCanvas.floor.objectLayer.tileElements[cX][cY].add(new Random().nextInt(5000));
                break;
            case STACK:
                mCanvas.floor.objectLayer.tileElements[cX][cY].add(new Random().nextInt(5000));
                break;
        }

        mCanvas.update();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        updateClickInfo(e);
        D.d(getClass(), "Coordenadas clique (mouseReleased): [" + cX + ", " + cY + "]");

        mCanvas.repaint();
    }

    private void updateClickInfo(MouseEvent e) {
        p = e.getPoint();
        cX = (p.x / C.TS) + mCanvas.floor.currX;
        cY = (p.y / C.TS) + mCanvas.floor.currY;
    }

    public TileElement getClickedTileElement() {
        if (!mCanvas.floor.objectLayer.tileElements[cX][cY].isEmpty()) {
            return mCanvas.floor.objectLayer.tileElements[cX][cY];
        } else if (mCanvas.floor.groundLayer.tileElements[cX][cY].size() == 1) {
            return mCanvas.floor.groundLayer.tileElements[cX][cY];
        }

        return null; //nao há elementos, está "preto", "vazio"...
    }
}
