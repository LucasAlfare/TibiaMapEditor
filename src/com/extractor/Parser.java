package com.extractor;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Parser {

    RandomAccessFile raf;
    byte[] bytes;
    int numSprites;

    ArrayList<Long> spriteAddresses;
    ArrayList<Pixel> spriteInfo;

    public Parser() throws IOException {
        bytes = Files.readAllBytes(Paths.get("src/assets/tibia-8.6.spr"));
        spriteAddresses = new ArrayList<>();
        spriteInfo = new ArrayList<>();

        getNumSprites();
        getSpritesAddresses();
        getSpriteInfo(spriteAddresses.get(1));
    }

    public int getNumSprites() {
        if (numSprites == 0) {
            numSprites = getUint16(new byte[]{bytes[4], bytes[5]});
        }

        return numSprites;
    }

    public ArrayList<Long> getSpritesAddresses() {
        if (spriteAddresses.isEmpty()) {
            for (int i = 0; i < numSprites; i += 4) {
                spriteAddresses.add(getUint32(
                        new byte[]{
                                bytes[i + 6],
                                bytes[i + 7],
                                bytes[i + 8],
                                bytes[i + 9]}));
            }
        }

        return spriteAddresses;
    }

    public ArrayList<Pixel> getSpriteInfo(long address) {
        long startingAddress = (address + 3);

        //System.out.println(startingAddress);

        long lastPixel = startingAddress + (getUint16(new byte[]{bytes[(int) startingAddress], bytes[(int) (startingAddress + 1)]}));
        int size = 32;
        long current = 0;
        long currentAddress = (startingAddress + 2);
        //System.out.println(currentAddress);

        while (currentAddress < lastPixel) {
            long transparentPixelsNum = getUint16(new byte[]{bytes[(int) currentAddress], bytes[(int) (currentAddress + 1)]});
            //System.out.println(transparentPixelsNum);

            currentAddress += 2;

            long coloredPixelsNum = getUint16(new byte[]{bytes[(int) currentAddress], bytes[(int) (currentAddress + 1)]});
            //System.out.println(coloredPixelsNum);

            currentAddress += 2;

            current += transparentPixelsNum;
            System.out.println(current);

            for (int i = 0; i < coloredPixelsNum; i++) {
                Pixel pixel = new Pixel(
                        (int)(current % size),
                        (int)(current / size),
                        new int[]{
                                currentAddress++
                        }
                        );
            }

            break;
        }

        return spriteInfo;
    }

    /**
     * retorna 1 (um) INT unsigned correspondente aos bytes repassados.
     *
     * @param buffer
     * @return
     */
    public int getUint16(byte[] buffer) {
        return (int) (char)
                (ByteBuffer
                        .wrap(buffer)
                        .order(ByteOrder.LITTLE_ENDIAN)
                        .getShort());
    }

    /**
     * retorna 1 (um) LONG correspondente aos bytes repassados.
     *
     * @param buffer
     * @return
     */
    public long getUint32(byte[] buffer) {
        return ByteBuffer
                .wrap(buffer)
                .order(ByteOrder.LITTLE_ENDIAN)
                .getInt() & 0xFFFFFFFFL;
    }

    public static void main(String[] args) throws IOException {
        Parser p = new Parser();
    }

    class Pixel {
        int x, y;
        int[] rgba;

        public Pixel(int x, int y, int[] rgba) {
            this.x = x;
            this.y = y;
            this.rgba = rgba;
        }
    }
}
