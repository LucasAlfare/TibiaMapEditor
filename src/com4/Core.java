package com4;

import parsers.Spr;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

import static com2.C.TS;

public class Core {

    public Tile[][] tiles;
    public int size;
    public int currX = 0, currY = 0;
    public Spr spr;

    public BufferedImage mainViewImg;

    public Core(int size, Spr spr) {
        this.size = size;
        this.spr = spr;

        //mock para testar
        tiles = new Tile[25][25];
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                Tile curr = new Tile(i, j);
                curr.add(new Random().nextInt(5000));
                tiles[i][j] = curr;
            }
        }

        this.setMainViewImg(currX, currY);
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

    public void addItemTo(int x, int y, int item, int times) {
        for (int i = 0; i < times; i++) {
            tiles[x][y].add(item);
        }
    }

    public void removeItemFrom(int x, int y, int item, int times) {
        Tile aux = tiles[x][y];
        for (int content : aux) {
            if (content == item) {
                for (int i = 0; i < times; i++) {
                    tiles[x][y].remove(content);
                }
            }
        }
    }

    //remove todos, exceto o primeiro
    public void clearTile(int x, int y) {
        tiles[x][y].clearTile();
    }

    //remove todos, incluindo o primeiro
    public void clearAll(int x, int y) {
        tiles[x][y].clear();
    }

    public boolean isInBounds(int x, int y) {
        return (x >= 0 && x < tiles.length) &&
                (y >= 0 && y < tiles.length);
    }

    public void setMainViewImg(int x, int y) {
        x = x <= 0 ? 0 : x;
        y = y <= 0 ? 0 : y;

        int xx = (x + size < tiles.length) ? x : tiles.length - size;
        int yy = (y + size < tiles.length) ? y : tiles.length - size;

        //sempre que for desenhar a imagem e "resetada"
        mainViewImg = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration()
                .createCompatibleImage(size * TS, size * TS, Transparency.TRANSLUCENT);

        //itera size vezes a patir da coordenada repassada
        //Obs.: tw e th sao "tile width" e "tile heigth", respectivamente
        for (int i = 0, tw = 0; i < size; i++, tw += TS) {
            for (int j = 0, th = 0; j < size; j++, th += TS) {
                //itera sobre os valores de sprite/pixel retornados do conteudo da coordenada atual
                //a funcao disso e desenhar esses valores de sprite/pixel na imagem grande
                //Nota: cada coordenada pode possuir um ou mais coisas a serem desenhadas.
                for (int currContent : tiles[i + xx][j + yy]) {
                    for (Spr.Pixel p : spr.getSpriteInfo(spr.spriteAddresses.get(currContent))) {
                        mainViewImg.setRGB(p.x + tw, p.y + th, p.color.getRGB());
                    }
                }
            }
        }
    }

    /*
    TODO:
        - corrigir marcação q fica em Tile errado depois de mexer no teclado;
        - tentar mudar o parametro para um Tile.
     */
    public void markTile(int x, int y) {
        setMainViewImg(currX, currY);
        for (int i = 0; i < TS; i++) {
            for (int j = 0; j < TS; j++) {
                int blackRGB = Color.BLACK.getRGB();
                mainViewImg.setRGB((x * TS) + i, (y * TS), blackRGB);
                mainViewImg.setRGB((x * TS), (y * TS) + j, blackRGB);
                mainViewImg.setRGB((x * TS) + i, (y * TS) + (TS - 1), blackRGB);
                mainViewImg.setRGB((x * TS) + (TS - 1), (y * TS) + j, blackRGB);

                //mainViewImg.setRGB(x + i, y + j, C.COLOR_SELECTED_TILE.getRGB());
            }
        }
    }
}
