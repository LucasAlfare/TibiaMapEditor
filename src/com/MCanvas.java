package com;

import com.misc.D;

import javax.swing.*;
import java.awt.*;

public class MCanvas extends JComponent {

    public Graphics2D graphics2D;

    public Floor floor;
    public Layer gl, ol;

    public int mapSize = 100, viewSize = 20;

    public MKeyEvents mKeyEvents;
    public MMouseEvents mMouseEvents;

    public MCanvas() {
        this.setFocusable(true);

        gl = new GroundLayer(mapSize);
        ol = new ObjectLayer(mapSize);
        floor = new Floor(gl, ol, viewSize);

        mKeyEvents = new MKeyEvents(this);
        mMouseEvents = new MMouseEvents(this);

        addKeyListener(mKeyEvents);
        addMouseListener(mMouseEvents);
    }

    public void update() {
        floor.renderFloorImage(floor.currX, floor.currY);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        graphics2D = (Graphics2D) g;

        long i = System.currentTimeMillis();
        graphics2D.drawImage(floor.image, 0, 0, this);
        D.d(getClass(), "Demorou " + (System.currentTimeMillis() - i) + "ms pra desenhar tudo!!");

        graphics2D.dispose();
    }
}
