package tt;

import com.extractor.SprParser;

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
            //graphics.drawImage(SprParser.SpriteBuilder.imagemSprite(50), 50, 50, null);
        }
    }

    public static void main(String[] args) {
        new M2();
    }
}
