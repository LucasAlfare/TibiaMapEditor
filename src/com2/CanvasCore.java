package com2;

import parsers.Spr;

import java.awt.*;
import java.io.IOException;
import java.util.Arrays;

public class CanvasCore {

    public int[][] tiles;
    public int w, h;

    public Spr sprParser;

    public CanvasCore(int w, int h) throws IOException {
        this.w = w;
        this.h = h;
        sprParser = new Spr("src/assets/tibia-8.6.spr");

        tiles = new int[w][h];
        for (int[] tile : tiles) {
            Arrays.fill(tile, -1);
        }
    }

    public boolean setTile(int x, int y, int tileValue) {
        if (coordInBounds(x, y)) {
            if (getTileValue(x, y) != tileValue) {
                tiles[x][y] = tileValue;
                return true;
            } else {
                return false;
            }
        }

        return false;
    }

    public boolean resetTile(int x, int y) {
        return setTile(x, y, -1);
    }

    public int getTileValue(int x, int y) {
        return coordInBounds(x, y) ? tiles[x][y] : -2;
    }

    public Image getTileSpriteImage(int x, int y) {
        if (coordInBounds(x, y)) {
            return sprParser.spriteImage(tiles[x][y]);
        }
        return null;
    }

    private boolean coordInBounds(int x, int y) {
        return (x >= 0 && x < w) && (y >= 0 && x < h);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int[] linha : tiles) {
            for (int i : linha) {
                s.append(i).append(" ");
            }
            s.append("\n");
        }
        return s.toString();
    }
}
