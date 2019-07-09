package com3;

import com2.C;
import parsers.Spr;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class Core {

    private int[][] mainContent;
    public int l;
    //TODO: fazer com que seja um array de BufferedImage
    private int[][] currView;
    public int currX = 0, currY = 0;
    public Spr spr;

    public BufferedImage mainViewImg;

    public Core(int[][] mainContent, int l, Spr spr) {
        this.mainContent = mainContent;
        this.l = l;
        this.spr = spr;
        this.currView = new int[l][l];

        setCurrView(currX, currY);
    }

    //TODO: adicionar spriteinfo em uma grande BufferedImage aqui
    public void setCurrView(int x, int y) {
        x = x < 0 ? 0 : x;
        y = y < 0 ? 0 : y;

        int xx = (x + l < mainContent.length) ? x : mainContent.length - l;
        int yy = (y + l < mainContent.length) ? y : mainContent.length - l;

        mainViewImg = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration()
                .createCompatibleImage(l * C.TS, l * C.TS, Transparency.TRANSLUCENT);

        for (int i = 0, tw = 0; i < l; i++, tw += C.TS) {
            for (int j = 0, th = 0; j < l; j++, th += C.TS) {
                for (Spr.Pixel p : spr.getSpriteInfo(spr.spriteAddresses.get(mainContent[i + xx][j + yy]))) {
                    mainViewImg.setRGB(p.x + tw, p.y + th, p.color.getRGB());
                }
            }
        }
    }

    public void clearSlot(int x, int y) {
        setSlotValue(-1, x, y);
    }

    public void setSlotValue(int slotValue, int x, int y) {
        if (isValid(x, y)) {
            mainContent[x][y] = slotValue;
        }
    }

    public boolean isValid(int x, int y) {
        return (x >= 0 && x < mainContent.length) &&
                (y >= 0 && y < mainContent.length);
    }

    public Image getSpriteImageFrom(int x, int y) {
        return spr.spriteImage(currView[x][y]);
    }

    public ArrayList<Spr.Pixel> getSpriteInfoFrom(int x, int y) {
        return spr.getSpriteInfo(spr.spriteAddresses.get(mainContent[x][y]));
    }

    public int[][] getCurrView() {
        return currView;
    }

    public void setL(int l) {
        this.l = l;
    }
}
