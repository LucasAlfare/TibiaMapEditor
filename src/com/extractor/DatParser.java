package com.extractor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DatParser {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("xyz.dat");
        Scanner scnr = new Scanner(file);
        while(scnr.hasNextLine()){
            String line = scnr.nextLine();
            System.out.println(line);
        }
    }
}
