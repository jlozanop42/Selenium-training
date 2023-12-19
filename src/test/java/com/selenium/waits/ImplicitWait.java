package com.selenium.waits;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class ImplicitWait {

    WebDriver driver;


    @BeforeClass
    public void setupDriver() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @BeforeMethod
    public void goToMainPage() {
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/seleniumPractise");
    }

    @Test
    public void addToCartAndCheckout() {
        List<String> itemsToAdd = Arrays.asList("Cucumber", "Tomato", "Brinjal");
        addItemsToCart(itemsToAdd);
        proceedToCheckout(itemsToAdd.size());
        applyPromoCode("rahulshettyacademy");
    }

    public void addItemsToCart(List<String> itemsToAdd) {
        List<WebElement> descriptionTitles = driver.findElements(By.cssSelector("h4.product-name"));
        List<WebElement> addToCartButtons = driver.findElements(By.cssSelector("div.products button"));

        for(int i = 0; i < descriptionTitles.size(); i++) {
            if(itemsToAdd.contains(descriptionTitles.get(i).getText().split(" ")[0])) {
                addToCartButtons.get(i).click();
            }
        }
    }

    public void proceedToCheckout(int expectedAmountOfItems) {
        driver.findElement(By.cssSelector("a.cart-icon img")).click();
        Assert.assertEquals(driver.findElements(By.cssSelector("div.cart ul.cart-items li")).size(),
                expectedAmountOfItems);
        driver.findElement(By.cssSelector("div.cart button")).click();
    }

    public void applyPromoCode(String promoCode) {
        WebElement applyButton = driver.findElement(By.cssSelector("button.promoBtn"));
        driver.findElement(By.cssSelector("input.promoCode")).sendKeys(promoCode);
        String totalBeforeDiscount = driver.findElement(By.cssSelector("span.discountAmt")).getText();
        System.out.println(totalBeforeDiscount);
        applyButton.click();
        String totalAfterDiscount = driver.findElement(By.cssSelector("span.discountAmt")).getText();
        driver.findElement(By.cssSelector("span.promoInfo"));
        Assert.assertEquals(driver.findElement(By.cssSelector("span.promoInfo")).getText(), "Code applied ..!");
        System.out.println(totalAfterDiscount);

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();}
}
