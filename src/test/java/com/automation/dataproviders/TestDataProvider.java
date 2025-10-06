// File: java/com/automation/dataproviders/TestDataProvider.java
package com.automation.dataproviders;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.DataProvider;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class TestDataProvider {

    @DataProvider(name = "registrationData")
    public static Object[][] registrationData() throws Exception {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Map<String, String>>>(){}.getType();
        List<Map<String, String>> users = gson.fromJson(
                new FileReader("src/test/resources/testdata/testdata.json"), listType);

        Object[][] data = new Object[users.size()][1];
        for (int i = 0; i < users.size(); i++) {
            data[i][0] = users.get(i);
        }
        return data;
    }
}
