package com.techproed.tests;
import com.techproed.utilities.ReusableMethods;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;
public class AlertTest {
    //    Go to https://the-internet.herokuapp.com/javascript_alerts
//    Create a class: AlertTest
//    Create setUp method
//    Create 3 test methods:
//    acceptAlert() => click on the first alert,
//    verify the text “I am a JS Alert” ,
//    click OK.
//    Then Verify “You successfuly clicked an alert”
//    dismissAlert()=> click on the second alert,
//    verify text "I am a JS Confirm”,
//    click cancel,
//    and Verify “You clicked: Cancel”
//    sendKeysAlert()=> click on the third alert,
//    verify text “I am a JS prompt”,
//    type “Hello Word”,
//    click OK,
//    and Verify “You entered: Hello Word”
    WebDriver driver;
    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
    }
    @Test
    public void acceptAlert() throws InterruptedException {
        WebElement firstButton=driver.findElement(By.xpath("//button[@onclick='jsAlert()']"));
        firstButton.click();
        String actualTextOfFirstAlert=driver.switchTo().alert().getText();
       // Thread.sleep(3000);
        ReusableMethods.waitFor(3);
        Assert.assertEquals(actualTextOfFirstAlert,"I am a JS Alert");
       // Thread.sleep(3000);
        driver.switchTo().alert().accept();
        String actualResultText=driver.
                findElement(By.xpath("//*[.='You successfuly clicked an alert']"))
                .getText();
        Assert.assertEquals(actualResultText,"You successfuly clicked an alert");
    }
    @Test
    public void dismissAlert(){
        WebElement secondButton=driver.findElement(By.xpath("//button[@onclick='jsConfirm()']"));
        secondButton.click();
        String actualTextOfSecondAlert=driver.switchTo().alert().getText();
        Assert.assertEquals(actualTextOfSecondAlert,"I am a JS Confirm");
        driver.switchTo().alert().dismiss();
        String actualResultText=driver
                .findElement(By.xpath("//p[.= 'You clicked: Cancel']"))
                .getText();
        Assert.assertEquals(actualResultText,"You clicked: Cancel");
    }
    @Test
    public void sendKeysAlert(){
        driver.findElement(By.xpath("//button[@onclick='jsPrompt()']")).click();
        Assert.assertEquals(driver.switchTo().alert().getText(),"I am a JS prompt");
        driver.switchTo().alert().sendKeys("Hello Word");
        driver.switchTo().alert().accept();
        Assert.assertEquals(driver.findElement(By.id("result")).getText(),"You entered: Hello Word");
    }
    @AfterMethod
    public void tearDown(){
        driver.close();
    }
}