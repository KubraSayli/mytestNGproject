package com.techproed.excelautomation;

import com.techproed.pages.DTPage;
import com.techproed.utilities.ConfigReader;
import com.techproed.utilities.Driver;
import com.techproed.utilities.ExcelUtil;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class Day16_ExcelAutomation {

    ExcelUtil excelUtil;
    List<Map<String, String>> testData;

    DTPage dtPage;
    @Test
    public void excelAutomation() throws InterruptedException {
        excelUtil=new ExcelUtil(".\\src\\test\\java\\recourses\\exceldata.xlsx","datatablessheet");
        testData=excelUtil.getDataList();
       // System.out.println(testData); //GIVES ME THE ENTIRE LIST

        //I will get each key value pairs inside the Map from the List
        for(Map<String,String> each : testData){
            System.out.println(each); //GIVES ME THE ENTIRE MAP
            Driver.getDriver().get(ConfigReader.getProperty("dt_url"));
            dtPage = new DTPage();
            dtPage.newButton.click();
//             each.get("firstname");// In map, get() method takes a key, it returns a value
//            System.out.println("FIRST NAME : "+ each.get("firstname"));
//            System.out.println("START DATES : "+each.get("startdate"));
//            System.out.println("START DATES : "+each.get("salary"));
            dtPage.firstName.sendKeys(each.get("firstname"));
            dtPage.lastName.sendKeys(each.get("lastname"));
            dtPage.position.sendKeys(each.get("position"));
            dtPage.office.sendKeys(each.get("office"));
            dtPage.extension.sendKeys(each.get("extension"));

            //Formatting the, because my date is coming as dd-MMM-yyyy but application accepting yyyy-MM-dd
            String oldDateString = each.get("startdate");
            LocalDate datetime = LocalDate.parse(oldDateString, DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
            String newDateString = datetime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            dtPage.startDate.sendKeys(newDateString);
            dtPage.salary.sendKeys(each.get("salary"));
            dtPage.createButton.click();
            dtPage.searchBox.sendKeys(each.get("firstname"));
            Thread.sleep(1000);
            String actualName=dtPage.nameField.getText();
            Assert.assertTrue(actualName.contains(each.get("firstname")));

        }
    }
}