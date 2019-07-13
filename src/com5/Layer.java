package com5;

public abstract class Layer {

    public int size;
    public TileElement[][] tileElements;

    public Layer(int size) {
        this.size = size;

        setupElements();
    }

    public abstract void setupElements();

    public abstract void addItemIn(int x, int y, int item, int times);

    public abstract void removeItemFrom(int x, int y, int item, int times);

    public abstract void clearTile(int x, int y);

    public void resetElement(int x, int y) {
        tileElements[x][y].resetTileElement();
    }

    /*
    TODO: usar isso pra controlar os métodos daqui e evitar AIOOBE
     */
    public boolean isInBounds(int x, int y) {
        return (x >= 0 && x < tileElements.length) &&
                (y >= 0 && y < tileElements.length);
    }
}
