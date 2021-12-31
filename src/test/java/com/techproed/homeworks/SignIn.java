package com.techproed.homeworks;


import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;
/*
   ENTER ALL REQUIRED FIELDS AND VERIFY THE ACCOUNT CREATION IS SUCCESSFUL
1. Create a class SignInHomework
2. Go to http://automationpractice.com/index.php
3. Click on sign in button
4.Send your email and click on create an account button
5.Verify the Text : CREATE AN ACCOUNT
6. Verify the Text : YOUR PERSONAL INFORMATION
7. Verify the Text : Title
8. Select your title
9. Enter your first name
10. Enter your last name
11. Enter your email
12. Enter your password
13. ENTER DATE OF BIRTH
14. Click on Sign up for our newsletter!
15. Enter your first name
16. Enter your last lane
17. Enter your company
18. Enter your Address
19. Enter your City
20. SELECT STATE
21. Enter Postal Code
22.SELECT COUNTRY
23. Enter additional information
24. Enter home phone
25. Enter mobile phone
26. Enter reference name
27. Click Register
20. Then verify MY ACCOUNT is displayed on the home page-SEE BELOW IMAGE

    */
public class SignIn {

    WebDriver driver;
    Faker faker;


    @BeforeClass
    public void createAccount(){
        faker = new Faker();
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://automationpractice.com/index.php");
        WebElement signIn = driver.findElement(By.xpath("//a[@class='login']"));
        signIn.click();
        WebElement emailBox = driver.findElement(By.xpath("//input[@name='email_create']"));
        emailBox.sendKeys( faker.internet().emailAddress()+ Keys.ENTER);
        /*
        String valueOfEmail = driver.findElement(By.id("email")).getAttribute("value");
        Assert.assertEquals(valueOfEmail,fakeMail);
         */
    }

    //Verify the Text : CREATE AN ACCOUNT
    @Test
    public void verifyCreateAccountText(){
        WebElement createAccountText = driver.findElement(By.className("page-heading"));
        Assert.assertTrue(createAccountText.isDisplayed(), "FAIL: CREATE AN ACCOUNT is not displayed.");
    }

    //Verify the Text : YOUR PERSONAL INFORMATION
    @Test
    public void verifyPersonalInfoText(){
        WebElement personalInfoText = driver.findElement(By.className("page-subheading"));
        Assert.assertTrue(personalInfoText.isDisplayed(),"FAIL! ,Your Personal Information' text is not displayed");
    }

    //Verify the Text : Title
    @Test
    public void verifyTitle(){
        WebElement titleText = driver.findElement(By.xpath("//label[.='Title']"));
        Assert.assertFalse(!titleText.isDisplayed(), "FAIL! , Title text is not displayed");
    }

    //
    @Test
    public void writingPersonalInfo(){
        driver.findElement(By.xpath("(//input[@type='radio'])[2]")).click();

        faker = new Faker();

        driver.findElement(By.xpath("//input[@name='customer_firstname']")).sendKeys(faker.name().firstName());
        driver.findElement(By.xpath("//input[@name='customer_lastname']")).sendKeys(faker.name().lastName());
        driver.findElement(By.xpath("//input[@data-validate='isEmail']"));  //it was displayed by default
        driver.findElement(By.id("passwd")).sendKeys(faker.internet().password());

        WebElement days = driver.findElement(By.id("days"));
        Select dayOptions = new Select(days);
        dayOptions.selectByValue("5");

        WebElement months = driver.findElement(By.id("months"));
        Select monthOptions = new Select(months);
        monthOptions.selectByValue("12");

        WebElement years = driver.findElement(By.id("years"));
        Select yearOptions = new Select(years);
        yearOptions.selectByIndex(18);

         driver.findElement(By.id("newsletter")).click();
         //First name and last name is filled in by default
        driver.findElement(By.id("company")).sendKeys("BestCompany");
        driver.findElement(By.id("address1")).sendKeys(faker.address().fullAddress());
        driver.findElement(By.id("address2")).sendKeys(faker.address().buildingNumber());
        driver.findElement(By.id("city")).sendKeys(faker.address().city());

        WebElement states = driver.findElement(By.id("id_state"));
        Select stateOptions = new Select(states);
        stateOptions.selectByIndex(32);

        driver.findElement(By.id("postcode")).sendKeys("67895");
        driver.findElement(By.id("other")).sendKeys("everything is gonna be allright");
        driver.findElement(By.id("phone")).sendKeys(faker.phoneNumber().cellPhone());
        driver.findElement(By.id("phone_mobile")).sendKeys(faker.phoneNumber().cellPhone());
        driver.findElement(By.id("alias")).sendKeys("Reference");
        driver.findElement(By.xpath("//span[.='Register']")).click();
        driver.quit();



    }
















}
