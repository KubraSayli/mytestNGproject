package com.techproed.tests;

import com.techproed.pages.DTPage;
import com.techproed.utilities.ConfigReader;
import com.techproed.utilities.Driver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Day16_DataTablesTest {

    DTPage dtPage;
    @Test
    public void dataTables(){
        Driver.getDriver().get(ConfigReader.getProperty("dt_url"));
        dtPage=new DTPage();
        dtPage.newButton.click();
        dtPage.firstName.sendKeys("Jimmy");
        dtPage.lastName.sendKeys("Carrey");
        dtPage.position.sendKeys("SDET");
        dtPage.office.sendKeys("Dallas");
        dtPage.extension.sendKeys("1234");
        dtPage.startDate.sendKeys("2021-01-19");
        dtPage.salary.sendKeys("100000");
        dtPage.createButton.click();
        dtPage.searchBox.sendKeys("Jimmy");
        String actualName=dtPage.nameField.getText();
        Assert.assertTrue(actualName.contains("Jimmy"));
    }

}
