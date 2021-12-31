package com.techproed.tests;

import com.techproed.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

public class WindowHandleReview extends TestBase {

    @Test
    public void test1(){

        driver.get("https://the-internet.herokuapp.com/windows");
        String parentWindowHandle = driver.getWindowHandle();
        WebElement click = driver.findElement(By.xpath("//a[@target='_blank']"));
        click.click();

        Set <String> allWindows = driver.getWindowHandles();
        for(String w : allWindows){
            if(!w.equals(parentWindowHandle)){
                driver.switchTo().window(w);
            }
        }
        WebElement text = driver.findElement(By.tagName("h3"));
        Assert.assertTrue(text.isDisplayed());


    }
}
