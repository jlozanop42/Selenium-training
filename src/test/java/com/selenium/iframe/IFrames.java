package com.selenium.iframe;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;

public class IFrames {
    WebDriver driver;

    WebDriverWait wait;

    Actions actions;

    @BeforeClass
    public void setupDriver() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
    }

    @BeforeMethod
    public void goToMainPage() {
        driver.manage().window().maximize();
        driver.get("https://jqueryui.com/droppable");
    }

    @Test
    public void withClickAndHoldMethod() throws InterruptedException {
       driver.switchTo().
               frame(driver.findElement(By.cssSelector("iframe.demo-frame")));
       actions.moveToElement(driver.
               findElement(By.xpath("//div[@id='draggable']"))).
               clickAndHold().
               moveToElement(driver.
                       findElement(By.xpath("//div[@id='droppable']"))).build().perform();
       driver.switchTo().defaultContent();
       driver.findElement(By.xpath("//div[@class='demo-list']//a[.='Accept']")).click();
       Thread.sleep(5000);
    }

    @Test
    public void withDragAndDropMethod() throws InterruptedException {
        driver.switchTo().
                frame(driver.findElement(By.cssSelector("#content iframe")));
        actions.dragAndDrop(driver.findElement(By.xpath("//div[@id='draggable']")),
                        driver.findElement(By.xpath("//div[@id='droppable']"))).
                build().
                perform();
        Thread.sleep(5000);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();}
}
