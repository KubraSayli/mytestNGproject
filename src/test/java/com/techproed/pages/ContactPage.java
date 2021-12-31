package com.techproed.pages;

import com.techproed.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
public class ContactPage {
    public ContactPage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }
    @FindBy(name="NameSurname")
    public WebElement name;
    @FindBy(id="Email")
    public WebElement email;
    @FindBy(id="PhoneNo")
    public WebElement phone;
    @FindBy(id="Subject")
    public WebElement subject;
    @FindBy(id="Message")
    public WebElement message;
    @FindBy(xpath = "//input[@type='submit']")
    public WebElement submitButton;
    @FindBy(id="divMessageResult")
    public WebElement errorMessage;
}
