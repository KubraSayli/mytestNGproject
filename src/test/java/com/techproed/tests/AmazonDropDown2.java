package com.techproed.tests;

/*
Create A Class: AmazonDropdown
Create A Method dropdownTest
Go to https://www.amazon.com/
Find the element of the dropdown element. HINT: By.id(“searchDropdownBox")
Print the first selected option and assert if it equals “All Departments”
Select the 4th option by index (index of 3) and assert if the name is “Amazon Devices”.(after you select you need to use get first selected option method)
Print all of the dropdown options
Print the the total number of options in the dropdown
Check if Appliances is a drop down option. Print true if “Appliances” is an option. Print false otherwise.
BONUS: Check if the dropdown is in Alphabetical Order

*/

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AmazonDropDown2 {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://amazon.com");
    }

    @Test
    public void dropdownTest() {

        //Find the element of the dropdown element:
        WebElement dropdown = driver.findElement(By.xpath("//select[@aria-describedby='searchDropdownDescription']"));
        Select options = new Select(dropdown);
        List<WebElement> dropdownValues = options.getOptions();

        System.out.println();
        //Print the first selected option and assert if it equals “All Departments”
        String firstSelectedOption = options.getFirstSelectedOption().getText();
        System.out.println("The first selected option is : " + firstSelectedOption);
        Assert.assertEquals(firstSelectedOption, "All Departments", "First selected option is not All Departments");
        System.out.println();
        //Select the 4th option by index (index of 3) and assert if the name is “Amazon Devices”.
        // (after you select you need to use get first selected option method)
        options.selectByIndex(3);
        String fourthSelectedOption = options.getFirstSelectedOption().getText();
        Assert.assertEquals(fourthSelectedOption, "Amazon Devices","The fourth option is not Amazon Devices");
    }


    @Test
    public void dropdownTest2(){

        WebElement dropdown = driver.findElement(By.xpath("//select[@aria-describedby='searchDropdownDescription']"));
        Select options = new Select(dropdown);
        List<WebElement> dropdownValues = options.getOptions();
        //Print all of the dropdown options
        for(WebElement w : dropdownValues){
            System.out.println(w.getText());
        }

        System.out.println();
        //Print the the total number of options in the dropdown
        System.out.println("There are "+ dropdownValues.size() + " of elements in dropdown");
        System.out.println();

        //Check if Appliances is a drop down option. Print true if “Appliances” is an option. Print false otherwise.

        if (dropdownValues.contains("Appliances")){
            System.out.println("TRUE : “Appliances” is an option. ");
        }else{
            System.out.println("FALSE: “Appliances” is not an option");
        }
        System.out.println();

        //BONUS: Check if the dropdown is in Alphabetical Order
        List <String> stringValueList = new ArrayList<>(); //Creating a string list to be able to sort
        for(int i = 0; i<dropdownValues.size() ; i++){
            stringValueList.add(dropdownValues.get(i).getText());
        }


        List tempList = new ArrayList<>(); //Creating a temporary list to compare
        tempList.addAll(stringValueList);
        Collections.sort(tempList);

        Assert.assertTrue(stringValueList.equals(tempList),"Dropdown is not in alphabetical order"); //"Dropdown is not in alphabetical order"

    }
        @AfterMethod
    public void tearDown(){
        driver.quit();
        /*

        Lambda ile AmazonDropDown ödevinde dropdownlarda istenen dorpdown un olupl olmadığının kontrolu:

        List<String> allOtpinsStr = new ArrayList<>();
        allOptions.stream().map(t->t.getText()).forEach(t->allOtpinsStr.add(t));
        System.out.println("Bütün optionslar" + allOtpinsStr);
        boolean result = allOtpinsStr.stream().sorted().anyMatch(t->t.contains("Baby"));
        System.out.println("Baby Var Mı Yok mu? " + result);
         */

            /*
            //BONUS: Check if the dropdown is in Alphabetical Order
        List<String> sortedallOtpinsStr = new ArrayList<>();
        allOptions.stream().map(t->t.getText()).sorted().forEach(t->sortedallOtpinsStr.add(t));
        System.out.println("Sorted: " + sortedallOtpinsStr);
        Assert.assertEquals(allOtpinsStr,sortedallOtpinsStr);

buda Bonusolarak yazılan taskin Lambda ile sort eilip assert edilmesi
             */


    }
}