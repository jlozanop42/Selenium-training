package com.selenium.checkbox;

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
import java.util.List;

public class CheckboxTest {
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
    public void selectCheckboxTest() {
        WebElement checkbox = driver.findElement(By.cssSelector("input[id*='SeniorCitizenDiscount']"));
        Assert.assertFalse(checkbox.isSelected());
        checkbox.click();
        Assert.assertTrue(checkbox.isSelected());
    }

    @Test
    public void selectAllCheckbox() {
        WebElement seniorCheckbox = driver.findElement(By.cssSelector("input[id*='SeniorCitizenDiscount']"));
        wait.until(ExpectedConditions.elementToBeClickable(seniorCheckbox));
        List<WebElement> checkboxes = driver.findElements(By.cssSelector("#discount-checkbox input"));
        for(WebElement checkbox: checkboxes) {
            checkbox.click();
        }
        Assert.assertEquals(checkboxes.size(), 5);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
