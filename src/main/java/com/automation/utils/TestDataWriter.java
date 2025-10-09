package com.automation.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;

public class TestDataWriter {

    public static void appendUserData(String username, String password) {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("src/test/resources/testdata/testdata.json");

        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
                mapper.writeValue(file, mapper.createArrayNode());
            }

            ArrayNode arrayNode;
            if (file.length() != 0) {
                arrayNode = (ArrayNode) mapper.readTree(file);
            } else {
                arrayNode = mapper.createArrayNode();
            }

            long nextId = arrayNode.size() > 0 ?
                    arrayNode.get(arrayNode.size() - 1).get("id").asLong() + 1 : 1;

            ObjectNode newUser = mapper.createObjectNode();
            newUser.put("id", nextId);
            newUser.put("username", username);
            newUser.put("password", password);

            arrayNode.add(newUser);

            mapper.writerWithDefaultPrettyPrinter().writeValue(file, arrayNode);

            System.out.println("[INFO] User saved to testdata.json: " + username);

        } catch (IOException e) {
            System.err.println("[ERROR] Failed to write test data: " + e.getMessage());
        }
    }
}
