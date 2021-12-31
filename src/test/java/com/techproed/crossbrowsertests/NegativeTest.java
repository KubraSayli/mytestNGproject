package com.techproed.crossbrowsertests;

import com.techproed.utilities.TestBaseCross;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
public class NegativeTest extends TestBaseCross {
    @Test
    public void invalidID(){
        driver.get("https://qa-environment.crystalkeyhotels.com/Account/Logon");
        WebElement userName=driver.findElement(By.id("UserName"));
        WebElement password=driver.findElement(By.id("Password"));
        WebElement loginButton=driver.findElement(By.id("btnSubmit"));
        userName.sendKeys("manar2");
        password.sendKeys("manager2!");
        loginButton.click();
    }
    @Test
    public void invalidPass(){
        driver.get("https://qa-environment.crystalkeyhotels.com/Account/Logon");
        WebElement userName=driver.findElement(By.id("UserName"));
        WebElement password=driver.findElement(By.id("Password"));
        WebElement loginButton=driver.findElement(By.id("btnSubmit"));
        userName.sendKeys("manager2");
        password.sendKeys("mana2!");
        loginButton.click();
    }
    @Test
    public void invalidIDPass(){
        driver.get("https://qa-environment.crystalkeyhotels.com/Account/Logon");
        WebElement userName=driver.findElement(By.id("UserName"));
        WebElement password=driver.findElement(By.id("Password"));
        WebElement loginButton=driver.findElement(By.id("btnSubmit"));
        userName.sendKeys("mager2");
        password.sendKeys("mana2!");
        loginButton.click();
    }
}
