package com.extractor;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TibiaSpritesExtractor {

    public static void main(String[] args) throws IOException {
        extract();
    }

    public static void extract() throws IOException {
        byte[] sprBytes = Files.readAllBytes(Paths.get("src/assets/tibia-8.6.spr"));

        System.out.println(Math.abs(ByteBuffer.wrap(new byte[]{
                sprBytes[12],
                sprBytes[13]
        }).getShort()));
        //System.out.println(new ByteBuffer.wrap(new byte[]{sprBytes[4], sprBytes[5]}).intValue() << 32 - 3 >> 32 - 3);
    }

    public static Image getSpriteImage(int spriteId) throws IOException {
        int size = 32;
        BufferedImage bitmap = new BufferedImage(size, size, BufferedImage.TYPE_4BYTE_ABGR);

        //byte[] sprBytes = Files.readAllBytes(Paths.get("src/com/extractor/tibia-8.6.spr"));

        return bitmap;
    }
}
