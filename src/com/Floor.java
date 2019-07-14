package com;

import java.awt.image.BufferedImage;
import java.util.Random;

import static com.misc.ImgPainter.paintFloorImage;

public class Floor {

    public static final int ID = new Random().nextInt();
    public Layer groundLayer, objectLayer;
    public BufferedImage image;
    public int currX, currY, targetX, targetY;
    public boolean initied;

    public Floor() {
        groundLayer = new GroundLayer();
        objectLayer = new ObjectLayer();

        init();
    }

    private void init() {
        update();
        initied = true;
    }

    public void update() {
        paintFloorImage(currX, currY, this);
        //paintFloorImage2(currX, currY, this);
    }
}
