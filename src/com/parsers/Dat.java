package com.parsers;

import java.io.IOException;

public class Dat extends ParserBase {

    public Dat(String filePath) throws IOException {
        super(filePath);

        load();
    }

    public void load() {
        long datSignature = lerInt();

        int itemCount = lerShort();
        int creatureCount = lerShort();
        int effectCount = lerShort();
        int distanceCount = lerShort();

        int minclientID = 100;
        int maxclientID = itemCount;

        int currentID = minclientID;

        byte optbyte;
        do {
            optbyte = lerByte();
        } while (optbyte != 0xFF);
    }

    public static void main(String[] args) throws IOException {
        new Dat("src/assets/tibia-8.6.dat");
    }
}
