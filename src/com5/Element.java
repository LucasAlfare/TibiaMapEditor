package com5;

import misc.D;

import java.util.ArrayList;


public class Element extends ArrayList<Integer> {

    public int x, y;

    public Element(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void resetElement() {
        this.clear();
    }

    public void removeLast() {
        if (this.size() > 1) {
            remove(size() - 1);
        }
    }

    public void clearElement() {
        int sz = size();
        for (int i = 0; i < sz; i++) {
            removeLast();
        }
    }

    @Override
    public String toString() {
        return D.d(getClass(), "Este Element [" + x + ", " + y + "] contem os itens: " + super.toString());
    }
}
