package com2;

import java.util.Arrays;

public class CanvasCore {

    int[][] tiles;
    int w, h;
    int currX, currY;

    public CanvasCore(int w, int h) {
        this.w = w;
        this.h = h;

        tiles = new int[w][h];
        for (int[] tile : tiles) {
            Arrays.fill(tile, -1);
        }
    }

    public void setTile(int x, int y, int value) {
        if (coordInBounds(x, y)) {
            tiles[x][y] = value;
        }
    }

    public void resetTile(int x, int y) {
        setTile(x, y, -1);
    }

    public int getTileValue(int x, int y) {
        return coordInBounds(x, y) ? tiles[x][y] : -2;
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
