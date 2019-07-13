package com5;

import misc.D;

import java.util.ArrayList;


public class TileElement extends ArrayList<Integer> {

    public int x, y;

    public enum State {
        EMPTY, GROUND, STACK
    }

    public TileElement(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void resetTileElement() {
        this.clear();
    }

    public void removeLastTileElement() {
        if (this.size() > 1) {
            remove(size() - 1);
        }
    }

    public void clearTileElement() {
        int sz = size();
        for (int i = 0; i < sz; i++) {
            removeLastTileElement();
        }
    }

    public State getTileElementState() {
        if (isEmpty()) {
            return State.EMPTY;
        } else if (size() == 1) {
            return State.GROUND;
        } else {
            return State.STACK;
        }
    }

    @Override
    public String toString() {
        return D.d(getClass(), "Este TileElement (map coord)[" + x + ", " + y + "] contém os itens: " + super.toString());
    }
}
