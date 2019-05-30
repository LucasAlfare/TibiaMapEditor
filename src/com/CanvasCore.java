package com;

import java.util.Arrays;

public class CanvasCore {

    int[][] tiles;
    int w, h;
    int currX, currY;

    public CanvasCore(int w, int h) {
        this.w = w;
        this.h = h;
        tiles = new int[w][h];

        tiles = new int[w][h];
        for (int[] tile : tiles) {
            Arrays.fill(tile, -1);
        }
    }

    public void setTile(int x, int y, int value) {
        if ((x >= 0 && x < w) && (y >= 0 && x < h)) {
            tiles[x][y] = value;
        }
    }

    public void resetTile(int x, int y) {
        this.setTile(x, y, -1);
    }

    public int getTileValue(int x, int y) {
        if ((x >= 0 && x < w) && (y >= 0 && x < h)) {
            return tiles[x][y]; //this can return -1
        }

        return -2; //this is a not valid acess
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
