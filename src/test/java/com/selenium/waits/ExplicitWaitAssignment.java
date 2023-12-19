package com.selenium.waits;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class ExplicitWaitAssignment {
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
        driver.get("https://rahulshettyacademy.com/loginpagePractise/");
    }

    @Test
    public void addToCartAndCheckout() {
        login("rahulshettyacademy", "learning");
        selectAllElementsAndCheckout();
        selectCountryAndPlaceOrder();
    }
    private void selectCountryAndPlaceOrder() {
        driver.findElement(By.id("country")).sendKeys("Colombia");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.lds-ellipsis")));
        driver.findElement(By.xpath("//input[@value='Purchase']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.alert-success")));
        String successMessage = driver.findElement(By.cssSelector("div.alert-success")).getText();
        System.out.println(successMessage);
        Assert.assertTrue(successMessage.contains("Success"));
    }

    private void selectAllElementsAndCheckout() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1.my-4")));
        Assert.assertEquals(driver.getCurrentUrl(), "https://rahulshettyacademy.com/angularpractice/shop");
        List<WebElement> addButtons = driver.findElements(By.cssSelector("button.btn-info"));
        System.out.println("There are " + addButtons.size() + " elements to add");
        for (WebElement button : addButtons) {
            button.click();
        }
        WebElement checkoutButton = driver.findElement(By.cssSelector("a.btn-primary"));
        int numberOfItemsInTheCart = Integer.parseInt(checkoutButton.getText().trim().split(" ")[2]);
        System.out.println("There are " + numberOfItemsInTheCart + "elements in the cart");
        Assert.assertEquals(numberOfItemsInTheCart, addButtons.size());
        checkoutButton.click();
        driver.findElement(By.cssSelector("button.btn-success")).click();
    }

    private void login(String username, String password) {
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.xpath("//input[@value='user']//following::span[@class='checkmark']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.modal-content")));
        driver.findElement(By.id("okayBtn")).click();
        WebElement userTypeDropdown = driver.findElement(By.cssSelector("select[data-style]"));
        Select userTypeSelect = new Select(userTypeDropdown);
        userTypeSelect.selectByValue("consult");
        driver.findElement(By.id("terms")).click();
        driver.findElement(By.name("signin")).click();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();}
}
