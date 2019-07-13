package com5;

import misc.C;
import misc.D;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class MMouseEvents extends MouseAdapter {

    public MCanvas mCanvas;
    public int cx, cy; //coordenadas de clique atuais
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
                mCanvas.floor.objectLayer.tileElements[cx][cy].add(new Random().nextInt(5000));
                break;
            case STACK:
                mCanvas.floor.objectLayer.tileElements[cx][cy].add(new Random().nextInt(5000));
                break;

            default:
                break;
        }

        mCanvas.update();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        updateClickInfo(e);
        D.d(getClass(), "Coordenadas clique (mouseReleased): [" + cx + ", " + cy + "]");

        mCanvas.repaint();
    }

    private void updateClickInfo(MouseEvent e) {
        p = e.getPoint();
        cx = (p.x / C.TS) + mCanvas.floor.currX;
        cy = (p.y / C.TS) + mCanvas.floor.currY;
    }

    private TileElement getClickedTileElement() {
        if (!mCanvas.floor.objectLayer.tileElements[cx][cy].isEmpty()) {
            return mCanvas.floor.objectLayer.tileElements[cx][cy];
        } else if (mCanvas.floor.groundLayer.tileElements[cx][cy].size() == 1) {
            return mCanvas.floor.groundLayer.tileElements[cx][cy];
        }

        return null; //nao há elementos, está "preto", "vazio"...
    }
}
