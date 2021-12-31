package com.techproed.tests;

import com.techproed.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class Day08_IframeReview extends TestBase {

    @Test
    public void test01(){
        driver.get("https://html.com/tags/iframe/");

//        WebElement button = driver.findElement(By.xpath("//button[@aria-label='Oynat']"));
//        button.click();

        int numOfIframes = driver.findElements(By.tagName("iframe")).size();
        System.out.println(numOfIframes);




    }
}
