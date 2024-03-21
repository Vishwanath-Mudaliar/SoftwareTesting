package com.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.testng.annotations.Test;

public class AppTest 
{
    List<String[]> testData;
    @Test
    public void readXL()throws Exception{

        testData = new ArrayList<>();

        File fobj = new File("C://Users//VishwanathD//Desktop//Software_Testing//Projects//Login.xls");

        FileInputStream fis = new FileInputStream(fobj);

        Workbook wb = new HSSFWorkbook(fis);

        Sheet sheet = wb.getSheet("Sheet1");

        int rowcount = sheet.getLastRowNum() - sheet.getFirstRowNum() + 1;

        for(int row = 0 ; row < rowcount ; row++){
            
            Row r  = sheet.getRow(row);

            testData.add(new String[]{r.getCell(0).getStringCellValue(),r.getCell(1).getStringCellValue()});

            WritingExcel();
        }

        wb.close();
        fis.close();
    }

    public void WritingExcel() throws IOException{
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("Login Test Results");

        // Create header row
        Row headerRow = sheet.createRow(0);
        String[] columns = {"Username", "Password", "Result"};
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
        }

        // Perform login tests and write results to Excel
        int rowNum = 1;
        for (String[] data : testData) {
            String username = data[0];
            String password = data[1];

            // Perform login test
            boolean loginSuccessful = performLogin(username, password);

            // Write test results to Excel
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(username);
            row.createCell(1).setCellValue(password);
            row.createCell(2).setCellValue(loginSuccessful ? "Pass" : "Fail");
        }

        // Write workbook to file
        try (FileOutputStream outputStream = new FileOutputStream("login_test_results.xls")) {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        workbook.close();
    }

    // Method for login test logic
    private static boolean performLogin(String username, String password) {
        // Simulate login logic (replace this with your actual login logic)
        if (username.equals(password)) {
            // Simulating successful login for user1
            return true;
        } else {
            // Simulating failed login for other users
            return false;
        }
    }
    
}
