package com.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class JMapEditorCanvas extends JComponent implements Scrollable {

    private int w, h;
    private int[][] tiles;

    private int currX, currY;
    public int currentSelectedID = 253; //TODO: permitir usuário alterar esse valor

    private Dimension preferredScrollableViewportSize;

    public JMapEditorCanvas(int w, int h) {
        this.w = w;
        this.h = h;

        tiles = new int[w][h];
        for (int[] tile : tiles) {
            Arrays.fill(tile, -1);
        }

        this.setPreferredScrollableViewportSize(new Dimension((w+1) * 32, (h+1) * 32));

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent mouseEvent) {
                super.mouseDragged(mouseEvent);
                Point p = mouseEvent.getPoint();
                int i = p.x/32; int j = p.y/32;
                if ((i >= 0 && j >= 0) && (i < JMapEditorCanvas.this.w && j < JMapEditorCanvas.this.h)){
                    tiles[p.x/32][p.y/32] = currentSelectedID;
                    repaint();
                }
            }

            @Override
            public void mouseMoved(MouseEvent mouseEvent) {
                super.mouseMoved(mouseEvent);
                Point p = mouseEvent.getPoint();
                int i = p.x/32; int j = p.y/32;
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
                Point p = mouseEvent.getPoint();
                int i = p.x/32; int j = p.y/32;
                if ((i >= 0 && j >= 0) && (i < JMapEditorCanvas.this.w && j < JMapEditorCanvas.this.h)){
                    tiles[p.x/32][p.y/32] = currentSelectedID;
                    repaint();
                }
            }
        });
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(w * 32, h * 32);
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

    private void atualizar(Graphics2D g) throws IOException {
        for (int i = 0; i < tiles.length * 32; i += 32) {
            for (int j = 0; j < tiles[i/32].length * 32; j += 32) {
                g.setColor(Color.LIGHT_GRAY);
                if (tiles[i/32][j/32] != -1){
                    g.drawImage(
                            ImageIO.read(new File("src/com/sprites/10304.gif")),
                            i,
                            j,
                            this
                    );
                } else {
                    g.fillRect(i, j, 32, 32);
                }
                g.setColor(Color.BLACK);
                g.drawRect(i, j, 32, 32);
            }
        }
    }

    private void mouseCursor(Graphics2D g) throws IOException {
        g.drawImage(
                imgW_ModedAlpha(ImageIO.read(new File("src/com/sprites/10304.gif")), 180),
                (currX / 32) * 32,
                (currY / 32) * 32,
                this);
    }

    private static BufferedImage imgW_ModedAlpha(BufferedImage modMe, double modAmount) {
        for (int x = 0; x < modMe.getWidth(); x++) {
            for (int y = 0; y < modMe.getHeight(); y++) {
                //
                int argb = modMe.getRGB(x, y); //always returns TYPE_INT_ARGB
                int alpha = (argb >> 24) & 0xff;  //isolate alpha

                alpha *= modAmount; //similar distortion to tape saturation (has scrunching effect, eliminates clipping)
                alpha &= 0xff;      //keeps alpha in 0-255 range

                argb &= 0x00ffffff; //remove old alpha info
                argb |= (alpha << 24);  //add new alpha info
                modMe.setRGB(x, y, argb);
            }
        }

        return modMe;
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
        return 32;
    }

    @Override
    public int getScrollableBlockIncrement(Rectangle rectangle, int i, int i1) {
        return 32;
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
