package com.techproed.tests;

import com.techproed.pages.ContactPage;
import com.techproed.pages.MainPage;
import com.techproed.utilities.ConfigReader;
import com.techproed.utilities.Driver;
import com.techproed.utilities.ReusableMethods;
import com.techproed.utilities.TestBaseFinal;
import org.testng.Assert;
import org.testng.annotations.Test;


public class Day23_ExtentReportsTest2 extends TestBaseFinal {
    MainPage mainPage=new MainPage();
    ContactPage contactPage = new ContactPage();
    @Test
    public void extendReportsTest(){
        extentTest=extentReports.createTest("Crystal Keys extendReportsTest","Contact Page Test");
        extentTest.info("Opening the Application HomePage");
        Driver.getDriver().get(ConfigReader.getProperty("qa_environment"));
        extentTest.info("Clicking the contact link");
        mainPage.contactButton.click();
        extentTest.info("Entering the name");
        contactPage.name.sendKeys("sending name");
        extentTest.info("Entering the email");
        contactPage.email.sendKeys("sendingfakeemail@gmail.com");
        extentTest.info("Entering the phone number");
        contactPage.phone.sendKeys("99999999");
        extentTest.pass("Sending the subject");
        contactPage.subject.sendKeys("sending the subject");
        extentTest.pass("sending the message");
        contactPage.message.sendKeys("I d like to cancel");
        extentTest.pass("clicking");
        contactPage.submitButton.click();
        extentTest.pass("clicked on the click button");
        ReusableMethods.waitForVisibility(contactPage.errorMessage,3);
        extentTest.pass("Found the message");
        //This assertion will fail so we ll get ss that is attached to html report
        Assert.assertTrue(contactPage.errorMessage.getText().contains("Errors occured, please try againn"));
        extentTest.pass("TEST CASE PASSED");


    }
}
