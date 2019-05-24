package com.extractor;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Parser {

    private byte[] bytesDoArquivo;
    private int numSprites;

    private ArrayList<Long> spriteAddresses;
    private ArrayList<Pixel> spriteInfo;

    public Parser() throws IOException {
        bytesDoArquivo = Files.readAllBytes(Paths.get("src/assets/tibia-8.6.spr"));
        spriteAddresses = new ArrayList<>();
        spriteInfo = new ArrayList<>();

        getNumSprites();
        getSpritesAddresses();
        //getSpriteInfo(spriteAddresses.get(1)); // para testes..
    }

    public int getNumSprites() {
        if (numSprites == 0) {
            numSprites = getUint16(
                    new byte[]{
                            bytesDoArquivo[4],
                            bytesDoArquivo[5]
                    });
        }

        return numSprites;
    }

    public ArrayList<Long> getSpritesAddresses() {
        if (spriteAddresses.isEmpty()) {
            for (int i = 0; i < numSprites; i += 4) {
                spriteAddresses.add(getUint32(
                        new byte[]{
                                bytesDoArquivo[i + 6],
                                bytesDoArquivo[i + 7],
                                bytesDoArquivo[i + 8],
                                bytesDoArquivo[i + 9]
                        }));
            }
        }

        return spriteAddresses;
    }

    public ArrayList<Pixel> getSpriteInfo(long endereco) {
        long enderecoInicial = (endereco + 3);

        long ultimoPixel = enderecoInicial + (getUint16(
                new byte[]{
                        bytesDoArquivo[(int) enderecoInicial],
                        bytesDoArquivo[(int) (enderecoInicial + 1)]
                }));
        int tamanho = 32;
        long atual = 0;
        long enderecoAtual = (enderecoInicial + 2);

        while (enderecoAtual < ultimoPixel) {
            long numPixelsTransparentes = getUint16(
                    new byte[]{
                            bytesDoArquivo[(int) enderecoAtual],
                            bytesDoArquivo[(int) (enderecoAtual + 1)]
                    }
            );

            enderecoAtual += 2;

            long numPixelsColoridos = getUint16(
                    new byte[]{
                            bytesDoArquivo[(int) enderecoAtual],
                            bytesDoArquivo[(int) (enderecoAtual + 1)]
                    }
            );

            enderecoAtual += 2;

            atual += numPixelsTransparentes;
            System.out.println(atual);

            for (int i = 0; i < numPixelsColoridos; i++) {
                Pixel pixel = new Pixel(
                        (int) (atual % tamanho),
                        (int) (atual / tamanho),
                        //TODO: isso aqui precisa ser corrigido...
                        new Color(
                                getUint8(new byte[]{bytesDoArquivo[(int) enderecoAtual++]}),
                                getUint8(new byte[]{bytesDoArquivo[(int) enderecoAtual++]}),
                                getUint8(new byte[]{bytesDoArquivo[(int) enderecoAtual++]}),
                                255
                        )
                );

                //                        new int[]{
//                                getUint8(new byte[]{bytesDoArquivo[(int) enderecoAtual++]}),
//                                getUint8(new byte[]{bytesDoArquivo[(int) enderecoAtual++]}),
//                                getUint8(new byte[]{bytesDoArquivo[(int) enderecoAtual++]}),
//                                255
                //}

                atual++;
                spriteInfo.add(pixel);
            }
        }

        return spriteInfo;
    }

    public byte getUint8(byte[] buffer) {
        return ByteBuffer
                .wrap(buffer)
                .order(ByteOrder.LITTLE_ENDIAN)
                .get();
    }

    /**
     * retorna 1 (um) INT unsigned correspondente aos bytesDoArquivo repassados.
     *
     * @param buffer
     * @return
     */
    public int getUint16(byte[] buffer) {
        return (int) (char)
                (ByteBuffer
                        .wrap(buffer)
                        .order(ByteOrder.LITTLE_ENDIAN)
                        .getShort());
    }

    /**
     * retorna 1 (um) LONG correspondente aos bytesDoArquivo repassados.
     *
     * @param buffer
     * @return
     */
    public long getUint32(byte[] buffer) {
        return ByteBuffer
                .wrap(buffer)
                .order(ByteOrder.LITTLE_ENDIAN)
                .getInt() & 0xFFFFFFFFL;
    }

    public static void main(String[] args) throws IOException {
        Parser p = new Parser();
    }

    private class Pixel {
        int x, y;
        //int[] rgba;
        Color color;

        public Pixel(int x, int y, Color color) {
            this.x = x;
            this.y = y;
            this.color = color;
        }
    }

    public static class SpriteBuilder {

        public static BufferedImage imagemSprite(int endereco) throws IOException {
            Parser parser = new Parser();
            ArrayList<Pixel> spriteInfo = parser.getSpriteInfo(parser.spriteAddresses.get(endereco));
            BufferedImage img = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
            for (Pixel p : spriteInfo) img.setRGB(p.x, p.y, p.color.getRGB());
            return img;
        }
    }
}
