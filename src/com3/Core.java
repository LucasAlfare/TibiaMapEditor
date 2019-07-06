package com3;

import parsers.Spr;

import java.awt.*;

/**
 * A ideia dessa classe é gerenciar os itens gerais, no caso
 * todos os itens em questao devem estar aqui. Além desses
 * esta classe ira lidar com os itens que deverao ser exibidos.
 * <p>
 * Por exemplo, se tivermos uma grade 3x3 (9 slots) e uma grade de visualização
 * máxima 2x2 (4 slots), esta classe irá disponibilizar, publicamente, uma grade
 * 2x2 mostrando 4 slots pertecentes à grade principal, considerando
 * as coordenadas repassadas. No exemplo, poderiamos tentar ver
 * a partir das coordenadas [x = 1, y = 0], poderíamos obter:
 * <p>
 * grade principal (suposição):
 * 0 1 2
 * 3 4 5
 * 6 7 8
 * <p>
 * visualização em [1,0]:
 * 1 2
 * 4 5
 */
public class Core {

    /**
     * Array para guardar conteudo principal.
     * Por padrao, itens vazios serao considerados
     * como o valor -1.
     */
    private int[][] mainContent;

    //Campo para guardar a quantidade de itens a ser mostrada (lado).
    public int l;

    //Array para guardar apenas os elementos que serao mostrados.
    private int[][] currView;

    public int currX = 0, currY = 0;

    public Spr spr;

    /**
     * Cria um core com o conteudo repassado que tem de tamanho
     * de visualização l.
     *
     * @param l tamanho do lado
     */
    public Core(int[][] mainContent, int l, Spr spr) {
        this.mainContent = mainContent;
        this.l = l;
        this.spr = spr;
        this.currView = new int[l][l];
        setCurrView(currX, currY);
    }

    public void setCurrView(int x, int y) {
        x = x < 0 ? 0 : x;
        y = y < 0 ? 0 : y;

        int xx = (x + l < mainContent.length) ? x : mainContent.length - l;
        int yy = (y + l < mainContent.length) ? y : mainContent.length - l;

        //TODO: fazer com que esse loop só aconteça se as coordenadas forem OK
        for (int i = 0; i < l; i++) {
            System.arraycopy(mainContent[xx + i], yy, currView[i], 0, l);
        }
    }

    /**
     * Define o valor de um slot para o valor vazio padrão (-1).
     *
     * @param x
     * @param y
     */
    public void clearSlot(int x, int y) {
        //checa se as coordenadas são válidas antes de definir
        setSlotValue(-1, x, y);
    }

    /**
     * Define o valor de um slot para o valor repassado, desde
     * que as coordenadas estejam nos limites da grade principal.
     *
     * @param slotValue
     * @param x
     * @param y
     */
    public void setSlotValue(int slotValue, int x, int y) {
        if (isValid(x, y)) {
            mainContent[x][y] = slotValue;
        }
    }

    /**
     * retorna se uma coordenada está dentro dos limites da
     * grade principal.
     *
     * @param x
     * @param y
     * @return true se esta tudo certo e false se não está no limite.
     */
    public boolean isValid(int x, int y) {
        return (x >= 0 && x < mainContent.length) &&
                (y >= 0 && y < mainContent.length);
    }

    /**
     * Retorna conteudo a partir da grade de visualizacao, e nao da grade
     * principal, ok?
     *
     * @param x
     * @param y
     * @return
     */
    public Image getSpriteImageFrom(int x, int y) {
        return spr.spriteImage(currView[x][y]);
    }

    /**
     * Retorna a visualização principal.
     *
     * @return
     */
    public int[][] getCurrView() {
        return currView;
    }

    public void setL(int l) {
        this.l = l;
    }

    /**
     * Serve só pra debugar no console.
     *
     * @return uma representação da visualização atual.
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("\n");
        s.append("[x=").append(currX).append(", y=").append(currY).append("]").append("\n");
        for (int i = 0; i < currView.length; i++) {
            for (int j = 0; j < currView[i].length; j++) {
                s.append(currView[i][j]).append(" ");
            }
            s.append("\n");
        }

        return "curr view (l=" + l + "): " + s + "\n";
    }
}
