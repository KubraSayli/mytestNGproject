package com.techproed.homeworks;

import com.techproed.utilities.ConfigReader;
import com.techproed.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class RoomReservationTest {
    //Create a class: RoomReservationTest
//Click on Hotel Management
//Click on Room reservations
//Click on Add Room Reservation
//Enter All required fields
//Click Save
//Verify the message: RoomReservation was inserted successfully
//Click OK
    //just do happy path
    //to practice, do a negative test
    WebElement username;
    WebElement password;
    WebElement loginButton;

    @BeforeMethod
    public void logIn() {
        Driver.getDriver().get(ConfigReader.getProperty("qa_environment"));
        WebElement mainPageLoginButton = Driver.getDriver().findElement(By.linkText("Log in"));
        mainPageLoginButton.click();
        username = Driver.getDriver().findElement(By.id("UserName"));
        password = Driver.getDriver().findElement(By.id("Password"));
        loginButton = Driver.getDriver().findElement(By.id("btnSubmit"));
    }

    @AfterMethod
    public void tearDown() {
        Driver.closeDriver();

    }

    @Test
    public void roomReservationHappyPath()  {

        username.sendKeys("manager");
        password.sendKeys("Manager2!");
        loginButton.click();
        WebElement hotelManagement = Driver.getDriver().findElement(By.linkText("Hotel Management"));
        hotelManagement.click();
        //Click on Room reservations:
        WebElement roomResButton = Driver.getDriver().findElement(By.partialLinkText("reservations"));
        roomResButton.click();

        //Click on Add Room Reservation
        WebElement addRrBtn = Driver.getDriver().findElement(By.xpath("//span[@class='hidden-480']"));
        addRrBtn.click();

        //Enter All required fields
        WebElement selectUser = Driver.getDriver().findElement(By.xpath("//select[@id='IDUser']"));
        Select optionsIDUser = new Select(selectUser);
        optionsIDUser.selectByVisibleText("manager");

        WebElement selectHotel = Driver.getDriver().findElement(By.xpath("//select[@id='IDHotelRoom']"));
        Select optionsSelectHotel = new Select(selectHotel);
        optionsSelectHotel.selectByIndex(6);

        WebElement price = Driver.getDriver().findElement(By.xpath("//input[@placeholder='Price']"));
        price.sendKeys("800");

        WebElement dateStartCal = Driver.getDriver().findElement(By.xpath("(//i[@class='fa fa-calendar'])[1]"));
        dateStartCal.click();

        WebElement startDate = Driver.getDriver().findElement(By.xpath("//tbody//tr[4]//td[6]"));
        startDate.click();

        WebElement endDateCal = Driver.getDriver().findElement(By.xpath("(//i[@class='fa fa-calendar'])[2]"));
        endDateCal.click();

        WebElement endDate = Driver.getDriver().findElement(By.xpath("//tbody//tr[6]//td[1]"));
        endDate.click();
/*
LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String date = formatter.format(currentDate.plusDays(2));
        crysPageObj.dateStart.sendKeys(date);
 */

        WebElement adultAmount = Driver.getDriver().findElement(By.xpath("//input[@placeholder='AdultAmount']"));
        adultAmount.sendKeys("2");

        WebElement childrenAmount = Driver.getDriver().findElement(By.xpath("//input[@placeholder='ChildrenAmount']"));
        childrenAmount.sendKeys("2");

        WebElement contactNameSurname = Driver.getDriver().findElement(By.xpath("//input[@placeholder='ContactNameSurname']"));
        contactNameSurname.sendKeys("Brown");

        WebElement contactPhone = Driver.getDriver().findElement(By.xpath("//input[@placeholder='ContactPhone']"));
        contactPhone.sendKeys("(123) 456-7899");

        WebElement contactEmail = Driver.getDriver().findElement(By.xpath("//input[@placeholder='ContactEmail']"));
        contactEmail.sendKeys("xyz@gmail.com");

        WebElement notes = Driver.getDriver().findElement(By.xpath("//input[@placeholder='Notes']"));
        notes.sendKeys("I want a room with sunrise view");

        WebElement approvedBox = Driver.getDriver().findElement(By.xpath("//input[@data-val-required='The Approved field is required.']"));
        approvedBox.click();

        WebElement paidBox = Driver.getDriver().findElement(By.xpath("//input[@data-val-required='The IsPaid field is required.']"));
        paidBox.click();

        //Click Save
        WebElement saveButton = Driver.getDriver().findElement(By.xpath("//button[@id='btnSubmit']"));
        saveButton.click();

        //Verify the message: RoomReservation was inserted successfully

        WebDriverWait wait = new WebDriverWait(Driver.getDriver(),10);
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='bootbox-body']")));
        String actualSuccessMessage = successMessage.getText();
        System.out.println(actualSuccessMessage);
        String expectedSuccessMessage = "RoomReservation was inserted successfully";
        Assert.assertEquals(actualSuccessMessage,expectedSuccessMessage);

        WebElement okButton = Driver.getDriver().findElement(By.xpath("//button[@data-bb-handler='ok']"));
        okButton.click();



    }
}