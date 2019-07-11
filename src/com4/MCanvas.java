package com4;

import misc.D;

import javax.swing.*;
import java.awt.*;

public class MCanvas extends JComponent {

    public Graphics2D graphics2D;

    private Core core;
    MKeyEvents mKeyEvents;
    MMouseEvents mMouseEvents;

    public MCanvas(Core core) {
        this.core = core;
        this.setFocusable(true);

        mKeyEvents = new MKeyEvents(this);
        mMouseEvents = new MMouseEvents(this);

        addKeyListener(mKeyEvents);
        addMouseListener(mMouseEvents);
    }

    public Core getCore() {
        return this.core;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        graphics2D = (Graphics2D) g;

        long i = System.currentTimeMillis();
        graphics2D.drawImage(core.mainViewImg, 0, 0, this);
        D.d(getClass(), "Demorou " + (System.currentTimeMillis() - i) + "ms pra desenhar tudo!!");

        graphics2D.dispose();
    }
}
