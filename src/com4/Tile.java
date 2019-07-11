package com4;

import java.util.ArrayList;


public class Tile extends ArrayList<Integer> {

    public int x, y;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void removeLast() {
        if (this.size() > 1) {
            remove(size() - 1);
        }
    }

    public void clearTile() {
        int sz = size();
        for (int i = 0; i < sz; i++) {
            removeLast();
        }
    }

    @Override
    public String toString() {
        return "O Tile das coordenadas [" + x + ", " + y + "] contem os itens: " + super.toString();
    }
}
