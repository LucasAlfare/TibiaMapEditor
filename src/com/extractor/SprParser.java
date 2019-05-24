package com.extractor;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Eu fiz essa classe em java baseado em ensinamentos
 * encontrados pela net e em um arquivo em JavaScript
 * que encontrei nesse link:
 * https://github.com/nawarian/TibiaSprParser/blob/master/Tibia.Spr.Parser.js
 * <p>
 * Para funcionar precisei adaptar varias coisas, sobretudo a forma de
 * leitura dos "bytes unsigned", pois tava muito complicado...
 */
@SuppressWarnings("WeakerAccess")
public class SprParser {

    private byte[] bytesDoArquivo;
    private int numSprites;

    private ArrayList<Long> spriteAddresses;
    private ArrayList<Pixel> spriteInfo;

    /**
     * Cria um parser e, de cara, carrega todos os campos...
     *
     * @throws IOException se nao encontrar um arquivo chamado
     *                     "tibia-8.6.spr" (vou alterar isso dps)
     */
    public SprParser() throws IOException {
        bytesDoArquivo = Files.readAllBytes(Paths.get("src/assets/tibia-8.6.spr"));
        spriteAddresses = new ArrayList<>();
        spriteInfo = new ArrayList<>();

        /*
         * carrega tudo na instancia do objeto..
         */
        getNumSprites();
        getEnderecosSprites();
    }

    /**
     * Serve pra pegar o numero total de desenhos no arquivo.
     * <p>
     * Esse numero e encontrado exatamente no buffer do segundo
     * "offset" dos bytes do arquivo .spr
     *
     * @return um numero...
     */
    public int getNumSprites() {
        if (numSprites == 0) {
            numSprites = lerShort(4);
        }

        return numSprites;
    }

    /**
     * Esse metodo serve pra mapear os offsets de todas as sprites
     * contidas no arquivo .spr. Assim, sempre que for necessario
     * buscar por uma sprite nao sera necessario percorrer o arquivo
     * completo, bastando apenas buscar a referencia para tal nesta
     * lista.
     *
     * @return uma lista de longs onde cada um e um long q representa
     * offset de uma sprite...
     */
    public ArrayList<Long> getEnderecosSprites() {
        if (spriteAddresses.isEmpty()) {
            for (int i = 0; i < numSprites; i += 4) {
                spriteAddresses.add(lerInt(i + 6));
            }
        }

        return spriteAddresses;
    }

    /**
     * Esse metodo e resposavel por capturar todos os dados de uma sprite.
     * <p>
     * Basicamente este metodo faz a busca dos valores dos bytes a partir
     * do endereco repassado.
     *
     * @param endereco
     * @return uma lista contendo todos os pixels de uma sprite...
     */
    public ArrayList<Pixel> getSpriteInfo(long endereco) {
        int tamanho = 32;
        long enderecoInicial = (endereco + 3);
        long ultimoPixel = enderecoInicial + (lerShort((int) enderecoInicial));
        long atual = 0;
        long enderecoAtual = (enderecoInicial + 2);

        while (enderecoAtual < ultimoPixel) {
            long numPixelsTransparentes = lerShort((int) enderecoAtual);
            enderecoAtual += 2;
            long numPixelsColoridos = lerShort((int) enderecoAtual);
            enderecoAtual += 2;

            atual += numPixelsTransparentes;
            for (int i = 0; i < numPixelsColoridos; i++) {
                Pixel pixel = new Pixel(
                        (int) (atual % tamanho),
                        (int) (atual / tamanho),
                        new Color(getIntFromColor(
                                lerByte((int) enderecoAtual++),
                                lerByte((int) enderecoAtual++),
                                lerByte((int) enderecoAtual++)
                        ))
                );

                atual++;
                spriteInfo.add(pixel);
            }
        }

        return spriteInfo;
    }


    /**
     * Fonte: https://stackoverflow.com/a/18037185
     * <p>
     * serve pra converter os 3 numeros INT em um unico INT
     *
     * @param red
     * @param green
     * @param blue
     * @return
     */
    private static int getIntFromColor(int red, int green, int blue) {
        red = (red << 16) & 0x00FF0000; //Shift red 16-bits and mask out other stuff
        green = (green << 8) & 0x0000FF00; //Shift green 8-bits and mask out other stuff
        blue = blue & 0x000000FF; //Mask out anything not blue.

        return 0xFF000000 | red | green | blue; //0xFF000000 for 100% Alpha. Bitwise OR everything together.
    }

    /**
     * Esse metodo auxiliar retorna um novo array contendo apenas
     * um trecho de um outro array. Isso e literalmente o tal "buffer".
     *
     * @param fonte
     * @param inicio
     * @param tamanho
     * @return um buffer...
     */
    private static byte[] bufferDe(byte[] fonte, int inicio, int tamanho) {
        byte[] r = new byte[tamanho];
        System.arraycopy(fonte, inicio, r, 0, r.length);
        return r;
    }

    /**
     * Usado no lugar de "getUint8()", do JS
     *
     * @param inicio
     * @return
     */
    private byte lerByte(int inicio) {
        return ByteBuffer
                .wrap(bufferDe(bytesDoArquivo, inicio, 1))
                .order(ByteOrder.LITTLE_ENDIAN)
                .get();
    }

    /**
     * retorna 1 (um) INT unsigned correspondente aos bytesDoArquivo repassados.
     * <p>
     * usado no lugar de "getUint16()"
     *
     * @param
     * @return
     */
    private int lerShort(int inicio) {
        return (int) (char)
                (ByteBuffer
                        .wrap(bufferDe(bytesDoArquivo, inicio, 2))
                        .order(ByteOrder.LITTLE_ENDIAN)
                        .getShort());
    }

    /**
     * retorna 1 (um) LONG correspondente aos bytesDoArquivo repassados.
     * <p>
     * usado no lugar de "getUint32()"
     *
     * @param
     * @return
     */
    private long lerInt(int inicio) {
        return ByteBuffer
                .wrap(bufferDe(bytesDoArquivo, inicio, 4))
                .order(ByteOrder.LITTLE_ENDIAN)
                .getInt() & 0xFFFFFFFFL;
    }

    /**
     * Dataclass usada pra segurar os parametros que representam 1 pixel.
     */
    private class Pixel {
        int x, y;
        Color color;

        public Pixel(int x, int y, Color color) {
            this.x = x;
            this.y = y;
            this.color = color;
        }
    }

    /**
     * Usado pra criar uma imagem de uma sprite..
     */
    public static class SpriteBuilder {

        /**
         * Cria o BufferedImage de uma sprite a partir de uma lista de pixels.
         * <p>
         * A sprite a ser desenhada e definida a partir do valor do parametro
         * endereco. OK
         *
         * @param endereco
         * @return um ufferedImage....
         * @throws IOException
         */
        public static BufferedImage imagemSprite(int endereco) throws IOException {
            SprParser sprParser = new SprParser();
            ArrayList<Pixel> spriteInfo = sprParser.getSpriteInfo(sprParser.spriteAddresses.get(endereco));
            BufferedImage img = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
            for (Pixel p : spriteInfo) img.setRGB(p.x, p.y, p.color.getRGB());
            return img;
        }
    }
}
