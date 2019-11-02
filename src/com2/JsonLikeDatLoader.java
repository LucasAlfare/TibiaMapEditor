package com2;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class JsonLikeDatLoader {

    public static String JSON_LIKE_DAT_PATH = "src/assets/json/full_dat.json";
    private static DatItem[] loadedItems;

    public static DatItem[] getLoadedItems() {
        if (loadedItems == null) {
            BufferedReader bufferedReader = null;
            try {
                bufferedReader = new BufferedReader(
                        new FileReader(
                                JsonLikeDatLoader.JSON_LIKE_DAT_PATH));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            assert bufferedReader != null;
            loadedItems = new Gson().fromJson(
                    bufferedReader, DatItem[].class);
        }

        return loadedItems;
    }
}