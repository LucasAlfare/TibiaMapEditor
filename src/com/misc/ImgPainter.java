package com.misc;

import com.Floor;
import com.parsers.Spr;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import static com.misc.G.*;

public class ImgPainter {

    /**
     * TODO:
     * - performance pode melhorar evitando de desenhar pilhas muito grandes.
     * pode ser uma boa alternativa limitar o desenho da pilha somente para os ultimos
     * X itens!
     * - achar um jeito de guardar um desenho e so iterar pixels se o atual for diferente
     * do anterior! (talvez seja possivel guardando isso em uma subImage!)
     */
    public static void renderFloorImage(int x, int y, Floor currentFloor) {
        int xx = x + viewSize < mapSize ? x : mapSize - viewSize;
        int yy = y + viewSize < mapSize ? y : mapSize - viewSize;

        //sempre que for desenhar a imagem e "resetada"
        currentFloor.image = rawImage(viewSize * TS, viewSize * TS);

        long s = System.currentTimeMillis();
        for (int i = 0, tw = 0; i < viewSize; i++, tw += TS) {
            for (int j = 0, th = 0; j < viewSize; j++, th += TS) {
                //pinta o chao aaaaaa
                for (int currContent : currentFloor.groundLayer.tileElements[i + xx][j + yy]) {
                    paintContentPixels(currentFloor.image, currContent, tw, th);
                }

                if (!currentFloor.objectLayer.tileElements[i + xx][j + yy].isEmpty()) {
                    //pinta os objetos que estiverem sobre o chao (mesmo lugar)
                    for (int currContent : currentFloor.objectLayer.tileElements[i + xx][j + yy]) {
                        if (currContent > 0) {
                            paintContentPixels(currentFloor.image, currContent, tw, th);
                        }
                    }
                }
            }
        }
        D.d(ImgPainter.class, "Imagem do andar de ID [" + 0 + "] foi desenhada em " + (System.currentTimeMillis() - s) + "ms!!!!");
    }

    private static void paintContentPixels(BufferedImage targetImage, int targetContentValue, int tw, int th) {
        for (Spr.Pixel p : SPR.getSpriteInfo(SPR.spriteAddresses.get(targetContentValue))) {
            targetImage.setRGB(p.x + tw, p.y + th, p.color.getRGB());
        }
    }

    public static BufferedImage rawImage(int w, int h) {
        return GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration()
                .createCompatibleImage(
                        w, h,
                        BufferedImage.TYPE_INT_ARGB);
    }

    public static void copyImgToAnotherAt(BufferedImage src, BufferedImage dst, int dx, int dy) {
        int[] srcBuf = ((DataBufferInt) src.getRaster().getDataBuffer()).getData();
        int[] dstBuf = ((DataBufferInt) dst.getRaster().getDataBuffer()).getData();
        int w = src.getWidth();
        int h = src.getHeight();
        int dstOffset = dx + dy * dst.getWidth();
        int srcOffset = 0;
        for (int y = 0; y < h; y++, dstOffset += dst.getWidth(), srcOffset += w) {
            System.arraycopy(srcBuf, srcOffset, dstBuf, dstOffset, w);
        }
    }
}
