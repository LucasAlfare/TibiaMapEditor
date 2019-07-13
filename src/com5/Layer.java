package com5;

public abstract class Layer {

    public int size;
    public Element[][] elements;

    public Layer(int size) {
        this.size = size;

        setupElements();
    }

    public abstract void setupElements();

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
}
