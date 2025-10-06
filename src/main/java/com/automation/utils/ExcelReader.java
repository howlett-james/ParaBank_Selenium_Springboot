package com.automation.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {

    public static List<String[]> readExcel(String filePath, String sheetName) {
        List<String[]> data = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            for (Row row : sheet) {
                int lastColumn = row.getLastCellNum();
                String[] rowData = new String[lastColumn];
                for (int i = 0; i < lastColumn; i++) {
                    Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    rowData[i] = cell.toString();
                }
                data.add(rowData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
