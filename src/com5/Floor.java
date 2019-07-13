package com5;

import parsers.Spr;

import java.awt.*;
import java.awt.image.BufferedImage;

import static misc.C.SPR;
import static misc.C.TS;

public class Floor {

    public Layer groundLayer, objectLayer;
    public BufferedImage image;
    public int currX, currY;

    public int viewSize, mapSize;

    public Floor(Layer groundLayer, Layer objectLayer, int viewSize) {
        this.groundLayer = groundLayer;
        this.objectLayer = objectLayer;
        this.viewSize = viewSize;

        mapSize = groundLayer.elements.length;

        System.out.println(viewSize);
        System.out.println(mapSize);

        renderFloor(currX, currY);
    }

    public void renderFloor(int x, int y) {
        x = x <= 0 ? 0 : x;
        y = y <= 0 ? 0 : y;
        //System.out.println("x: " + x + ", y: " + y);

        int xx = x + viewSize < mapSize ? x : mapSize - viewSize;
        int yy = y + viewSize < mapSize ? y : mapSize - viewSize;

        //sempre que for desenhar a imagem e "resetada"
        image = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration()
                .createCompatibleImage(viewSize * TS, viewSize * TS, BufferedImage.TYPE_INT_ARGB);

        for (int i = 0, tw = 0; i < viewSize; i++, tw += TS) {
            for (int j = 0, th = 0; j < viewSize; j++, th += TS) {
                //pinta o chao
                for (int currContent : groundLayer.elements[i + xx][j + yy]) {
                    paintContentPixels(currContent, tw, th);
                }

                //pinta os objetos que estiverem sobre o chao (mesmo lugar)
                for (int currContent : objectLayer.elements[i + xx][j + yy]) {
                    if (currContent > 0) {
                        paintContentPixels(currContent, tw, th);
                    }
                }
            }
        }
    }

    private void paintContentPixels(int targetContentValue, int tw, int th) {
        for (Spr.Pixel p : SPR.getSpriteInfo(SPR.spriteAddresses.get(targetContentValue))) {
            image.setRGB(p.x + tw, p.y + th, p.color.getRGB());
        }
    }
}
