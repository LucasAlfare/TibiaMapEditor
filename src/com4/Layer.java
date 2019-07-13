package com4;

import parsers.Spr;

import java.awt.*;
import java.awt.image.BufferedImage;

import static misc.C.SPR;
import static misc.C.TS;

public abstract class Layer {

    public int size;
    public Element[][] elements;
    public BufferedImage image;
    public int currX, currY;

    public Layer(int size, Element[][] elements) {
        this.size = size;
        this.elements = elements;

        //mock para testar
        this.elements = new Element[25][25];
        for (int i = 0; i < this.elements.length; i++) {
            for (int j = 0; j < this.elements[i].length; j++) {
                //TileElement de Ground devem conter apenas 1 item (o chao)
                //Ja TileElement de objetos (outras coisas que ficam no ground,
                //podem conter mais de uma coisa.
                Element curr = new Element(i, j);
                curr.add(10);
                this.elements[i][j] = curr;
            }
        }

        paintImageOf(currX, currY);
    }

    public abstract void addItemIn(int x, int y, int item, int times);

    public abstract void removeItemFrom(int x, int y, int item, int times);

    public abstract void clearTile(int x, int y);

    public void resetElement(int x, int y) {
        elements[x][y].resetElement();
    }

    public boolean isInBounds(int x, int y) {
        return (x >= 0 && x < elements.length) &&
                (y >= 0 && y < elements.length);
    }

    public void paintImageOf(int x, int y) {
        x = x <= 0 ? 0 : x;
        y = y <= 0 ? 0 : y;

        int xx = (x + size < elements.length) ? x : elements.length - size;
        int yy = (y + size < elements.length) ? y : elements.length - size;

        //sempre que for desenhar a imagem e "resetada"
        image = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration()
                .createCompatibleImage(size * TS, size * TS, BufferedImage.TYPE_INT_ARGB);

        //itera size vezes a patir da coordenada repassada
        //Obs.: tw e th sao "tile width" e "tile heigth", respectivamente
        for (int i = 0, tw = 0; i < size; i++, tw += TS) {
            for (int j = 0, th = 0; j < size; j++, th += TS) {
                //itera sobre os valores de sprite/pixel retornados do conteudo da coordenada atual
                //a funcao disso e desenhar esses valores de sprite/pixel na imagem grande
                //Nota: cada coordenada pode possuir um ou mais coisas a serem desenhadas.
                for (int currContent : elements[i + xx][j + yy]) {
                    for (Spr.Pixel p : SPR.getSpriteInfo(SPR.spriteAddresses.get(currContent))) {
                        image.setRGB(p.x + tw, p.y + th, p.color.getRGB());
                    }
                }
            }
        }
    }
}
