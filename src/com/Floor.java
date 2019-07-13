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
    public int currX, currY, targetX, targetY;

    public int viewSize, mapSize;

    public Floor(Layer groundLayer, Layer objectLayer, int viewSize) {
        this.groundLayer = groundLayer;
        this.objectLayer = objectLayer;
        this.viewSize = viewSize;

        mapSize = groundLayer.tileElements.length;

        objectLayer.tileElements[0][0].add(2);
        objectLayer.tileElements[viewSize - 1][viewSize - 1].add(1);
        renderFloorImage2(currX, currY);
    }

    /**
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
        image = rawImage(viewSize * TS, viewSize * TS);

        long s = System.currentTimeMillis();
        for (int i = 0, tw = 0; i < viewSize; i++, tw += TS) {
            for (int j = 0, th = 0; j < viewSize; j++, th += TS) {
                //pinta o chao aaaaaa
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

    public void renderFloorImage2(int x, int y) {
        if (image == null) {
            renderFloorImage(x, y);
        } else {
            int xx = x + viewSize < mapSize ? x : mapSize - viewSize;
            int yy = y + viewSize < mapSize ? y : mapSize - viewSize;

            System.out.println("targets: " + targetX + "," + targetY);
            System.out.println("currents: " + currX + "," + currY);

            BufferedImage aux = rawImage(image.getWidth(), image.getHeight());

            aux.getGraphics()
                    .drawImage(image
                                    .getSubimage(
                                            targetX * TS,
                                            targetY * TS,
                                            image.getWidth() - (targetX == 1 ? TS : 0),
                                            image.getHeight() - (targetY == 1 ? TS : 0)),
                            0, 0, null);

            image = aux;
        }
    }

    private void paintContentPixels(int targetContentValue, int tw, int th) {
        paintContentPixels(image, targetContentValue, tw, th);
    }

    private void paintContentPixels(BufferedImage targetImage, int targetContentValue, int tw, int th) {
        for (Spr.Pixel p : SPR.getSpriteInfo(SPR.spriteAddresses.get(targetContentValue))) {
            targetImage.setRGB(p.x + tw, p.y + th, p.color.getRGB());
        }
    }

    private BufferedImage rawImage(int w, int h) {
        return GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration()
                .createCompatibleImage(
                        w, h,
                        BufferedImage.TYPE_INT_ARGB);
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
