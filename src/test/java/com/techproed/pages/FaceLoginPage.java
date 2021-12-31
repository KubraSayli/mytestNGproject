package com.techproed.pages;

import com.techproed.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FaceLoginPage {
    /*
Create a new FaceLoginPage in the pages package
Create a new test class: FaceLogin
Create a test method: logInTest() and test the following user story
When user enter invalid credentials, we should see sign up page
https://www.facebook.com/
Username: fakeusername
Password: fakepassword
     */

    //We will initialize the driver using a constructor
    public FaceLoginPage(){
        //PageFactory has initElements method. It is used to initialize the driver in page object model
        PageFactory.initElements(Driver.getDriver(),this);
    }
    //We locate the elements using FindBy annotation
    @FindBy(id="email")
    public WebElement username;
    @FindBy(id="pass")
    public WebElement password;
    @FindBy(xpath = "//button[@type='submit']")
    public WebElement loginButton;
    @FindBy(className = "_9ay7")
    public WebElement errorMessage;

}
