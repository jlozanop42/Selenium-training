package com.selenium.assignments;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class CompleteForm {
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
        driver.get("https://rahulshettyacademy.com/angularpractice/");
    }

    @Test
    public void completeFormTest() {
        driver.findElement(By.cssSelector("div.form-group input[name='name']")).
                sendKeys("Name");
        driver.findElement(By.name("email")).sendKeys("myemail@mail.com");
        driver.findElement(By.id("exampleInputPassword1")).sendKeys("qwerty");
        driver.findElement(By.id("exampleCheck1")).click();
        WebElement genderDropdown = driver.findElement(By.id("exampleFormControlSelect1"));
        Select genderSelect = new Select(genderDropdown);
        genderSelect.selectByVisibleText("Male");
        driver.findElement(By.xpath("//input[contains(@name,'inlineRadio')][@value='option1']")).click();
        driver.findElement(By.xpath("//input[@name='bday']")).sendKeys("03/24/1998");
        driver.findElement(By.cssSelector("input.btn-success")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class, 'alert-success')]")).
                getText().contains("has been submitted successfully"));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}
