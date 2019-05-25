package com.parsers;

import java.io.IOException;

public class Dat extends ParserBase {

    public Dat(String filePath) throws IOException {
        super(filePath);


    }

    public static void main(String[] args) throws IOException {
        new Dat("src/assets/tibia-8.6.dat");
    }
}
