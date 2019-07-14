package com;

import com.misc.D;

import javax.swing.*;
import java.awt.*;

import static com.misc.ImgPainter.paintFloorImage;

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
        paintFloorImage(floor.currX, floor.currY, floor);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        graphics2D = (Graphics2D) g;

        long i = System.currentTimeMillis();
        graphics2D.drawImage(floor.image, 0, 0, this);
        D.d(getClass(), "Demorou " + (System.currentTimeMillis() - i) + "ms pra exibir a imagem");

        graphics2D.dispose();
    }
}
