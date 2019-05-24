package com.extractor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class DatParser {

    public static void main(String[] args) throws IOException {
        File file = new File("src/assets/tibia-8.6.dat");
        byte[] b = Files.readAllBytes(file.toPath());
        //ByteArrayInputStream stream = new ByteArrayInputStream(b);

        System.out.println(0xff);
    }
}
