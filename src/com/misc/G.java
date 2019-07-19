package com.misc;

import com.MCoordinate;
import com.parsers.Spr;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/*
"GLOBAIS"
 */
public class G {

    /**
     * Caminho relativo para o arquivo .spr padrão do projeto
     */
    public static final String SPR_PATH = "src/com/assets/tibia-8.6.spr";

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

    /**
     * tamanho padrão de 1 TILE (quadrado)
     */
    public static int ts = 32;

    public static int mapSize = 100, viewSize = 20;
    public static MCoordinate currentViewCoordinate = new MCoordinate(0, 0);
    public static int targetX, targetY;

    public static BufferedImage viewImg;
}
