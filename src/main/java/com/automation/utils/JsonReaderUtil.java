package com.automation.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class JsonReaderUtil {

    public static Map<String, Object> getRandomRecord(File file) {
        Map<String, Object> latestRecordMap = null;
        Random random = new Random();

        try {
            if (!file.exists() || file.length() == 0) {
                return null;
            }

            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(file);

            if (!rootNode.isArray() || rootNode.size() == 0) {
                return null;
            }

            int randomIndex = random.nextInt(rootNode.size());
            JsonNode randomRecord = rootNode.get(randomIndex);

            latestRecordMap = new HashMap<>();
            Iterator<String> fields = randomRecord.fieldNames();
            while (fields.hasNext()) {
                String field = fields.next();
                latestRecordMap.put(field, randomRecord.get(field).asText());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return latestRecordMap;
    }
}
