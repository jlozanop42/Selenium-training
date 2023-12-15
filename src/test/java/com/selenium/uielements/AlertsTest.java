package com.selenium.uielements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;

public class AlertsTest {
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
    public void simpleAlertTest() {
        String name = "Emiliano";
        driver.findElement(By.id("name")).sendKeys(name);
        driver.findElement(By.xpath("//input[@value='Alert']")).click();
        String alertText = driver.switchTo().alert().getText();
        System.out.println(alertText);
        Assert.assertTrue(alertText.contains(name));
        driver.switchTo().alert().accept();
    }

    @Test
    public void complexAlertTest() {
        String name = "Emiliano";
        driver.findElement(By.id("name")).sendKeys(name);
        driver.findElement(By.xpath("//input[@value='Confirm']")).click();
        String alertText = driver.switchTo().alert().getText();
        System.out.println(alertText);
        Assert.assertTrue(alertText.contains(name));
        driver.switchTo().alert().dismiss();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
