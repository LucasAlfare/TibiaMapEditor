package com5;

public class GroundLayer extends Layer {

    public GroundLayer(int size) {
        super(size);
    }

    @Override
    public void setupElements() {
        //mock para testar
        this.elements = new Element[size][size];
        for (int i = 0; i < this.elements.length; i++) {
            for (int j = 0; j < this.elements[i].length; j++) {
                //Element de Ground devem conter apenas 1 item (o chao)
                //Ja Element de objetos (outras coisas que ficam no ground,
                //podem conter mais de uma coisa.
                Element curr = new Element(i, j);
                curr.add(4540);
                this.elements[i][j] = curr;
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
