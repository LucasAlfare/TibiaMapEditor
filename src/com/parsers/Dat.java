package com.parsers;

import java.io.IOException;

/**
 * TODO: meu sonho um dia é conseguir fazer essa...
 */
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

        System.out.println(datSignature);
        System.out.println(itemCount);
        System.out.println(creatureCount);
        System.out.println(effectCount);
        System.out.println(distanceCount);

        int minclientID = 100;
        int maxclientID = itemCount;

        int currentID = minclientID;

        byte optbyte;
        do {
            optbyte = lerByte();
        } while (optbyte != 255);

        System.out.println("oi " + optbyte);
    }

    public static void main(String[] args) throws IOException {
        new Dat("src/com.assets/tibia-8.6.dat");
    }
}
