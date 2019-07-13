package com5;

public class ObjectLayer extends Layer {

    public ObjectLayer(int size) {
        super(size);
    }

    @Override
    public void setupElements() {
        //mock para testar
        this.tileElements = new TileElement[size][size];
        for (int i = 0; i < this.tileElements.length; i++) {
            for (int j = 0; j < this.tileElements[i].length; j++) {
                //TileElement de Ground devem conter apenas 1 item (o chao)
                //Ja TileElement de objetos (outras coisas que ficam no ground,
                //podem conter mais de uma coisa.
                TileElement curr = new TileElement(i, j);
                if ((i == 1 && j == 1) || (i == 3 && j == 2)) {
                    //if (new Random().nextBoolean()){
                    curr.add(1); //um sapo
                }

                this.tileElements[i][j] = curr;
            }
        }
    }

    @Override
    public void addItemIn(int x, int y, int item, int times) {

    }

    @Override
    public void removeItemFrom(int x, int y, int item, int times) {

    }

    @Override
    public void clearTile(int x, int y) {

    }
}
