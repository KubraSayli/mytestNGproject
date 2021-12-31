package com.techproed.tests;
import com.techproed.utilities.ConfigReader;
import com.techproed.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class Day12_HotelRoomTest {
    /*
    //Create a clickOnLogin method
//Click on Hotel Management
//Click on Hotel Rooms
//Click on Add Hotel Room
//Enter All required fields
//To enter a price, we can send keys, OR we can use actions class to drag and drop
//Click Save
//Verify the message: HotelRoom was inserted successfully
//Click OK

     */

    //We can improve this code
    //Create a method and write the code for pre conditions.
    WebElement username;
    WebElement password;
    WebElement loginButton;
    @BeforeMethod
    public void logIn(){
        Driver.getDriver().get(ConfigReader.getProperty("qa_environment"));
        WebElement mainPageLoginButton=Driver.getDriver().findElement(By.linkText("Log in"));
        mainPageLoginButton.click();
        username=Driver.getDriver().findElement(By.id("UserName"));
        password=Driver.getDriver().findElement(By.id("Password"));
        loginButton=Driver.getDriver().findElement(By.id("btnSubmit"));
    }
    @AfterMethod
    public void tearDown(){
        Driver.closeDriver();
    }
    @Test
    public void createHotel() throws InterruptedException {
        username.sendKeys("manager");
        password.sendKeys("Manager2!");
        loginButton.click();
        WebElement hotelManagement=Driver.getDriver().findElement(By.linkText("Hotel Management"));
        hotelManagement.click();
        WebElement hotelRooms=Driver.getDriver().findElement(By.partialLinkText("Hotel Rooms"));
        hotelRooms.click();
        WebElement addHotelRoomsButton=Driver.getDriver().findElement(By.partialLinkText("ADD HOTELROOM"));
        addHotelRoomsButton.click();

        WebElement idHotel = Driver.getDriver().findElement(By.id("IDHotel"));
        Select idHotelOptions = new Select(idHotel);
        idHotelOptions.selectByIndex(1);

        WebElement code = Driver.getDriver().findElement(By.id("Code"));
        code.sendKeys("1234");

        WebElement name = Driver.getDriver().findElement(By.id("Name"));
        name.sendKeys("Royal Family");

        WebElement location = Driver.getDriver().findElement(By.id("Location"));
        location.sendKeys("354");

        WebElement description = Driver.getDriver().findElement(By.xpath("//textarea[@role='textbox']"));
        description.sendKeys("This is the best room in the Hotel");



        WebElement price=Driver.getDriver().findElement(By.id("Price"));
        price.sendKeys("700");
        WebElement roomType=Driver.getDriver().findElement(By.id("IDGroupRoomType"));
        Select roomTypeOptions=new Select(roomType);
        roomTypeOptions.selectByVisibleText("Studio");
        WebElement adultCount=Driver.getDriver().findElement(By.id("MaxAdultCount"));
        adultCount.sendKeys("2");
        WebElement maxChildrenCount=Driver.getDriver().findElement(By.id("MaxChildCount"));
        maxChildrenCount.sendKeys("3");
        WebElement isApproved=Driver.getDriver().findElement(By.id("IsAvailable"));
        isApproved.click();

        WebElement saveButton = Driver.getDriver().findElement(By.id("btnSubmit"));
        saveButton.click();

        //This fixes the issue but this is not the best way to solve
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        //THIS CODE FAILED DUE TO WAIT ISSUE
        //WebElement successMessage=Driver.getDriver().findElement(By.className("bootbox-body"));
        //String successMessageText=successMessage.getText();
        //Assert.assertEquals(successMessageText,"HotelRoom was inserted successfully");


        //Try using explicit wait. Implicit wait did not work here
        WebDriverWait wait=new WebDriverWait(Driver.getDriver(),10);
        //WebElement successMessage=wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("bootbox-body")));
        //String successMessageText=successMessage.getText();
        //Assert.assertEquals(successMessageText,"HotelRoom was inserted successfully");


        //textToBe: Wait for By.className("bootbox-body") locator up to 10 seconds
        //If text of that element is HotelRoom was inserted successfully, then return true, otherwise return false
        boolean isTrue=wait.until(ExpectedConditions.textToBe(By.className("bootbox-body"),"HotelRoom was inserted successfully"));
        Assert.assertTrue(isTrue);

        //In Summary. We solved the wait issue in 3 ways
        //1.   Thread.Sleep(2000) -> Hard wait, not recommended

        //2.   WebDriverWait wait=new WebDriverWait(Driver.getDriver(),10);--> Using explicit wait
        //     WebElement successMessage=wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("bootbox-body")));
        //     String successMessageText=successMessage.getText();
        //     Assert.assertEquals(successMessageText,"HotelRoom was inserted successfully");

        //3.  WebDriverWait wait=new WebDriverWait(Driver.getDriver(),10);--> Using explicit wait
        //    boolean isTrue=wait.until(ExpectedConditions.textToBe(By.className("bootbox-body"),"HotelRoom was inserted successfully"));
        //    Assert.assertTrue(isTrue);

        WebElement okButton = Driver.getDriver().findElement(By.xpath("//button[@data-bb-handler='ok']"));
        okButton.click();

        /*
        HOMEWORK
        When user clicks on Hotel Roooms link
        End filters
        IdHotel :ADA
        IdGroup :Studio
        IsAvailable :true
        And click Search
        Then verify table name includes " Royal Family "
         */

    }
    @Test
    public void checkRoom() throws InterruptedException {
        username.sendKeys("manager");
        password.sendKeys("Manager2!");
        loginButton.click();
        WebElement hotelManagement=Driver.getDriver().findElement(By.linkText("Hotel Management"));
        hotelManagement.click();
        WebElement hotelRooms=Driver.getDriver().findElement(By.partialLinkText("Hotel Rooms"));
        hotelRooms.click();
        WebElement IdHotelCriteria = Driver.getDriver().findElement(By.xpath("//select[@id='lkpHotels']"));
        Select optionsIdHotel = new Select(IdHotelCriteria);
        optionsIdHotel.selectByVisibleText("Ada");

        WebElement IDGroupRoomType = Driver.getDriver().findElement(By.xpath("//select[@id='lkpGroupRoomTypes']"));
        Select optionsRoomType = new Select(IDGroupRoomType);
        optionsRoomType.selectByVisibleText("Studio");

        WebElement IsAvailable = Driver.getDriver().findElement(By.xpath("//select[@name='IsAvailable']"));
        Select optionsIsAvailable = new Select(IsAvailable);
        optionsIsAvailable.selectByValue("true");

       WebElement searchButton = Driver.getDriver().findElement(By.xpath("//button[@class='btn btn-sm yellow filter-submit margin-bottom']"));
       searchButton.click();
        Thread.sleep(5000);
        List <WebElement> filteredNames = Driver.getDriver().findElements(By.xpath("//tbody//td[4]"));
        boolean isTrue = false;
        for (WebElement each :filteredNames) {
            isTrue = each.getText().contains("Royal Family");
        }

        Assert.assertTrue(isTrue);

        /*
                List<String> allRoomNamesStr = new ArrayList<>();
        allRoomNames.stream().map(t->t.getText()).forEach(t-> allRoomNamesStr.add(t));
        Assert.assertTrue(allRoomNamesStr.contains("Royal Family"));

                 */
    }
}
