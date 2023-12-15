package com.selenium.uielements.checkbox;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class CheckboxAssignment {
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
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
    }

    @Test
    public void selectFirstCheckbox() {
        WebElement firstCheckbox = driver.findElement(By.id("checkBoxOption1"));
        firstCheckbox.click();
        Assert.assertTrue(firstCheckbox.isSelected());
        firstCheckbox.click();
        Assert.assertFalse(firstCheckbox.isSelected());
    }

    @Test
    public void countCheckboxesAndSelectAll() {
        List<WebElement> checkboxes = driver.findElements(By.cssSelector("#checkbox-example input"));
        Assert.assertEquals(checkboxes.size(), 3);
        for(WebElement checkbox: checkboxes){
            checkbox.click();
            Assert.assertTrue(checkbox.isSelected());
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
