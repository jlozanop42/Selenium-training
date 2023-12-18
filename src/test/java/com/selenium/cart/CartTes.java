package com.selenium.cart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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
        List<String> productNamesToClick = Arrays.asList("Cucumber", "Tomato", "Brinjal");
        List<WebElement> descriptionTitles = driver.findElements(By.cssSelector("h4.product-name"));
        List<WebElement> addToCartButtons = driver.findElements(By.cssSelector("div.products button"));
        System.out.println(descriptionTitles);

        for(int i = 0; i < descriptionTitles.size(); i++) {
            System.out.println(descriptionTitles.get(i).getText().split(" ")[0]);
            if(productNamesToClick.contains(descriptionTitles.get(i).getText().split(" ")[0])) {
                addToCartButtons.get(i).click();
            }
        }
        driver.findElement(By.cssSelector("a.cart-icon img")).click();
        Assert.assertEquals(driver.findElements(By.cssSelector("div.cart ul.cart-items li")).size(), 3);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
