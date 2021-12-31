package com.techproed.homeworks;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import java.text.NumberFormat;
import java.util.concurrent.TimeUnit;
public class Testing {
    @Test
    public void testing() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.wonderplugin.com/wordpress-lightbox");
        WebElement element = driver.findElement(By.xpath("//a[contains(text(),'Open a Div in Lightbox')]"));
        element.click();
        System.out.println("found element and clicked");
        Thread.sleep(3000);
        WebElement frameElement = driver.findElement(By.xpath("//iframe[@src='https://www.youtube.com/embed/wswxQ3mhwqQ']"));
        driver.switchTo().frame(frameElement);
        driver.findElement(By.xpath("//button[@aria-label=\'Play\']")).click();
        Actions builder = new Actions(driver);
        WebElement we = driver.findElement(By.className("ytp-progress-bar-container"));
        Action mouseMovement = builder.moveToElement(we).build();
        mouseMovement.perform();
        we.click();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement titleText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ytp-title-link")));
        System.out.println(titleText.getText());
        WebElement time = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.ytp-time-current")));
        System.out.println(time.getText());
        WebElement totalTime=wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.ytp-time-duration")));
        System.out.println(totalTime.getText());
        double currentTime1=Double.valueOf(time.getText().replace(":",""));
        double totalTime1=Double.valueOf(totalTime.getText().replace(":",""));
        System.out.println(currentTime1);
        System.out.println(totalTime1);
        double percent=(currentTime1/totalTime1);
        System.out.println(percent);
        NumberFormat defaultFormat = NumberFormat.getPercentInstance();
        defaultFormat.setMinimumFractionDigits(2);
        System.out.println("Percent format: " + defaultFormat.format(percent));
    }
}
