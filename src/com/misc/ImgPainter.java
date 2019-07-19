package com.misc;

import com.MCoordinate;
import com.MapTile;
import com.parsers.Spr;

import java.awt.*;
import java.awt.image.BufferedImage;

import static com.misc.G.*;

@SuppressWarnings("ALL")
public class ImgPainter {

    public static BufferedImage paintViewImg(MapTile[][] tiles, boolean firstDraw) {
        int worldX = currentViewCoordinate.x + viewSize < mapSize ?
                currentViewCoordinate.x : mapSize - viewSize;
        int worldY = currentViewCoordinate.y + viewSize < mapSize ?
                currentViewCoordinate.y : mapSize - viewSize;

        long s = System.currentTimeMillis();
        if (firstDraw) {
            viewImg = rawImage(viewSize * ts, viewSize * ts);
            for (int i = 0, tw = 0; i < viewSize; i++, tw += ts) {
                for (int j = 0, th = 0; j < viewSize; j++, th += ts) {
                    for (int currContent : tiles[worldX + i][worldY + j]) {
                        paintContentPixels(viewImg, currContent, tw, th);
                    }
                }
            }
        } else {
            //TODO: isso deve ser chamado só quando o teclado for acionado!
            /*
            BufferedImage aux = viewImg.getSubimage(
                    targetX * ts,
                    targetY * ts,
                    (viewSize - 1) * ts,
                    (viewSize - 1) * ts
            );

            //TODO: achar uma forma mais eficiente de copiar a imagem X na outra
            viewImg.getGraphics().drawImage(aux, 0, 0, null);

            MCoordinate tmp1 = new MCoordinate(0, 0);

            //horizontal
            for (int i = 0, tw = 0; i < viewSize; i++, tw += ts) {
                for(int currContent : tiles[i][targetY == 1 ? worldY : 0]){
                    paintContentPixels(tmp1, viewImg, currContent, tw, 0);
                    tmp1.x++;
                }
            }

            //vertical
            for (int j = 0, th = 0; j < viewSize - 1; j++, th += ts) {
                for(int currContent : tiles[targetX == 1 ? worldX : 0][j]){
                    paintContentPixels(tmp1, viewImg, currContent, 0, th);
                    tmp1.y++;
                }
            }
             */
        }

        D.d(ImgPainter.class, "tudo foi pintado em " + (System.currentTimeMillis() - s) + "ms");
        targetX = 0;
        targetY = 0;
        return viewImg;
    }

    private static void paintContentPixels(BufferedImage targetImage, int targetContentValue, int tw, int th) {
        paintContentPixels(new MCoordinate(0, 0), targetImage, targetContentValue, tw, th);
    }

    private static void paintContentPixels(MCoordinate coord, BufferedImage targetImage, int targetContentValue, int tw, int th) {
        for (Spr.Pixel pixel : SPR.getSpriteInfo(SPR.spriteAddresses.get(targetContentValue))) {
            targetImage.setRGB(
                    pixel.x + (coord.x * ts) + tw,
                    pixel.y + (coord.y * ts) + th,
                    pixel.color.getRGB());
        }
    }

    public static BufferedImage rawImage(int w, int h) {
        return GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration()
                .createCompatibleImage(w, h, BufferedImage.TYPE_INT_ARGB);
    }
}
