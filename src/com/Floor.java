package com;

import java.awt.image.BufferedImage;
import java.util.Random;

import static com.misc.G.viewSize;
import static com.misc.ImgPainter.paintFloorImage;

public class Floor {

    public static final int ID = new Random().nextInt();
    public Layer groundLayer, objectLayer;
    public BufferedImage image;
    public int currX, currY, targetX, targetY;
    public boolean initied = false;

    public Floor() {
        groundLayer = new GroundLayer();
        objectLayer = new ObjectLayer();

        init();
    }

    private void init() {
        objectLayer.tileElements[0][0].add(2);
        objectLayer.tileElements[viewSize - 1][viewSize - 1].add(4);

        paintFloorImage(currX, currY, this);
        initied = true;
    }

//    public void renderFloorImage2(int x, int y) {
//        if (image == null) {
//            paintFloorImage(x, y);
//        } else {
//            int xx = x + viewSize < mapSize ? x : mapSize - viewSize;
//            int yy = y + viewSize < mapSize ? y : mapSize - viewSize;
//
//            System.out.println("targets: " + targetX + "," + targetY);
//            System.out.println("currents: " + currX + "," + currY);
//
//            BufferedImage aux = rawImage(image.getWidth(), image.getHeight());
//            aux.getGraphics()
//                    .drawImage(image
//                                    .getSubimage(
//                                            targetX * TS,
//                                            targetY * TS,
//                                            image.getWidth() - (targetX == 1 ? TS : 0),
//                                            image.getHeight() - (targetY == 1 ? TS : 0)),
//                            targetX * (targetX == 1 ? 0 : TS),
//                            targetY * (targetY == 1 ? 0 : TS),
//                            null);
//
//            for (int i = 0, tw = 0; i < viewSize * targetX; i++, tw += TS) {
//                //pinta o chao aaaaaa
//                for (int currContent : groundLayer.tileElements[i + xx][targetY + yy]) {
//                    paintContentPixels(aux, currContent, tw, 0);
//                }
//
//                if (!objectLayer.tileElements[i + xx][targetY + yy].isEmpty()) {
//                    //pinta os objetos que estiverem sobre o chao (mesmo lugar)
//                    for (int currContent : objectLayer.tileElements[i + xx][targetY + yy]) {
//                        if (currContent > 0) {
//                            paintContentPixels(aux, currContent, tw, 0);
//                        }
//                    }
//                }
//            }
//
//            for (int j = 0, th = 0; j < viewSize * targetY; j++, th += TS) {
//                //pinta o chao aaaaaa
//                for (int currContent : groundLayer.tileElements[targetX + xx][j + yy]) {
//                    paintContentPixels(aux, currContent, 0, th);
//                }
//
//                if (!objectLayer.tileElements[targetX + xx][j + yy].isEmpty()) {
//                    //pinta os objetos que estiverem sobre o chao (mesmo lugar)
//                    for (int currContent : objectLayer.tileElements[targetX + xx][j + yy]) {
//                        if (currContent > 0) {
//                            paintContentPixels(aux, currContent, 0, th);
//                        }
//                    }
//                }
//            }
//
//            image = aux;
//        }
//
//        targetX = 0;
//        targetY = 0;
//    }
}
