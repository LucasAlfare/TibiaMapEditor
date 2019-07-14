package com.misc;

import com.parsers.Spr;

import java.awt.*;
import java.io.IOException;

/*
"GLOBAIS"
 */
public class G {

    /**
     * tamanho de um TileElement padrão
     */
    public static final int TS = 32;

    /**
     * Caminho para o arquivo .spr padrão do projeto
     */
    public static final String SPR_PATH = "src/assets/tibia-8.6.spr";

    /**
     * Parser de sprites padrão para ser usado durante todas as operações
     */
    public static Spr SPR;

    /*
     * bloco de inicialização estática para atribuir valor ao Parser de sprites padrão
     */
    static {
        try {
            SPR = new Spr(G.SPR_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static final Color COLOR_SELECTED_TILE = new Color(0, 255, 150, 125);

    public static int mapSize = 100, viewSize = 25;
}
