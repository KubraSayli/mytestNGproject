package com.techproed.tests;
/*

Create a class:TestBaseDemo
Create two methods: test1, test2
test1 search “porcelain teapot” on google
Then verify page contains “porcelain teapot”
——————————-
test2 search “porcelain teapot” on amazon
Then verify page contains “porcelain teapot”

//    test2 search "porcelain teapot" on amazon
//    Then verify page contains "porcelain teapot"
 */

//When we run a test case then the follow as following:
//SetUp() (in TestBase class )=====test1=======tearDown() (in TestBase class )

//Since we are using TestBase class
import com.techproed.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestBaseDemo extends TestBase {

    //Since we are using TestBase class we don't add before and after methods
    @Test
    public void test1(){
        driver.get("https://www.google.com");
        driver.findElement(By.name("q")).sendKeys("porcelain teapot"+ Keys.ENTER);
        String pageSource = driver.getPageSource();//returns the page source as a string
        //System.out.println(pageSource);
        Assert.assertTrue(pageSource.contains("porcelain teapot"));


    }

    @Test
    public void test2(){
        driver.get("https://www.amazon.com/");
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("porcelain teapot" + Keys.ENTER);
        String title =  driver.getTitle(); //pagesource veya pagetitle kullanabilirsin
        Assert.assertTrue(title.contains("porcelain teapot"));
    }
}
