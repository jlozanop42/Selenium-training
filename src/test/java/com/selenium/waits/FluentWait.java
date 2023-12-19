package com.selenium.waits;

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

public class FluentWait {
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
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
    }

    @Test
    public void withExplicitWait() {
        driver.findElement(By.cssSelector("#start button")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish")));
        WebElement helloWorldMessage = driver.findElement(By.id("finish"));
        System.out.println(helloWorldMessage.getText());
        Assert.assertEquals(helloWorldMessage.getText(), "Hello World!");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();}
}
