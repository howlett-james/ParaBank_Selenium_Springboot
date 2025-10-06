package com.automation.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static String getCurrentTimestamp(String pattern) {
        return new SimpleDateFormat(pattern).format(new Date());
    }

    public static String getDefaultTimestamp() {
        return getCurrentTimestamp("yyyyMMdd_HHmmss");
    }
}
