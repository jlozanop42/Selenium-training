package com.selenium.windows;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

public class WindowsAssignment {
    WebDriver driver;

    @BeforeTest
    public void setup() {
        driver = new ChromeDriver();
    }

    @BeforeMethod
    public void beforeTest() {
        driver.get("https://the-internet.herokuapp.com/windows");
    }

    @Test
    public void windowHandling() {
        driver.findElement(By.cssSelector(".example a")).click();

        Set<String> windows = driver.getWindowHandles();
        Iterator<String> iterator = windows.iterator();
        String mainContext = iterator.next();
        String newWindow = iterator.next();
        driver.switchTo().window(newWindow);
        System.out.println(driver.findElement(By.xpath("//div//h3")).getText());

        driver.switchTo().window(mainContext);
        System.out.println(driver.findElement(By.xpath("//div[@class='example']//h3")).getText());
    }

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/windows");
        driver.findElement(By.cssSelector(".example a")).click();

        Set<String> windows = driver.getWindowHandles();
        Iterator<String> iterator = windows.iterator();
        String mainContext = iterator.next();
        String newWindow = iterator.next();
        driver.switchTo().window(newWindow);
        System.out.println(driver.findElement(By.xpath("//div//h3")).getText());

        driver.switchTo().window(mainContext);
        System.out.println(driver.findElement(By.xpath("//div[@class='example']//h3")).getText());
        driver.quit();
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }


}
