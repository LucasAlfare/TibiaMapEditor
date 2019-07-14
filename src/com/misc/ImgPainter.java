package com.misc;

import com.Floor;
import com.parsers.Spr;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import static com.misc.G.*;

@SuppressWarnings("ALL")
public class ImgPainter {

    /**
     * TODO:
     * - performance pode melhorar evitando de desenhar pilhas muito grandes.
     * pode ser uma boa alternativa limitar o desenho da pilha somente para os ultimos
     * X itens!
     * - achar um jeito de guardar um desenho e so iterar pixels se o atual for diferente
     * do anterior! (talvez seja possivel guardando isso em uma subImage!)
     */
    public static void paintFloorImage(int x, int y, Floor floor) {
        if (!floor.initied) {
            int xx = x + viewSize < mapSize ? x : mapSize - viewSize;
            int yy = y + viewSize < mapSize ? y : mapSize - viewSize;

            //sempre que for desenhar a imagem e "resetada"
            floor.image = rawImage(viewSize * TS, viewSize * TS);

            long s = System.currentTimeMillis();
            for (int i = 0, tw = 0; i < viewSize; i++, tw += TS) {
                for (int j = 0, th = 0; j < viewSize; j++, th += TS) {
                    //pinta o chao aaaaaa
                    for (int currContent : floor.groundLayer.tileElements[i + xx][j + yy]) {
                        paintContentPixels(floor.image, currContent, tw, th);
                    }

                    if (!floor.objectLayer.tileElements[i + xx][j + yy].isEmpty()) {
                        //pinta os objetos que estiverem sobre o chao (mesmo lugar)
                        for (int currContent : floor.objectLayer.tileElements[i + xx][j + yy]) {
                            if (currContent > 0) {
                                paintContentPixels(floor.image, currContent, tw, th);
                            }
                        }
                    }
                }
            }
            D.d(ImgPainter.class, "Imagem do andar de ID [" + 0 + "] foi pintada em " + (System.currentTimeMillis() - s) + "ms!!!!");
        }
    }

    public static void paintFloorImage2(int x, int y, Floor floor) {
        if (floor.initied) {
            System.out.println("foi chamado!");
            long s = System.currentTimeMillis();
            BufferedImage aux = floor.image.getSubimage(
                    floor.targetX * TS,
                    floor.targetY * TS,
                    floor.image.getWidth() - (floor.targetX == 1 ? TS : 0),
                    floor.image.getHeight() - (floor.targetY == 1 ? TS : 0));

            floor.image = rawImage(viewSize * TS, viewSize * TS);
            System.out.println("targets: " + floor.targetX + ", " + floor.targetY);
            floor.image
                    .getGraphics()
                    .drawImage(
                            aux,
                            floor.targetX * (floor.targetX == 0 ? TS : 0),
                            floor.targetY * (floor.targetY == 0 ? TS : 0),
                            null);

            int xx = x + viewSize < mapSize ? x : mapSize - viewSize;
            int yy = y + viewSize < mapSize ? y : mapSize - viewSize;

            if (floor.targetX == 0 && floor.targetY == 0) {
                System.out.println("if 1");
                for (int i = 0, tw = 0; i < 1; i++, tw += TS) {
                    for (int j = 0, th = 0; j < viewSize; j++, th += TS) {
                        //desenhar na posição [i][th]
                    }
                }
            } else if (floor.targetX == 0 && floor.targetY == 1) {
                System.out.println("if 2");
                for (int i = 0, tw = 0; i < viewSize; i++, tw += TS) {
                    for (int j = 0, th = 0; j < 1; j++, th += TS) {
                        //desenhar na posição [tw][viewSize-1]
                    }
                }
            } else if (floor.targetX == 1 && floor.targetY == 0) {
                System.out.println("if 3");
                //paintContentPixels((viewSize - 1) * TS, 0, floor.image, 1, 0, 0);
                for (int i = 0, tw = 0; i < 1; i++, tw += TS) {
                    for (int j = 0, th = 0; j < viewSize; j++, th += TS) {
                        //desenhar na posição [viewSize-1][th]
                        //pinta o chao aaaaaa
                        for (int currContent : floor.groundLayer.tileElements[i + xx][j + yy]) {
                            paintContentPixels((viewSize - 1) * TS, j * TS, floor.image, currContent, 0, 0);
                        }

                        if (!floor.objectLayer.tileElements[i + xx][j + yy].isEmpty()) {
                            //pinta os objetos que estiverem sobre o chao (mesmo lugar)
                            for (int currContent : floor.objectLayer.tileElements[i + xx][j + yy]) {
                                if (currContent > 0) {
                                    paintContentPixels((viewSize - 1) * TS, j * TS, floor.image, currContent, 0, 0);
                                }
                            }
                        }
                    }
                }
            } else { //[1, 1]
                System.out.println("else");
                for (int i = 0, tw = 0; i < viewSize; i++, tw += TS) {
                    for (int j = 0, th = 0; j < 1; j++, th += TS) {
                        //desenhar na posição [tw][viewSize-1]
                    }
                }

                for (int i = 0, tw = 0; i < 1; i++, tw += TS) {
                    for (int j = 0, th = 0; j < viewSize - 1; j++, th += TS) {
                        //desenhar na posição [viewSize-1][th]
                    }
                }
            }
            D.d(ImgPainter.class, "Imagem do andar de ID [" + 0 + "] foi pintada2 em " + (System.currentTimeMillis() - s) + "ms!!!!");
        }
    }

    private static void paintContentPixels(BufferedImage targetImage, int targetContentValue, int tw, int th) {
        paintContentPixels(0, 0, targetImage, targetContentValue, tw, th);
    }

    private static void paintContentPixels(int x, int y, BufferedImage targetImage, int targetContentValue, int tw, int th) {
        for (Spr.Pixel p : SPR.getSpriteInfo(SPR.spriteAddresses.get(targetContentValue))) {
            targetImage.setRGB(p.x + x + tw, p.y + y + th, p.color.getRGB());
        }
    }

    public static BufferedImage rawImage(int w, int h) {
        return GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration()
                .createCompatibleImage(w, h, BufferedImage.TYPE_INT_ARGB);
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
