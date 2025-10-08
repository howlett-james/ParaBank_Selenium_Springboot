package com.automation.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class JsonReaderUtil {

    public static Map<String, Object> getLatestRecord(String filePath) {
        Map<String, Object> latestRecordMap = null;

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(new File(filePath));

            // Assuming the JSON has only one array (top-level) for test data
            Iterator<JsonNode> elements = rootNode.elements();
            JsonNode latestRecord = null;

            while (elements.hasNext()) {
                JsonNode arrayNode = elements.next();
                if (arrayNode.isArray()) {
                    for (JsonNode record : arrayNode) {
                        if (latestRecord == null ||
                                record.get("id").asLong() > latestRecord.get("id").asLong()) {
                            latestRecord = record;
                        }
                    }
                }
            }

            if (latestRecord != null) {
                latestRecordMap = new HashMap<>();
                Iterator<String> fields = latestRecord.fieldNames();
                while (fields.hasNext()) {
                    String field = fields.next();
                    latestRecordMap.put(field, latestRecord.get(field).asText());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return latestRecordMap;
    }
}
