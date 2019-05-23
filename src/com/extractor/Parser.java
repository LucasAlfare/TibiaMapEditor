package com.extractor;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Parser {

    //BinaryReader b;
    RandomAccessFile raf;
    byte[] bytes;
    int numSprites;

    ArrayList<Integer> addresses;
    ArrayList<Pixel> spriteInfo;

    public Parser() throws IOException {
        bytes = Files.readAllBytes(Paths.get("src/assets/tibia-8.6.spr"));
        //b = new BinaryReader(new ByteArrayInputStream(bytes));
        addresses = new ArrayList<>();
        spriteInfo = new ArrayList<>();
        raf = new RandomAccessFile(Paths.get("src/assets/tibia-8.6.spr").toFile(), "r");

        getNumSprites();

        System.out.println(numSprites);
    }

    public static void main(String[] args) throws IOException {
        Parser p = new Parser();
    }

    public int getNumSprites() throws IOException {
        if (numSprites == 0){
            raf.seek(4);
            numSprites = raf.read();
        }

        return numSprites;
    }

    public ArrayList<Integer> getAddresses() {
        return addresses;
    }

    public ArrayList<Pixel> getSpriteInfo() {
        return spriteInfo;
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
