package com.techproed.tests;

import com.techproed.pages.FaceLoginPage;
import com.techproed.utilities.Driver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Day13_FaceLogin {
    /*
    Create a new test class: FaceLogin
Create a test method: logInTest() and test the following user story
When user enter invalid credentials, we should see sign up page
https://www.facebook.com/
Username: fakeusername
Password: fakepassword
     */
    FaceLoginPage faceLoginPage;
    @Test
    public void logInTest() throws InterruptedException {
        Driver.getDriver().get("https://www.facebook.com/");
        FaceLoginPage faceLoginPage = new FaceLoginPage();
        faceLoginPage.username.sendKeys("kubra");
        faceLoginPage.password.sendKeys("123456");
        faceLoginPage.loginButton.click();
        Thread.sleep(1000);
        String errorMessage = "Girdiğin şifre yanlış. Şifreni mi Unuttun?";
        Assert.assertEquals(faceLoginPage.errorMessage.getText(),"Girdiğin şifre yanlış. Şifreni mi Unuttun?");
    }


}
