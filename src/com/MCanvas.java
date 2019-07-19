package com;

import com.misc.ImgPainter;

import javax.swing.*;
import java.awt.*;

import static com.misc.G.mapSize;

public class MCanvas extends JComponent {

    private MapTile[][] tiles;
    private boolean firstDraw = true;

    public MCanvas() {
        tiles = new MapTile[mapSize][mapSize];
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                MapTile aux = new MapTile();
                aux.add(4540);
                tiles[i][j] = aux;
            }
        }

        tiles[0][0].add(1);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(ImgPainter.paintViewImg(tiles, firstDraw), 0, 0, this);
        firstDraw = false;
    }
}
