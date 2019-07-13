package com2;

import misc.C;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;

public class Canvaskk extends JComponent implements Scrollable {

    private int w, h;
    private CanvasCore core;
    private int TS = 32;
    private Dimension preferredScrollableViewportSize;

    public Canvaskk(int w, int h) throws IOException {
        this.w = w;
        this.h = h;

        core = new CanvasCore(w, h);
        //System.out.println(core);

        init();
    }

    private void init() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                super.mousePressed(mouseEvent);
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                super.mouseReleased(mouseEvent);
                int x = mouseEvent.getX() / C.TS;
                int y = mouseEvent.getY() / C.TS;
                setAndDrawRefresh(x, y);
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent mouseEvent) {
                super.mouseDragged(mouseEvent);
            }

            @Override
            public void mouseMoved(MouseEvent mouseEvent) {
                super.mouseMoved(mouseEvent);
            }
        });
    }

    private void setAndDrawRefresh(int x, int y) {
        if (core.setTile(x, y, 45)) {
            System.out.println();
            System.out.println(String.format("x: %s, y: %s", x, y));
            System.out.println(core.getTileValue(x, y));
            System.out.println(core);

            repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        //TODO: fazer com q isso seja chamado somente se uma coord for alterada
        for (int i = 0; i < core.tiles.length * C.TS; i += C.TS) {
            for (int j = 0; j < core.tiles[i / C.TS].length * C.TS; j += C.TS) {
                try {
                    graphics.drawImage(core.getTileSpriteImage(i / C.TS, j / C.TS), i, j, this);
                } catch (ArrayIndexOutOfBoundsException e) {
                    //e.printStackTrace();
                    //pass
                }
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(w * C.TS, h * C.TS);
    }

    @Override
    public Dimension getPreferredScrollableViewportSize() {
        return preferredScrollableViewportSize;
    }

    public void setPreferredScrollableViewportSize(Dimension size) {
        this.preferredScrollableViewportSize = size;
    }

    @Override
    public int getScrollableUnitIncrement(Rectangle rectangle, int i, int i1) {
        return C.TS;
    }

    @Override
    public int getScrollableBlockIncrement(Rectangle rectangle, int i, int i1) {
        return C.TS;
    }

    @Override
    public boolean getScrollableTracksViewportWidth() {
        return false;
    }

    @Override
    public boolean getScrollableTracksViewportHeight() {
        return false;
    }
}
