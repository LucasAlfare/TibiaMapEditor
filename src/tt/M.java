package tt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;
import java.util.ArrayList;
import java.util.Arrays;

public class M extends JFrame {

    public M(){
        setSize((20 * 32) + 20, (20 * 32) + 20);
        setDefaultCloseOperation(3);
        setLocationRelativeTo(null);

        C c = new C();
        add(c);
        setVisible(true);
    }

    public static void main(String[] args) {
        new M();
    }

    class C extends JComponent {

        int[][] kk = new int[20][20];
        ArrayList<double[]> contorno;

        public C(){
            for (int[] ints : kk) {
                Arrays.fill(ints, -1);
            }

            contorno = new ArrayList<>();

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent mouseEvent) {
                    super.mouseClicked(mouseEvent);
                    f5(mouseEvent.getPoint());
                    M.this.repaint();
                }

                @Override
                public void mouseReleased(MouseEvent mouseEvent) {
                    super.mouseReleased(mouseEvent);
                    M.this.repaint();
                }
            });

            addMouseMotionListener(new MouseMotionAdapter() {
                @Override
                public void mouseDragged(MouseEvent mouseEvent) {
                    super.mouseDragged(mouseEvent);
                    f5(mouseEvent.getPoint());
                    contorno.add(new double[]{
                            mouseEvent.getPoint().getX() / 32,
                            mouseEvent.getPoint().getY() / 32
                    });
                }
            });
        }

        public void f5(Point p){
            kk[p.x/32][p.y/32] = 0;
            //repaint();
        }

        public Path2D poligonoDoContorno(Graphics2D g){
            Path2D p = new Path2D.Double();
            if (!contorno.isEmpty()){
                p.moveTo(contorno.get(0)[0] * 32, contorno.get(0)[1] * 32);
                for (int i = 1; i < contorno.size(); i++) {
                    p.lineTo(contorno.get(i)[0] * 32, contorno.get(i)[1] * 32);
                }
                p.closePath();
                g.setColor(Color.BLACK);
                g.draw(p);
                contorno.clear();
            }
            return p;
        }

        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            Graphics2D g = (Graphics2D) graphics;

            Path2D p = poligonoDoContorno(g);

            for (int i = 0; i < kk.length * 32; i += 32) {
                for (int j = 0; j < kk[i/32].length * 32; j += 32) {
                    if (p.contains(i, j)){
                        kk[i/32][j/32] = 0;
                    }

                    if (kk[i/32][j/32] == -1){
                        g.setColor(Color.LIGHT_GRAY);
                    } else {
                        g.setColor(Color.CYAN);
                    }

                    g.fillRect(i, j, 32, 32);
                    g.setColor(Color.BLACK);
                    g.drawRect(i, j, 32, 32);
                }
            }
        }
    }
}
