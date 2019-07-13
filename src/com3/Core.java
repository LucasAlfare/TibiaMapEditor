package com3;

import misc.C;
import parsers.Spr;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class Core {

    //cada linha e um tile e cada coluna e uma pilha de itens do tile
    private ArrayList<ArrayList<Integer>> mapContent;

    //private int[][] mainContent;
    public int l;
    public int currX = 0, currY = 0;
    public Spr spr;

    public BufferedImage mainViewImg;

    public Core(ArrayList<ArrayList<Integer>> mapContent, int l, Spr spr) {
        this.mapContent = mapContent;

        //this.mainContent = mainContent;
        this.l = l;
        this.spr = spr;

        setCurrView(currX, currY);
    }

    public void setCurrView(int x, int y) {
        x = x <= 0 ? 0 : x;
        y = y <= 0 ? 0 : y;

        int xx = (x + l < mapContent.size()) ? x : mapContent.size() - l;
        int yy = (y + l < mapContent.size()) ? y : mapContent.size() - l;

        //sempre que for desenhar a imagem e "resetada"
        mainViewImg = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration()
                .createCompatibleImage(l * C.TS, l * C.TS, Transparency.TRANSLUCENT);

        //itera size vezes a patir da coordenada repassada
        for (int i = 0, tw = 0; i < l; i++, tw += C.TS) {
            for (int j = 0, th = 0; j < l; j++, th += C.TS) {
                //itera sobre os valores de sprite/pixel retornados do conteudo da coordenada atual
                //a funcao disso e desenhar esses valores de sprite/pixel na imagem grande
                for (Spr.Pixel p : spr.getSpriteInfo(spr.spriteAddresses.get(mapContent.get(i + xx).get(j + xx)))) {
                    mainViewImg.setRGB(p.x + tw, p.y + th, p.color.getRGB());
                }
            }
        }
    }

    public void updateMainViewImg(int x, int y, int operation) {
        /*
        TODO: operation tem que armazenar se vai ser realizado(a):
            - alteração do valor (consequentemente do tile correspondente);
            - limpeza do tile (valor igual a -1);
            - realce e/ou contorno;
            - movimentacao to tile.
         */
    }

    public void clearSlot(int x, int y) {
        setSlotValue(-1, x, y);
    }

    public void setSlotValue(int slotValue, int x, int y) {
        if (isValid(x, y)) {
            mapContent.get(x).set(y, slotValue);
            //mainContent[x][y] = slotValue;

            if (mainViewImg != null) {

                ArrayList<Spr.Pixel> targetTile =
                        spr.getSpriteInfo(spr.spriteAddresses.get(slotValue));
                for (Spr.Pixel p : targetTile) {
                    mainViewImg.setRGB(p.x + (x * C.TS), p.y + (y * C.TS), p.color.getRGB());
                }
            }
        }
    }

    public boolean isValid(int x, int y) {
        return (x >= 0 && x < mapContent.size()) &&
                (y >= 0 && y < mapContent.size());
    }
}
