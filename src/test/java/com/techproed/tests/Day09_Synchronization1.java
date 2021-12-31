package com.techproed.tests;
/*
Create a class:Day09_Synchronization1. Create a method: synchronization1
Go to https://the-internet.herokuapp.com/dynamic_controls
Click on remove button
And verify the message is equal to “It's gone!”
Then click on Add button
And verify the message is equal to “It's back!”
 */

import com.techproed.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Day09_Synchronization1 extends TestBase {
    @Test
    public void synchronization1(){
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");
        WebElement removeButton=driver.findElement(By.xpath("(//button[@type='button'])[1]"));
        removeButton.click();
        //This element won't be found if there is no wait
        //We get NoSuchElementException
        //1. Check if my locator is correct(id,xpath,...).Element is correct.
        //2. Check if there is a synchronization issue. Here we have this problem.
        //-Try if you have enough wait.
        //-Try if you need to use Explicit Wait. Because some elements can only be handled using explicit wait
        //Note: Implicit wait is enough to handle this wait problem. No need to use explicit wait
        WebElement goneMessage=driver.findElement(By.id("message"));
        String goneMessageText=goneMessage.getText();
        Assert.assertEquals(goneMessageText,"It's gone!");
        WebElement addButton=driver.findElement(By.xpath("(//button[@type='button'])[1]"));
        addButton.click();
        WebElement backMessage=driver.findElement(By.xpath("(//*[contains(text(),'back')])[2]"));
        String backMessageText=backMessage.getText();
        Assert.assertEquals(backMessageText,"It's back!");
    }
    @Test
    public void synchronization2(){
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");
        WebElement removeButton=driver.findElement(By.xpath("(//button[@type='button'])[1]"));
        removeButton.click();
        //This element won't be found if there is no wait
        //We get NoSuchElementException
        //1. Check if my locator is correct(id,xpath,...).Element is correct.
        //2. Check if there is a synchronization issue. Here we have this problem.
        //HOW CAN WE HANDLE USING EXPLICIT WAIT?
        //1. Create WebDriverWait object:
        WebDriverWait wait = new WebDriverWait(driver,10);
        //We add waits uo to 10 second. That returns the WebElement. So We don't need to use findElement if we we are using explicit wait
        //Explicit Wait = wait + findElement
        WebElement goneMessage=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
        String goneMessageText=goneMessage.getText();
        Assert.assertEquals(goneMessageText,"It's gone!");

        WebElement addButton=driver.findElement(By.xpath("(//button[@type='button'])[1]"));
        addButton.click();
        //Putting explicit wait on the message element.
        WebElement backMessage=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[contains(text(),'back')])[2]")));
        String backMessageText=backMessage.getText();
        Assert.assertEquals(backMessageText,"It's back!");
    }

}
