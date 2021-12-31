package com.techproed.tests;

import com.techproed.utilities.ConfigReader;
import com.techproed.utilities.TestBase;
import org.testng.annotations.Test;

public class Day11_ConfigReaderTest extends TestBase {
    @Test
    public void googleTitleTest(){
        //driver.get("https://www.google.com"); //hard coded
        System.out.println(ConfigReader.getProperty("url"));

    }
}
