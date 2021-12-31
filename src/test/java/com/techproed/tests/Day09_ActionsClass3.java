package com.techproed.tests;

import com.techproed.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class Day09_ActionsClass3 extends TestBase {
 /*
 Create a class: ActionsClass3
Create test method : keysUpDown()
Go to google
Send iPhone X prices => convert small letter capital vice versa.
Highlight the search box by double clicking
  */
    @Test
    public void keysUpDown(){
        driver.get("https://www.google.com");
        WebElement searchBox = driver.findElement(By.name("q"));
        Actions actions = new Actions(driver);
        //we want to press shift and type
        //we can use actions class to perform mouse keybords events. We can use different combinations
        actions.keyDown(searchBox, Keys.SHIFT). //press the button
                sendKeys("iPhone X prices").//typing in
                keyUp(searchBox,Keys.SHIFT).//releasing the button
                sendKeys(" too expensive in SOME COUNTRIES").
                doubleClick().perform();//Execution the task


    }

}
