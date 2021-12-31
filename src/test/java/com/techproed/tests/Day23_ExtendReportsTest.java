package com.techproed.tests;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.techproed.pages.ContactPage;
import com.techproed.pages.MainPage;
import com.techproed.utilities.ConfigReader;
import com.techproed.utilities.Driver;
import com.techproed.utilities.ReusableMethods;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Day23_ExtendReportsTest {

    protected static ExtentReports extentReports;
    protected static ExtentTest extentTest;
    protected static ExtentHtmlReporter extentHtmlReporter;

    MainPage mainPage=new MainPage();
    ContactPage contactPage = new ContactPage();

    @BeforeTest
    public void setUpTest(){
        extentReports=new ExtentReports();
        //Path of the report               //C ve comp name folder name report name
        String filePath = System.getProperty("user.dir")+"/test-output/myprojectreport.html";
        //Creating the html report in this path
        extentHtmlReporter=new ExtentHtmlReporter(filePath);
        //Attaching the html report to the ExtendReports
        extentReports.attachReporter(extentHtmlReporter);
        //We can add custom information on our report. These information is optional
        extentReports.setSystemInfo("Environment","QA Environment");
        extentReports.setSystemInfo("Browser",ConfigReader.getProperty("browser"));
        extentReports.setSystemInfo("Automation Engineer", "Mrs. Best Tester");
        extentHtmlReporter.config().setDocumentTitle("Crystal Keys Hotel Reports");
        extentHtmlReporter.config().setReportName("Crystal Keys Automation Reports");
    }

    @AfterTest
    public void tearDownTest(){
        //Closing the extent reports:
        extentReports.flush();
    }

    @AfterMethod
    public void tearDownMethod(ITestResult result) throws IOException {
        //If test case fails
        if(result.getStatus()==ITestResult.FAILURE){
            // naming the screenshot with the current date to avoid duplication
            String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
            // TakesScreenshot is an interface of selenium that takes the screenshot
            TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
            File source = ts.getScreenshotAs(OutputType.FILE);
            // full path to the screenshot location          //folder       screenshotname=Screenshots+date+.png
            String target = System.getProperty("user.dir") + "/test-output/Screenshots/"+ date + ".png";
            File finalDestination = new File(target);
            // save the screenshot to the path given
            FileUtils.copyFile(source, finalDestination);
            //This line will mark the test method as FAILED
            extentTest.fail("TEST CASE IS FAILED: "+result.getName());
        }else if(result.getStatus()==ITestResult.SKIP){
            extentTest.skip("TEST CASE IS SKIPPED: "+result.getName());
        }
    }



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
        Assert.assertTrue(contactPage.errorMessage.getText().contains("Errors occured, please try againn"));
        extentTest.pass("TEST CASE PASSED");

    }
}
