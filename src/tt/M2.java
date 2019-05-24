package tt;

import com.extractor.Parser;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class M2 extends JFrame {

    public M2() {
        setSize(400, 400);
        setDefaultCloseOperation(3);
        setLocationRelativeTo(null);

        add(new K());

        setVisible(true);
    }

    class K extends JComponent {

        @Override
        protected void paintComponent(Graphics graphics) {
            super.paintComponent(graphics);
            try {
                graphics.drawImage(Parser.SpriteBuilder.imagemSprite(1), 50, 50, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new M2();
    }
}
