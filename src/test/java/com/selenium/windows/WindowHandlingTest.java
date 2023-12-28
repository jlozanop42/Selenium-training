package com.selenium.windows;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

public class WindowHandlingTest {

    WebDriver driver;

    WebDriverWait wait;

    @BeforeClass
    public void setupDriver() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @BeforeMethod
    public void goToMainPage() {
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/loginpagePractise/");
    }

    @Test
    public void actionsTest() {
        driver.findElement(By.cssSelector("a[class='blinkingText']")).click();
        Set<String> windows = driver.getWindowHandles();
        Iterator<String> iterator = windows.iterator();
        String parentId = iterator.next();
        String childId = iterator.next();
        driver.switchTo().window(childId);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".inner-box h1")));
        String mail = driver.findElement(By.cssSelector("p.im-para.red strong a")).
                getAttribute("href").split(":")[1];
        System.out.println(mail);
        driver.switchTo().window(parentId);
        driver.findElement(By.xpath("//input[contains(@id, 'username')]")).
                sendKeys(mail);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();}
}
