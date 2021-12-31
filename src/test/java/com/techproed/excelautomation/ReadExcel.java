package com.techproed.excelautomation;

import org.apache.poi.ss.usermodel.*;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//Workbook>Worksheet>Row>Cell
public class ReadExcel {

    @Test
    public void readExcel() throws Exception {
        //Locating the path of the excel workbook
        String path = ".\\src\\test\\java\\recourses\\Capitals.xlsx";
        FileInputStream fileInputStream = new FileInputStream(path);
        //Opening the workbook using fileinputstream
        Workbook workbook = WorkbookFactory.create(fileInputStream);
        Sheet sheet = workbook.getSheetAt(0);//Reading the first sheet
        Row row = sheet.getRow(0);
        Cell cell = row.getCell(0);
        System.out.println(cell);

        Cell usaCell = sheet.getRow(1).getCell(0);
        System.out.println(usaCell);

        //We can convert a cell object to a String and do a String manipulation with data
        String DCCell = sheet.getRow(1).getCell(1).toString();
        System.out.println(DCCell);

        //Find the number of row
        int lastRowNum = sheet.getLastRowNum();//Returns the last row number that has data
                                                //arada boşluklar da olsa sondakine göre bakar
        //index 0 dan başlar. So we need to add 1 to find the last one
        System.out.println("Row num : "+ lastRowNum);

        int physicalNumOfRow = sheet.getPhysicalNumberOfRows();
        //index starts at 1
        //boşluklar varsa onları saymaz
        System.out.println("Number of row that is used : " + physicalNumOfRow);
        //{USA, DC}, {France, Paris}, {TURKEY, Ankara},...
        Map <String, String> worldsCapitals = new HashMap<>();
        int countryColumn=0;
        int capitalColumn=1;
        for(int rowNum=1; rowNum<lastRowNum; rowNum++ ){
            String countries = sheet.getRow(rowNum).getCell(countryColumn).toString();
            System.out.println(countries);
            String capitals = sheet.getRow(rowNum).getCell(capitalColumn).toString();
            System.out.println(capitals);
            worldsCapitals.put(countries,capitals);
        }
        System.out.println(worldsCapitals);
        //closing the workbook
        fileInputStream.close();
        workbook.close();


//        System.out.println("COUNTRIES:");
//        System.out.println(sheet.getRow(0).getCell(0));
//        System.out.println(sheet.getRow(1).getCell(0));
//        System.out.println(sheet.getRow(2).getCell(0));
//        System.out.println(sheet.getRow(3).getCell(0));
//        System.out.println(sheet.getRow(4).getCell(0));
//
//        System.out.println("CAPITALS:");
//        System.out.println(sheet.getRow(0).getCell(1));
//        System.out.println(sheet.getRow(1).getCell(1));
//        System.out.println(sheet.getRow(2).getCell(1));
//        System.out.println(sheet.getRow(3).getCell(1));
//        System.out.println(sheet.getRow(4).getCell(1));

    }
}
