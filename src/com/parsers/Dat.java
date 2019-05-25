package com.parsers;

import java.io.IOException;

public class Dat extends ParserBase {

    public Dat(String filePath) throws IOException {
        super(filePath);

        System.out.println(lerInt());

        System.out.println(lerShort());
        System.out.println(lerShort());
        System.out.println(lerShort());
        System.out.println(lerShort());
    }

    public void load() {
        int itemCount = lerShort();
        int creatureCount = lerShort();
        int effectCount = lerShort();
        int distanceCount = lerShort();

        int minclientID = 100;

    }

    public static void main(String[] args) throws IOException {
        new Dat("src/assets/tibia-8.6.dat");
    }
}
