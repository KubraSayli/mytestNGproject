package com.techproed.tests;


import com.techproed.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class StageMovementTest extends TestBase {
    /*
When user is on the page
Click on Add To Cart
And verifies the product is added to the cart
And verifies the color, size, quantity, price, shipping, Total Price
(Example: Color : Orange, Size:S, Quanty:1 ,Price : $16.51, Shipping: $2.00, Total Price: $18.51)
And clicks on Proceed to checkout
Then verify Summary card is displayed(SELECT A CORE ELEMENT ON THAT PAGE FOR VERIFICATION: EX SHOPPING-CART SUMMARY)
Then verify product is In stock
Then verify the Unit price matches the price that is on the Add To Card page
And user clicks on + sign
Then verifies the TOTAL price is 2*price+shipping
And click on Proceed to checkout
Then verify sign in page displayed
Then user enters username password and sign in
Then Verify user in on the Address stage
And click on Proceed to checkout
Then verify user moves to Shipping stage(SHIPPING)
And click on Proceed to checkout
Then verify the error message: You must agree to the terms of service before continuing.
Then click on x to exit out
And click on Terms of service checkbox
And click on Proceed to checkout
Then verify user is on Payment stage(PLEASE CHOOSE YOUR PAYMENT METHOD)
 */
    @Test
    public void test() throws InterruptedException {

        driver.get("http://automationpractice.com/index.php");
        driver.manage().window().maximize();

        //Click on Add To Cart
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.PAGE_DOWN).perform();
        actions.sendKeys(Keys.ARROW_DOWN).perform();
        actions.sendKeys(Keys.ARROW_DOWN).perform();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement img = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//img[@title='Blouse'])[1]")));
        actions.moveToElement(img).perform();
        //verify the product is added to the cart
        WebElement addToCart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[.='Add to cart'])[2]")));
        addToCart.click();
        Thread.sleep(3000);
        WebElement title1 = driver.findElement(By.xpath("//h2[contains(.,'Product successfully ')]"));
        System.out.println(title1.getText());
        Thread.sleep(3000);
        Assert.assertTrue(title1.isDisplayed());

        //verifies the color, size, quantity, price, shipping, Total Price:
        //quantity
        WebElement quantity = driver.findElement(By.id("layer_cart_product_quantity"));
        String stringActualQuantity = quantity.getText();
        String expectedquantity = "1";
        Assert.assertEquals(stringActualQuantity, expectedquantity);

        //price
        WebElement chartPrice = driver.findElement(By.id("layer_cart_product_price"));
        String stringActualChartPrice = chartPrice.getText();
        String expectedprice = "$27.00";
        Assert.assertEquals(stringActualChartPrice, expectedprice);

        //color,size
        WebElement color_and_size = driver.findElement(By.id("layer_cart_product_attributes"));
        String actualc_and_size = color_and_size.getText();
        String expectedc_and_size = "Black, S";
        Assert.assertEquals(actualc_and_size, expectedc_and_size);

        //shipping
        WebElement shipping_cost = driver.findElement(By.xpath("//span[@class='ajax_cart_shipping_cost']"));
        String stringActualShippingCost = shipping_cost.getText();
        String expected_shipping_cost = "$2.00";
        Assert.assertEquals(stringActualShippingCost, expected_shipping_cost);

        //total price
        WebElement total_price = driver.findElement(By.xpath("//span[@class='ajax_block_cart_total']"));
        String stringActualTotalPrice = total_price.getText();
        String expected_total_price = "$29.00";
        Assert.assertEquals(stringActualTotalPrice, expected_total_price);

        //clicks on Proceed to checkout
        driver.findElement(By.partialLinkText("Proceed")).click();

        //verify Summary card is displayed
        actions.sendKeys(Keys.PAGE_DOWN).perform();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement summary = driver.findElement(By.id("cart_title"));
        Assert.assertTrue(summary.isDisplayed());

        //verify product is In stock
        WebElement inStock = driver.findElement(By.xpath("//*[.='In stock']"));
        Assert.assertTrue(inStock.isDisplayed());

        //verify the Unit price matches the price that is on the Add To Card page
        String priceInSummary = driver.findElement(By.id("product_price_2_7_0")).getText();
        Assert.assertEquals(priceInSummary,stringActualChartPrice);

        //verify the TOTAL price is 2*price+shipping
        WebElement plusSign = driver.findElement(By.xpath("//span//i[@class='icon-plus']"));
        plusSign.click();
        Thread.sleep(3000);

        double doubleShipping = Double.valueOf(stringActualShippingCost.substring(1));
        double expUnitPrice = (Double.valueOf(priceInSummary.substring(1)))*2+doubleShipping;
        System.out.println(expUnitPrice);

        String total = driver.findElement(By.xpath("//td[@id='total_price_container']")).getText();
        double doubleTotal = Double.valueOf(total.substring(1));
        System.out.println(doubleTotal);
        Assert.assertEquals(doubleTotal,expUnitPrice);

        driver.findElement(By.linkText("Proceed to checkout")).click();

        //verify sign in page displayed
        WebElement signInPageText= driver.findElement(By.xpath("//h1[contains(text(),'hentication')]"));
        Assert.assertTrue(signInPageText.isDisplayed());

        //verify user enters username password and sign in
        WebElement email = driver.findElement(By.id("email"));
        email.sendKeys("xxyyzzerte20@gmail.com");
        WebElement pswd = driver.findElement(By.id("passwd"));
        pswd.sendKeys("12345");
        driver.findElement(By.id("SubmitLogin")).click();

        //Verify user in on the Address stage
        WebElement addressText = driver.findElement(By.xpath("//h1[contains(text(),'Addresses')]"));
        Assert.assertTrue(addressText.isDisplayed());
        actions.sendKeys(Keys.END).perform();
        driver.findElement(By.name("processAddress")).click();

        //verify user moves to Shipping stage(SHIPPING)
        WebElement shippingText = driver.findElement(By.className("page-heading"));
        Assert.assertTrue(shippingText.isDisplayed());
        actions.sendKeys(Keys.PAGE_DOWN).perform();
        Thread.sleep(3000);
        WebElement shippingButton=driver.findElement(By.name("processCarrier"));
        shippingButton.click();

        //verify the error message: You must agree to the terms of service before continuing.
        WebElement errorMessage = driver.findElement(By.className("fancybox-error"));
        Assert.assertTrue(errorMessage.isDisplayed());
        //click on x to exit out
        driver.findElement(By.xpath("//a[@title='Close']")).click();
        //click on Terms of service checkbox
        WebElement checkBox=  driver.findElement(By.id("cgv"));
        checkBox.click();
        shippingButton.click();
        //verify user is on Payment stage(PLEASE CHOOSE YOUR PAYMENT METHOD)
        WebElement paymentText = driver.findElement(By.className("page-heading"));
        Assert.assertTrue(paymentText.isDisplayed());

    }
}