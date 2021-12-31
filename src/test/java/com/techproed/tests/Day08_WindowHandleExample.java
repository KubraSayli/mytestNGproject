package com.techproed.tests;

/*
Create a new Class Tests package: Day08_WindowHandleExample
Method name:windowHandle
Given user is on the https://the-internet.herokuapp.com/windows
Then user verifies the text : “Opening a new window”
Then user verifies the title of the page is “The Internet”
When user clicks on the “Click Here” button
Then user verifies the new window title is “New Window”
Then user verifies the text:  “New Window”
When user goes back to the previous window and then verifies the title : “The Internet”

 */

import com.techproed.utilities.TestBase;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

public class Day08_WindowHandleExample extends TestBase {

    @Test
    public void windowHandle() {
        driver.get("https://the-internet.herokuapp.com/windows");

        //Getting the handle of current window (window1)
        //getWindowHandle() return the current of current window

        String window1Handle = driver.getWindowHandle();
        System.out.println(window1Handle);
        String window1Text = driver.findElement(By.xpath("//h3")).getText();
        Assert.assertEquals(window1Text, "Opening a new window");

        String window1Title = driver.getTitle();
        Assert.assertEquals(window1Title, "The Internet");

        driver.findElement(By.xpath("//a[@target='_blank']")).click();


        Set<String> allWindows = driver.getWindowHandles();
        System.out.println(allWindows);

        //Remember driver is on window1Handle
        //We want to switch window2Handle
        for (String eachWindow : allWindows) {

            //loop1 : eachWindow = window1handle
            //loop2 : eachWindow = window2handle
            if (!eachWindow.equals(window1Handle)) { //false //true 
                driver.switchTo().window(eachWindow);
                }
            }

        String window2Title = driver.getTitle();
        Assert.assertEquals(window2Title, "New Window");



    }

}
