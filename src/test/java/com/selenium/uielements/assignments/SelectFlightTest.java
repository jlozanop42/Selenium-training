package com.selenium.uielements.assignments;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
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

        WebElement origin = driver.findElement(By.cssSelector("#ctl00_mainContent_ddl_originStation1_CTNR a[text*='DEL']"));
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

        WebElement addPassengersDropdown = driver.findElement(By.id("divpaxinfo"));
        System.out.println("Before adding passengers: " + addPassengersDropdown.getText());
        addPassengersDropdown.click();

        WebElement addAdult = driver.findElement(By.id("hrefIncAdt"));
        wait.until(ExpectedConditions.visibilityOf(addAdult));
        for(int i = 0; i < 2; i ++) {
            addAdult.click();
        }
        driver.findElement(By.id("btnclosepaxoption")).click();
        Assert.assertEquals(addPassengersDropdown.getText(), "3 Adult");
        driver.findElement(By.cssSelector("input[id*='SeniorCitizenDiscount']")).click();
        driver.findElement(By.xpath("//div[@id='Div6']//input[contains(@id, 'FindFlights')]")).click();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
