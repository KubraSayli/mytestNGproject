package com.techproed.tests;


import com.techproed.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

/*
 /*
    Create a class:Day09_Synchronization2. Create a method: isEnabled
    Go to https://the-internet.herokuapp.com/dynamic_controls
    Click enable Button
    And verify the message is equal to “It's enabled!”
    And verify the textbox is enabled (I can type in the box)
    And click on Disable button
    And verify the message is equal to “It's disabled!”
    And verify the textbox is disabled (I cannot type in the box)
    Collapse
     */


public class Day09_Synchronization2 extends TestBase {
    @Test
    public void isEnabled(){
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");
        driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
        WebElement enabledMessage=driver.findElement(By.id("message"));
        String enabledMessageText=enabledMessage.getText();
        Assert.assertEquals(enabledMessageText,"It's enabled!");
        WebElement enableTextBox = driver.findElement(By.xpath("//input[@type = 'text']"));
        Assert.assertTrue(enableTextBox.isEnabled());
        //Locating disable button and clicking on it
        driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
        //And verify the message is equal to “It's disabled!”
        WebElement disabledMessage=driver.findElement(By.id("message"));
        String disabledMessageText=disabledMessage.getText();
        Assert.assertEquals(disabledMessageText,"It's disabled!");
        Assert.assertFalse(enableTextBox.isEnabled());
    }

    /*
    Homework : Create a  new method : isExampled1() ,
               Use Explicit Wait on the correct web elements
     */

    @Test
    public void isExampled(){
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");
        driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement enableMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
        String actualTest = enableMessage.getText();
        String expectedText = "It's enabled!";

        Assert.assertEquals(actualTest, expectedText);

        WebElement disableText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
        String actualText2 = disableText.getText();
        String expectedText2 = "It's disabled!";

        Assert.assertEquals(actualText2, expectedText2);

/*
@Test
    public void synchronization4(){
        //Homework"
        //Create a new method: isExampled1()
        //Use Explicit Wait On The Correct WebElements

        driver.get("https://the-internet.herokuapp.com/dynamic_controls");

        WebElement enableButton = driver.findElement(By.xpath("(//button[@type = 'button'])[2]"));
        enableButton.click();

        WebDriverWait wait = new WebDriverWait(driver,10);
        WebElement enableMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id = 'message']")));
        String actualText = enableMessage.getText();
        String expectedText = "It's enabled!";

        Assert.assertEquals(actualText,expectedText);

        WebElement disableButton = driver.findElement(By.xpath("(//button[@type = 'button'])[2]"));
        disableButton.click();
        WebElement disableText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@id = 'message']")));
        String actualTextTwo = disableText.getText();
}

 */
    }
}
