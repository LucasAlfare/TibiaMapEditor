package com;

import com.misc.D;

import javax.swing.*;
import java.awt.*;

import static com.misc.G.TS;
import static com.misc.G.viewSize;

public class MCanvas extends JComponent {

    public Graphics2D graphics2D;

    public Floor floor;

    public MKeyEvents mKeyEvents;
    public MMouseEvents mMouseEvents;

    public MCanvas() {
        this.setFocusable(true);
        floor = new Floor();
        mKeyEvents = new MKeyEvents(this);
        mMouseEvents = new MMouseEvents(this);
        addKeyListener(mKeyEvents);
        addMouseListener(mMouseEvents);
    }

    public void update() {
        floor.update();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        graphics2D = (Graphics2D) g;

        long i = System.currentTimeMillis();
        graphics2D.drawImage(floor.image, 0, 0, this);
        graphics2D.drawRect(0, 0, viewSize * TS, viewSize * TS);
        D.d(getClass(), "Demorou " + (System.currentTimeMillis() - i) + "ms pra exibir a imagem");

        graphics2D.dispose();
    }
}
