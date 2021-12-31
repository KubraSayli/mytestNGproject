package com.techproed.tests;
import com.techproed.utilities.ConfigReader;
import com.techproed.utilities.Driver;
import org.testng.Assert;
import org.testng.annotations.Test;
public class Day11_FirstDriverClass {
    @Test
    public void firstDriverTest(){
        //driver -> Driver.getDriver()
        //Driver.getDriver().get("https://www.amazon.com");

        Driver.getDriver().get(ConfigReader.getProperty("qa_environment"));
    }
}
