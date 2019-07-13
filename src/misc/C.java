package misc;

import parsers.Spr;

import java.awt.*;
import java.io.IOException;

/*
"CONSTANTES"
 */
public class C {

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
            SPR = new Spr(C.SPR_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Deprecated
    public static final Color COLOR_SELECTED_TILE = new Color(0, 255, 150, 125);
}
