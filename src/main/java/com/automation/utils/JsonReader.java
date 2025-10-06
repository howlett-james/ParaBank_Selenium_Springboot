package com.automation.utils;

import com.google.gson.Gson;

import java.io.FileReader;
import java.util.Map;

public class JsonReader {

    public static Map<String, Object> readJson(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            return new Gson().fromJson(reader, Map.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
