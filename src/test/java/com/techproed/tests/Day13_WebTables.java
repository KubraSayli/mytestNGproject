package com.techproed.tests;
import com.techproed.utilities.ConfigReader;
import com.techproed.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;
import java.util.Scanner;
public class Day13_WebTables {
    /*
    Create a class: WebTables
Create a method: setUp()
Log in  https://qa-environment.crystalkeyhotels.com/
//Click on Hotel Management
//Click on Hotel Rooms
Create a test method: entireTable() and Find the size of the entire table body and print all of headers
Create a test method: printRows() and Print all of the rows and print the element s on the 4th row
Create a test method: printCells() and a the total number of cells in the table body and print all of the cells
Create a test method: printColumns() and print Find the total number of columns and Print the elements of the 5th column
Create a test method: printData(int row, int column); This method should print the given cell. Example: printData(2,3); should print 2nd row,3rd column
     */
    WebElement username;
    WebElement password;
    WebElement loginButton;
    @BeforeMethod(alwaysRun = true)
    public void setUp() throws InterruptedException {
        Driver.getDriver().get(ConfigReader.getProperty("qa_environment"));
        WebElement mainPageLoginButton=Driver.getDriver().findElement(By.linkText("Log in"));
        mainPageLoginButton.click();
        username=Driver.getDriver().findElement(By.id("UserName"));
        password=Driver.getDriver().findElement(By.id("Password"));
        loginButton=Driver.getDriver().findElement(By.id("btnSubmit"));
        username.sendKeys("manager");
        password.sendKeys("Manager2!");
        loginButton.click();
        //We need a core element on the page to assert we are on the right page
        WebElement addUserButton=Driver.getDriver().findElement(By.xpath("//span[@class='hidden-480']"));
        Assert.assertTrue(addUserButton.isDisplayed(),"Login Test Fail");
        //NOTE: If any step is skipped, there might be synchronization issue. Then use wait
        WebElement hotelManagement=Driver.getDriver().findElement(By.linkText("Hotel Management"));
        hotelManagement.click();
        WebElement hotelRooms=Driver.getDriver().findElement(By.partialLinkText("Hotel Rooms"));
        hotelRooms.click();
    }
    // Find the size of the entire table body and print all of headers
    @Test(groups="regression1")
    public void entireTable() throws InterruptedException {
//        WebDriverWait wait = new WebDriverWait(Driver.getDriver(),30);
//        WebElement tBody = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody")));
        Thread.sleep(3000);
        WebElement tBody = Driver.getDriver().findElement(By.xpath("//tbody"));
        String tBodyText = tBody.getText();
        System.out.println(tBodyText);
        System.out.println("==========ENTIRE TABLE DATA===============");
        List<WebElement> tableData =  Driver.getDriver().findElements(By.xpath("//td"));
        for(WebElement each : tableData){
            System.out.println(each.getText());
        }
        System.out.println("There are "+tableData.size()+ " data in the table");
        //Printing the headers :
        System.out.println("======HEADERS=========");
        List <WebElement> allHeaders = Driver.getDriver().findElements(By.xpath("//th"));
        for(WebElement each : allHeaders){
            System.out.println(each.getText());
        }
    }
    //Print all of the rows and print the element s on the 4th row
    @Test(groups="regression1")
    public void printRows(){
        System.out.println("==========ALL ROWS=============");
        List <WebElement> allRows = Driver.getDriver().findElements(By.xpath("//tbody//tr"));
        int count = 1;
        for(WebElement each : allRows){
            System.out.println("Row "+count + each.getText());
            count++;
        }
        System.out.println("========4th ROW=================");
        WebElement forthRow = Driver.getDriver().findElement(By.xpath("//tbody//tr[4]"));
        System.out.println("This is the 4th row : "+ forthRow.getText());
    }
    //the total number of cells in the table body and print all of the cells
    @Test
    public void printCells(){
        System.out.println("======ALL BODY CELLS==========");
        List<WebElement> allCells = Driver.getDriver().findElements(By.xpath("//tbody//td"));
        for(WebElement each : allCells){
            System.out.println(each.getText());
        }
        System.out.println("There are "+allCells.size()+" data in the table body");
    }
    //Create a test method: printColumns() and Find the total number of columns and Print the elements of the 5th column
    @Test
    public void printColumns(){
        /*
        //th              -> we can use this xpath to find the total number of column
        //tbody/tr[1]/td  -> we can use this xpath to find the total number of column
         */
        //print Find the total number of columns
        System.out.println("=======================ALL COLUMN==============================");
        List<WebElement> allColumns=Driver.getDriver().findElements(By.xpath("//th"));
        int numOfColumn=allColumns.size();
        System.out.println("There are "+numOfColumn+" columns in the table");
        //Print the elements of the 5th column
        /*
         //tbody//td[5]      ->Gives the 5th column element in the table body
         //tbody// * //td[5] ->Gives the 5th column element in the table body
         //tbody//tr/td[5]   ->Gives the 5th column element in the table body
         */
        System.out.println("=======================ONLY 5TH COLUMN==============================");
        List<WebElement> fifthColumnData=Driver.getDriver().findElements(By.xpath("//tbody//td[5]"));
        for (WebElement each:fifthColumnData){
            System.out.println(each.getText());
        }
    }
    //Create a test method: printData(int row, int column);
    // This method should print the given cell.
    // Example: printData(2,3); should print 2nd row,3rd column
    //  23546
    public void printData(int row, int column){
        //Write an xpath for location the cell data
        //xpath for 2nd row,3rd column    //tbody//tr[2]//td[3]
        //xpath for 4th row,2nd column    //tbody//tr[4]//td[2]
        //xpath for 5th row,6th column    //tbody//tr[5]//td[6]
        //                  numbers are changing based on row and columns
        //  //tbody//tr[     2    ]//td[    3      ]
        //  //tbody//tr["+row+"]//td["+column+"]
        String dataXpath="//tbody//tr["+row+"]//td["+column+"]";
        WebElement data=Driver.getDriver().findElement(By.xpath(dataXpath));
        System.out.println(data.getText());
    }
    @Test
    public void printDataTest(){
        printData(2,3);//23546
        printData(4,6);
    }
}
