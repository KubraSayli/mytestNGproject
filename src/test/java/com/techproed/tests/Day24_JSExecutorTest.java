package com.techproed.tests;
import com.techproed.utilities.ConfigReader;
import com.techproed.utilities.Driver;
import com.techproed.utilities.JSUtils;
import com.techproed.utilities.ReusableMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
public class Day24_JSExecutorTest {
    //
    @Test
    public void jsExecutorTest(){
        Driver.getDriver().get(ConfigReader.getProperty("qa_environment"));
//        Recent Blog element
        WebElement element1=Driver.getDriver().findElement(By.xpath("(//h2)[10]"));
        System.out.println(element1.isDisplayed());//Return false because we must scroll down untill that element to be visible
        JavascriptExecutor je=(JavascriptExecutor) Driver.getDriver();
//        Scroll down to that element to be visible
        je.executeScript("arguments[0].scrollIntoView(true);",element1);
        ReusableMethods.waitFor(2);
        System.out.println(element1.isDisplayed());
        System.out.println(element1.getText());
//
    }
    @Test
    public void JSExecutorTest2(){
        Driver.getDriver().get("http://manos.malihu.gr/repository/custom-scrollbar/demo/examples/complete_examples.html");
        JavascriptExecutor je=(JavascriptExecutor) Driver.getDriver();
        WebElement element1=Driver.getDriver().findElement(By.xpath("//*[@id='mCSB_3_container']/p[3]"));
        ReusableMethods.waitFor(2);
//        Without scrolling down to the element, Element won't be displayed, and we can't get the text of the element
        je.executeScript("arguments[0].scrollIntoView(true);",element1);
        ReusableMethods.waitFor(2);
        System.out.println(element1.isDisplayed());
        System.out.println(element1.getText());
//        Check if text area is displayed
//        Locating the textarea
        WebElement textarea=Driver.getDriver().findElement(By.xpath("//textarea"));
        je.executeScript("arguments[0].scrollIntoView(true);",textarea);
        boolean isDisplayed=textarea.isDisplayed();
        Assert.assertTrue(isDisplayed);
//        Clear textarea
        textarea.clear();
//        Type "I can type"
        textarea.sendKeys("I can type");

        //We have JSUtilities class in our Utilities
        //We should use the methods from that class
        //So as always we should  seperate our actual test cases from the set up related tasks
        WebElement element2 = Driver.getDriver().findElement(By.xpath("(//img[@src='images/img5.jpg'])[2]"));
        JSUtils.scrollIntoVIewJS(element2);
        je.executeScript("arguments[0].scrollIntoView(true);",element2);
        boolean imageIsDisplayed = element2.isDisplayed();
        System.out.println(element2.isDisplayed());

    }

    @Test
    public void JSExecutorTest3(){
        Driver.getDriver().get("https://qa-environment.crystalkeyhotels.com/Account/Logon");
        WebElement loginButton = Driver.getDriver().findElement(By.xpath("//input[@type='submit']"));
        JSUtils.changeColor("green", loginButton);
        JSUtils.flash(loginButton);
    }


}


