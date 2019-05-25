package com.gui;

import com.parsers.Spr;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class JMapEditorCanvas extends JComponent implements Scrollable {

    private int w, h;
    private int[][] tiles;
    public static final int T_SIZE = 32;

    private int currX, currY;
    private int currentSelectedID = 1, lastID; //TODO: permitir usuário alterar esse valor
    private BufferedImage currentImg;

    private Dimension preferredScrollableViewportSize;

    private boolean ctrlPressed = true;
    private ArrayList<double[]> currentBounds;
    private Shape s;

    private Spr spr;

    public JMapEditorCanvas(int w, int h) throws IOException {
        this.w = w;
        this.h = h;

        tiles = new int[w][h];
        for (int[] tile : tiles) {
            Arrays.fill(tile, -1);
        }

        currentBounds = new ArrayList<>();
        s = null;
        System.out.println("Starting loading Spr...");
        long start = System.currentTimeMillis();
        spr = new Spr("src/assets/tibia-8.6.spr");
        System.out.println((System.currentTimeMillis() - start) + " milliseconds to load Spr.");

        currentImg = spr.imagemSprite(currentSelectedID);
        lastID = currentSelectedID;

        this.setPreferredScrollableViewportSize(new Dimension((w+1) * T_SIZE, (h+1) * T_SIZE));

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent mouseEvent) {
                super.mouseDragged(mouseEvent);
                s = null;

                if (ctrlPressed){
                    currentBounds.add(new double[]{
                            mouseEvent.getPoint().getX() / T_SIZE,
                            mouseEvent.getPoint().getY() / T_SIZE
                    });
                }

                Point p = mouseEvent.getPoint();
                int i = p.x/ T_SIZE; int j = p.y/ T_SIZE;
                if ((i >= 0 && j >= 0) && (i < JMapEditorCanvas.this.w && j < JMapEditorCanvas.this.h)){
                    tiles[p.x/ T_SIZE][p.y/ T_SIZE] = currentSelectedID;
                    repaint();
                }
            }

            @Override
            public void mouseMoved(MouseEvent mouseEvent) {
                super.mouseMoved(mouseEvent);
                s = null;
                Point p = mouseEvent.getPoint();
                int i = p.x/ T_SIZE; int j = p.y/ T_SIZE;
                if ((i >= 0 && j >= 0) && (i < JMapEditorCanvas.this.w && j < JMapEditorCanvas.this.h)){
                    currX = p.x;
                    currY = p.y;
                    repaint();
                }
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                super.mousePressed(mouseEvent);
                s = null;
                Point p = mouseEvent.getPoint();
                int i = p.x/ T_SIZE; int j = p.y/ T_SIZE;
                if ((i >= 0 && j >= 0) && (i < JMapEditorCanvas.this.w && j < JMapEditorCanvas.this.h)){
                    tiles[p.x/ T_SIZE][p.y/ T_SIZE] = currentSelectedID;
                    repaint();
                }
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                super.mouseReleased(mouseEvent);
                if (ctrlPressed){
                    s = boundsPolygon();
                    currentBounds.clear();
                    repaint();
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D gg = (Graphics2D) g;
        try {
            atualizar(gg);
            mouseCursor(gg);
            gg.dispose();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(w * T_SIZE, h * T_SIZE);
    }

    private void atualizar(Graphics2D g) throws IOException {
        //TODO: mover isso para o metodo q ira alterar o ID
        if (currentSelectedID != lastID){
            currentImg = spr.imagemSprite(currentSelectedID);
        }

        for (int i = 0; i < tiles.length * T_SIZE; i += T_SIZE) {
            for (int j = 0; j < tiles[i/ T_SIZE].length * T_SIZE; j += T_SIZE) {
                int ix = i/ T_SIZE, jy = j/ T_SIZE;

                if (s != null){
                    if (s.contains(ix, jy)){
                        tiles[ix][jy] = currentSelectedID;
                    }
                }

                g.setColor(Color.LIGHT_GRAY);
                g.fillRect(i, j, T_SIZE, T_SIZE);
                if (tiles[ix][jy] != -1){
                    //desenha o ID selecionado
                    g.drawImage(
                            currentImg,
                            i,
                            j,
                            this
                    );
                }
                g.setColor(Color.BLACK);
                g.drawRect(i, j, T_SIZE, T_SIZE);
            }
        }
    }

    private Path2D.Double boundsPolygon(){
        Path2D.Double p = new Path2D.Double();
        if (!currentBounds.isEmpty()){
            p.moveTo(currentBounds.get(0)[0], currentBounds.get(0)[1]);
            for (int i = 1; i < currentBounds.size(); i++) {
                p.lineTo(currentBounds.get(i)[0], currentBounds.get(i)[1]);
            }
            p.closePath();
        }
        return p;
    }

    private void mouseCursor(Graphics2D g) {
        g.drawImage(
                currentImg,
                (currX / T_SIZE) * T_SIZE,
                (currY / T_SIZE) * T_SIZE,
                this);
    }

    @Override
    public Dimension getPreferredScrollableViewportSize() {
        return preferredScrollableViewportSize;
    }

    public void setPreferredScrollableViewportSize(Dimension size) {
        this.preferredScrollableViewportSize = size;
    }

    @Override
    public int getScrollableUnitIncrement(Rectangle rectangle, int i, int i1) {
        return T_SIZE;
    }

    @Override
    public int getScrollableBlockIncrement(Rectangle rectangle, int i, int i1) {
        return T_SIZE;
    }

    @Override
    public boolean getScrollableTracksViewportWidth() {
        return false;
    }

    @Override
    public boolean getScrollableTracksViewportHeight() {
        return false;
    }
}
