package com.techproed.tests;
import com.techproed.pages.DefaultPage;
import com.techproed.pages.LoginPage;
import com.techproed.pages.MainPage;
import com.techproed.pages.RoomReservationPage;
import com.techproed.utilities.ConfigReader;
import com.techproed.utilities.Driver;
import com.techproed.utilities.ReusableMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
public class Day14_RoomReservationWithPom {
    MainPage mainPage;
    LoginPage loginPage;
    DefaultPage defaultPage;
    RoomReservationPage roomReservationPage;
    //This ll take me to the Room Reservation Page
    @BeforeMethod
    public void setUp(){
        Driver.getDriver().get(ConfigReader.getProperty("qa_environment"));
        mainPage=new MainPage();
        mainPage.mainPageLogin.click();
        loginPage=new LoginPage();
        loginPage.username.sendKeys(ConfigReader.getProperty("manager_username"));
        loginPage.password.sendKeys(ConfigReader.getProperty("manager_password"));
        loginPage.loginButton.click();
        defaultPage=new DefaultPage();
        defaultPage.hotelManagement.click();
        defaultPage.roomReservations.click();
    }
    @AfterMethod
    public void tearDown(){
        Driver.closeDriver();
    }
    @Test
    public void roomReservationTest() throws InterruptedException {
        ReusableMethods.waitForPageToLoad(10);
        roomReservationPage=new RoomReservationPage();
        roomReservationPage.addRoomReservationButton.click();
        Select idUserOptions=new Select(roomReservationPage.idUser);
        idUserOptions.selectByIndex(2);
        Select idHotelOptions=new Select(roomReservationPage.idHotelRoom);
        idHotelOptions.selectByIndex(3);
        roomReservationPage.price.sendKeys("600");
        roomReservationPage.dateStart.sendKeys("01/23/2022");
        roomReservationPage.dateEnd.sendKeys("01/24/2022");
        roomReservationPage.adultAmount.sendKeys("2");
        roomReservationPage.childrenAmount.sendKeys("3");
        roomReservationPage.nameAndSurname.sendKeys("James Bond");
//        Thread.sleep(2000);
        roomReservationPage.contactPhone.sendKeys("2222222222");
        roomReservationPage.contactEmail.sendKeys("abc@gmail.com");
        roomReservationPage.notes.sendKeys("Testing");
        roomReservationPage.approved.click();
        roomReservationPage.isPaid.click();
        roomReservationPage.saveButton.click();
//        WHEN WE NEED EXPLICIT WAIT< WE CAN USE THE ONE THAT IS IN TEH REUSABLE METHODS CLASS
        WebElement actualSuccessMessage= ReusableMethods.waitForVisibility(By.className("bootbox-body"),10);
        //USING THE OTHER EXMPLICIT WAIT
        //WebElement actualSuccessMessage= ReusableMethods.waitForVisibility(Driver.getDriver().findElement(By.className("bootbox-body")),10);
        // WebDriverWait wait=new WebDriverWait(Driver.getDriver(),10);
        // WebElement actualSuccessMessage=wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("bootbox-body")));
//        String actualReservationSuccessMessage=roomReservationPage.successMessage.getText();
//        String expectedReservationSuccessMessage="RoomReservation was inserted successfully";
//       boolean isTrue=wait.until(ExpectedConditions.textToBe(By.xpath("//div[@class='bootbox-body']"),ConfigReader.getProperty("expectedReservationSuccessMessage")));
//       Assert.assertTrue(isTrue);
        String actualSuccessMessageText=actualSuccessMessage.getText();
        Assert.assertEquals(actualSuccessMessageText,ConfigReader.getProperty("expectedReservationSuccessMessage"));
        // Assert.assertEquals(roomReservationPage.actualSuccessMessage.getText(),ConfigReader.getProperty("expectedReservationSuccessMessage"));
    }
}
