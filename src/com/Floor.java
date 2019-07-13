package com;

import com.misc.D;
import com.parsers.Spr;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;

import static com.misc.C.SPR;
import static com.misc.C.TS;

public class Floor {

    public static final int ID = new Random().nextInt();
    public Layer groundLayer, objectLayer;
    public BufferedImage image;
    public int currX, currY;

    private BufferedImage currentSubImage;

    public int viewSize, mapSize;

    public Floor(Layer groundLayer, Layer objectLayer, int viewSize) {
        this.groundLayer = groundLayer;
        this.objectLayer = objectLayer;
        this.viewSize = viewSize;

        mapSize = groundLayer.tileElements.length;

        System.out.println(viewSize);
        System.out.println(mapSize);

        renderFloorImage(currX, currY);
    }

    /*
    TODO:
        - performance pode melhorar evitando de desenhar pilhas muito grandes.
            pode ser uma boa alternativa limitar o desenho da pilha somente para os ultimos
            X itens!
        - achar um jeito de guardar um desenho e so iterar pixels se o atual for diferente
            do anterior! (talvez seja possivel guardando isso em uma subImage!)
     */
    public void renderFloorImage(int x, int y) {
        int xx = x + viewSize < mapSize ? x : mapSize - viewSize;
        int yy = y + viewSize < mapSize ? y : mapSize - viewSize;

        //sempre que for desenhar a imagem e "resetada"
        image = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration()
                .createCompatibleImage(
                        viewSize * TS, viewSize * TS,
                        BufferedImage.TYPE_INT_ARGB);

        long s = System.currentTimeMillis();
        for (int i = 0, tw = 0; i < viewSize; i++, tw += TS) {
            for (int j = 0, th = 0; j < viewSize; j++, th += TS) {
                //pinta o chao
                for (int currContent : groundLayer.tileElements[i + xx][j + yy]) {
                    paintContentPixels(currContent, tw, th);
                }

                if (!objectLayer.tileElements[i + xx][j + yy].isEmpty()) {
                    //pinta os objetos que estiverem sobre o chao (mesmo lugar)
                    for (int currContent : objectLayer.tileElements[i + xx][j + yy]) {
                        if (currContent > 0) {
                            paintContentPixels(currContent, tw, th);
                        }
                    }
                }
            }
        }
        D.d(getClass(), "Imagem do andar de ID [" + ID + "] foi desenhada em " + (System.currentTimeMillis() - s) + "ms!!!!");
    }

    private void paintContentPixels(int targetContentValue, int tw, int th) {
        for (Spr.Pixel p : SPR.getSpriteInfo(SPR.spriteAddresses.get(targetContentValue))) {
            image.setRGB(p.x + tw, p.y + th, p.color.getRGB());
        }
    }

    private static void copyImgToAnotherAt(BufferedImage src, BufferedImage dst, int dx, int dy) {
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
