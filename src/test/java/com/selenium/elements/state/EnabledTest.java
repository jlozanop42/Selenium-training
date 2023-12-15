package com.selenium.elements.state;

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

public class EnabledTest {
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
    public void isElementEnabledCheckingAttribute() {
        WebElement arrivalDateCalendar = driver.findElement(By.id("ctl00_mainContent_view_date2"));
        System.out.println(arrivalDateCalendar.isEnabled());
        WebElement calendarContainer = driver.findElement(By.id("Div1"));
        System.out.println(calendarContainer.getAttribute("style"));
        driver.findElement(By.id("ctl00_mainContent_rbtnl_Trip_1")).click();
        Assert.assertTrue(calendarContainer.getAttribute("style").contains("1"));
        System.out.println(arrivalDateCalendar.isEnabled());
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
