package com4;

import java.awt.image.BufferedImage;

public class Floor {

    public Layer groundLayer, objectLayer;
    public BufferedImage image;
    public int currX, currY;

    public Floor(Layer groundLayer, Layer objectLayer) {
        this.groundLayer = groundLayer;
        this.objectLayer = objectLayer;
    }

    /*
    Este método precisa desenhar as duas camadas de elementos, chão e objetos
    (primeiro o chão e depois os objetos).

    A melhor tática parece ser usar esse bloco como uma função de retorno, assim,
    poderá ser chamado "diretamente" (via objeto de instância) o objeto Graphics
    do JComponent alvo.
     */
    public void renderFloor(int x, int y) {
        groundLayer.paintImageOf(x, y);
        image = groundLayer.image;
    }
}
