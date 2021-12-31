package com.techproed.tests;

import com.techproed.utilities.TestBase;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Day10_FileDownloadTest extends TestBase {
    /*
    Create a class:FileDownloadTest
downloadTest()
In the downloadTest() method, do the following test:
Go to https://the-internet.herokuapp.com/download
Download flower.png file
Then verify if the file downloaded successfully
     */

    @Test
    public void downloadTest() throws InterruptedException {
       driver.get("https://the-internet.herokuapp.com/download");
      //  WebDriverWait wait = new WebDriverWait(driver,10);
        // WebElement file = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("flower.jpg"))); //bu çalışmadı
        WebElement file = driver.findElement(By.linkText("flower.jpg"));
        file.click();

        //Thread.sleep(1000);

        //HW: Try to handle this wait issue using the explicit wait
        //After click, file be downloaded on the downloads folder
        //Get the path of the file that is in download folder
        //And check if the file exists.
        //Pointing the file in download folder
        String userFolder = System.getProperty("user.home"); //Users/Acer => gives you the
        System.out.println(userFolder);
        //              userFolder   + "Downloads\flower.jpg"
        String path = userFolder + "\\\\Downloads\\\\flower.jpg";
        //String path = "C:\\Users\\Acer\\Downloads\\flower.jpg";
        boolean isDownloaded = Files.exists(Paths.get(path));
        //This pass is isFileExist in the downloded folder
        Assert.assertTrue(isDownloaded);
    }
}
