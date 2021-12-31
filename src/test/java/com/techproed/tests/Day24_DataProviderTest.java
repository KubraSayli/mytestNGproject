package com.techproed.tests;

import com.techproed.pages.LoginPage;
import com.techproed.utilities.Driver;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Day24_DataProviderTest {
    @Test(dataProvider = "smoke test data")
    public void loginTest(String user, String pass){
        Driver.getDriver().get("https://qa-environment.crystalkeyhotels.com/Account/Logon");
        LoginPage loginPage = new LoginPage();
        loginPage.username.sendKeys(user);
        loginPage.password.sendKeys(pass);
        loginPage.loginButton.click();
        Assert.assertTrue(Driver.getDriver().findElement(By.id("divMessageResult")).getText().contains("Try again please"));

    }
    //get the data using data provider from the "smoke test data"
    @DataProvider(name = "smoke test data")
    public Object[][] getData(){
        /*
       {user1, pass1}
        user2, pass2
        user3, pass3
         */                    //  row, column
        Object[][] data = new Object[4][2];

        //Assigning test data
        //1 st row data
        data[0][0]="user1";
        data[0][1]="pass1";

        //2 nd row data
        data[1][0]="user2";
        data[1][1]="pass2";

        //3 rd row data
        data[2][0]="user3";
        data[2][1]="pass3";


        //4 rd row data
        data[3][0]="user4";
        data[3][1]="pass4";

        return data;
    }

}
