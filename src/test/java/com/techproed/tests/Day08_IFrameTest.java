package com.techproed.tests;

import com.techproed.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

/*
Go to https://the-internet.herokuapp.com/iframe
Verify the Bolded text contains “Editor”
Locate the text box
Delete the text in the text box
Type “This text box is inside the iframe”
Verify the text Elemental Selenium text is displayed on the page
Collapse


 */
public class Day08_IFrameTest extends TestBase {

    @Test
    public void iframeTest(){
        driver.get("https://the-internet.herokuapp.com/iframe");
        String boldedText=driver.findElement(By.xpath("//h3[.='An iFrame containing the TinyMCE WYSIWYG Editor']")).getText();
        Assert.assertTrue(boldedText.contains("Editor"));
        //Number of Iframe
        int numOfIframe=driver.findElements(By.tagName("iframe")).size();
        System.out.println(numOfIframe);

        //Getting inside the iframe
//        driver.switchTo().frame(0);//index start from 0
//        driver.switchTo().frame("mce_0_ifr");//id or name value
        WebElement iframeElement=driver.findElement(By.xpath("//iframe[@id='mce_0_ifr']"));
        driver.switchTo().frame(iframeElement);//WebElement like xpath, etc.
        driver.findElement(By.xpath("//p")).clear();//clear deleted the input text
        driver.findElement(By.xpath("//p")).sendKeys("This text box is inside the iframe");

        //Getting out of the iframe
        //driver.switchTo().defaultContent(); //this one takes us to root content
        //OR
        driver.switchTo().parentFrame(); //this one takes to parent
        WebElement elemenalSelenium = driver.findElement(By.linkText("Elemental Selenium"));
        Assert.assertTrue(elemenalSelenium.isDisplayed());

    }


}
