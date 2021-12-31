package com.techproed.crossbrowsertests;

//In this class we want to run this using cross browser testing
import com.techproed.utilities.TestBaseCross;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
public class PositiveTest extends TestBaseCross {
    @Test
    public void positiveTest(){
        driver.get("https://qa-environment.crystalkeyhotels.com/Account/Logon");
        WebElement userName=driver.findElement(By.id("UserName"));
        WebElement password=driver.findElement(By.id("Password"));
        WebElement loginButton=driver.findElement(By.id("btnSubmit"));
        userName.sendKeys("manager");
        password.sendKeys("manager2!");
        loginButton.click();
    }
}
