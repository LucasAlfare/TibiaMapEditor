package com;

import com.misc.G;

public class GroundLayer extends Layer {

    public GroundLayer() {

    }

    @Override
    public void setupElements() {
        //mock para testar
        this.tileElements = new TileElement[G.mapSize][G.mapSize];
        for (int i = 0; i < this.tileElements.length; i++) {
            for (int j = 0; j < this.tileElements[i].length; j++) {
                //TileElement de Ground devem conter apenas 1 item (o chao)
                //Ja TileElement de objetos (outras coisas que ficam no ground,
                //podem conter mais de uma coisa.
                TileElement curr = new TileElement(i, j);
                curr.add(4540);
                this.tileElements[i][j] = curr;
            }
        }
    }

    @Override
    public void addItemIn(int x, int y, int item, int times) {
        //pass
    }

    @Override
    public void removeItemFrom(int x, int y, int item, int times) {

    }

    @Override
    public void clearTile(int x, int y) {

    }
}
