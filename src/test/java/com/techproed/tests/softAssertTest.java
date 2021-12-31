package com.techproed.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

public class softAssertTest {

    //When user goes to google home page
    //And search for porcelain teapot
   //Then verify the page title includes teapot

    WebDriver driver;
    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://www.google.com");


    }

    @Test
    public void teapotSearch(){

        WebElement searchBox = driver.findElement(By.xpath("//input[@autocomplete='off']"));
        searchBox.sendKeys("porcelain teapot"+Keys.ENTER);
        String title = driver.getTitle();
        //title does not include teapot" Message will fal show up only if assertion fails :
        //Assert.assertTrue(title.contains("teapot"),"title does not include teapot");
        //The lines after hard assert will run ONLY IF ASSERTION PASSES :

        //1.Create soft assert object
        SoftAssert softAssert = new SoftAssert();
        //2.Use assertion
        softAssert.assertTrue(title.contains("tea pot"),"tea pot");
        //3.Use assertAll to mark as Failed or Passed
        //Note that if we don't use assertAll() we get incorrect report
        softAssert.assertTrue(title.contains("teapot"),"teapot"); //bir sürü teest yptım ki soft assertion da biri fail etse alttakiler pass edebilir
        softAssert.assertTrue(title.contains("porcelain"),"porcelain");
        softAssert.assertAll(); //We must use this assertAll() to mark test case as PASSED or FAILED
        System.out.println("This is after hard assert");


    }
    @AfterMethod
    public void tearDown(){
        driver.close();
    }
}
