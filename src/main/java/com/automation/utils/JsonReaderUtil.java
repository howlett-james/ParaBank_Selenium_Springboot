package com.automation.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class JsonReaderUtil {

    public static Map<String, Object> getLatestRecord(File file) {
        Map<String, Object> latestRecordMap = null;

        try {
            if (!file.exists() || file.length() == 0) {
                return null;
            }

            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(file);

            if (!rootNode.isArray() || rootNode.size() == 0) {
                return null;
            }

            JsonNode latestRecord = null;
            for (JsonNode record : rootNode) {
                if (latestRecord == null ||
                        record.get("id").asLong() > latestRecord.get("id").asLong()) {
                    latestRecord = record;
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
