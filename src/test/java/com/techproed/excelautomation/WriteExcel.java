package com.techproed.excelautomation;

import org.apache.poi.ss.usermodel.*;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Test
public class WriteExcel {
    public void writeExcel() throws Exception {
        //Locate path of the workbook:
        String path = ".\\src\\test\\java\\recourses\\Capitals.xlsx";
        FileInputStream fileInputStream = new FileInputStream(path);
        //Opening the workbook using fileinputstream
        Workbook workbook = WorkbookFactory.create(fileInputStream);
        Sheet sheet = workbook.getSheetAt(0);//Reading the first sheet
        Row row =sheet.getRow(0);//going to the first row
        //Create a cell on the 3 rd index of the first row
        Cell cell4 = row.createCell(3);
        cell4.setCellValue("POPULATION");
        //Create a cell on the 2 nd row and 4th cell (index3), and write 150000
        //We can chain the methods
        sheet.getRow(1).createCell(3).setCellValue("150000");
        //Create a cell on the 3rd row 4th cell(index3), and write 250000
        sheet.getRow(2).createCell(3).setCellValue("250000");
        //Create a cell on the 4th row 4th cell(index3), and write 54000
        sheet.getRow(3).createCell(3).setCellValue("54000");

        //Write and Save the values , use fileoutputstream
        FileOutputStream fileOutputStream = new FileOutputStream(path);
        workbook.write(fileOutputStream);


    }
}
