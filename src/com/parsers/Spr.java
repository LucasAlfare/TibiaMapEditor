package com.parsers;

import com.misc.D;

import java.awt.*;
import java.io.IOException;
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
public class Spr extends ParserBase {

    private int numSprites;

    public ArrayList<Long> spriteAddresses;
    private ArrayList<Pixel> spriteInfo;

    /**
     * Cria um parser e, de cara, carrega todos os campos...
     *
     * @throws IOException se nao encontrar um arquivo chamado
     *                     "tibia-8.6.spr" (vou alterar isso dps)
     */
    public Spr(String filePath) throws IOException {
        super(filePath);

        spriteAddresses = new ArrayList<>();
        spriteInfo = new ArrayList<>();

        /*
         * carrega tudo na instancia DESTE objeto..
         */
        getNumSprites();
        D.d(getClass(), "Loading sprites addresses...");
        long a = System.currentTimeMillis();
        getEnderecosSprites();
        D.d(getClass(), "Sprites addresses loaded in " + (System.currentTimeMillis() - a) + "ms.");
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
     * Esse metodo e resposavel por capturar todos os dados de UMA sprite.
     * <p>
     * Basicamente este metodo faz a busca dos valores dos bytes a partir
     * do endereco repassado.
     *
     * @param endereco
     * @return uma lista contendo todos os pixels de uma sprite...
     */
    public ArrayList<Pixel> getSpriteInfo(long endereco) {
        spriteInfo.clear();
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

        resetSeeker();
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
     * Dataclass usada pra segurar os parametros que representam 1 pixel.
     */
    public class Pixel {
        public int x, y;
        public Color color;

        public Pixel(int x, int y, Color color) {
            this.x = x;
            this.y = y;
            this.color = color;
        }
    }
}
