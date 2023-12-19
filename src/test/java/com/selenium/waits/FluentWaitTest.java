package com.selenium.waits;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class FluentWaitTest {
    WebDriver driver;

    Wait<WebDriver> wait;


    @BeforeClass
    public void setupDriver() {
        driver = new ChromeDriver();
    }

    @BeforeMethod
    public void goToMainPage() {
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
    }

    @Test
    public void withExplicitWait() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.cssSelector("#start button")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish")));
        WebElement helloWorldMessage = driver.findElement(By.id("finish"));
        System.out.println(helloWorldMessage.getText());
        Assert.assertEquals(helloWorldMessage.getText(), "Hello World!");
    }

    @Test
    public void withFluentWait() {
        wait = new FluentWait<>(driver).
                withTimeout(Duration.ofSeconds(10)).
                pollingEvery(Duration.ofSeconds(3)).
                ignoring(NoSuchElementException.class);

        driver.findElement(By.cssSelector("#start button")).click();
        wait.until(driver -> {
            WebElement helloWorldElement = driver.findElement(By.id("finish"));
            return helloWorldElement.isDisplayed() ? helloWorldElement : null;
        });
        WebElement helloWorldMessage = driver.findElement(By.id("finish"));
        System.out.println(helloWorldMessage.getText());
        Assert.assertEquals(helloWorldMessage.getText(), "Hello World!");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();}
}
