package com.techproed.tests;

import com.techproed.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Day09_ActionsClass2 extends TestBase {
    /*
    Create a class: ActionClass2
Create test method : hoverOver() and test the following scenario:
Given user is on the https://www.amazon.com/
When use click on “Account” link
Then verify the page title contains “Your Account”

     */


    @Test
    public void hoverOver() throws InterruptedException {
        driver.get("https://www.amazon.com/");
        WebElement accountList = driver.findElement(By.id("nav-link-accountList"));
        Actions actions = new Actions(driver);
        //Hovering over the account list element
        //We need to hover over account list element because it is hidden under the account list element
        actions.moveToElement(accountList).perform();
       // Thread.sleep(5000);
        driver.findElement(By.linkText("Account")).click();
        Assert.assertTrue(driver.getTitle().contains("Your Account"));
    }


}
