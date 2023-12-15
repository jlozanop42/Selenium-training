package com.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class SelectFlightTest {
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
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
    }

    @Test
    public void selectFlight() {
        WebElement originInput =  driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT"));
        originInput.click();

        WebElement origin = driver.findElement(By.cssSelector("#ctl00_mainContent_ddl_originStation1_CTNR a[text*='BLR']"));
        wait.until(ExpectedConditions.visibilityOf(origin));
        origin.click();

        WebElement destinationInput = driver.findElement(By.id("ctl00_mainContent_ddl_destinationStation1_CTXT"));
        destinationInput.click();
        WebElement destination = driver.findElement(By.cssSelector("#ctl00_mainContent_ddl_destinationStation1_CTNR a[text*='MAA']"));
        wait.until(ExpectedConditions.visibilityOf(destination));
        destination.click();

        System.out.println(originInput.getAttribute("selectedtext"));
        System.out.println(destinationInput.getAttribute("selectedtext"));

        driver.findElement(By.cssSelector(".ui-datepicker-today")).click();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
