package com.selenium.cart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CartTes {

    WebDriver driver;

    WebDriverWait wait;


    @BeforeClass
    public void setupDriver() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @BeforeMethod
    public void goToMainPage() {
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/seleniumPractise");
    }

    @Test
    public void addToCartWithDirectSelector() throws InterruptedException {
        driver.findElement(By.xpath("//h4[contains(.,'Cucumber')]//parent::div//button")).click();
        Thread.sleep(5000);
    }

    @Test
    public void addToCartIterating() {
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
        wait.until(ExpectedConditions.visibilityOf(applyButton));
        driver.findElement(By.cssSelector("input.promoCode")).sendKeys(promoCode);
        String totalBeforeDiscount = driver.findElement(By.cssSelector("span.discountAmt")).getText();
        applyButton.click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("span.promoInfo"))));
        String totalAfterDiscount = driver.findElement(By.cssSelector("span.discountAmt")).getText();
        Assert.assertTrue(Integer.parseInt(totalBeforeDiscount) < Integer.parseInt(totalAfterDiscount));

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
