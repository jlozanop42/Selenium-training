package com.selenium.uielements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class DropdownTest {

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
    public void selectDropdown() {
        WebElement currencyDropdown = driver.findElement(By.id("ctl00_mainContent_DropDownListCurrency"));
        Select selectCurrency = new Select(currencyDropdown);

        selectCurrency.selectByIndex(3);
        System.out.println(selectCurrency.getFirstSelectedOption().getText());

        selectCurrency.selectByVisibleText("AED");
        System.out.println(selectCurrency.getFirstSelectedOption().getText());

        selectCurrency.selectByValue("INR");
        System.out.println(selectCurrency.getFirstSelectedOption().getText());
    }

    @Test
    public void selectDropdownByClicking() {
        WebElement addPassengersDropdown = driver.findElement(By.id("divpaxinfo"));
        System.out.println("Before adding passengers: " + addPassengersDropdown.getText());
        addPassengersDropdown.click();

        WebElement addAdult = driver.findElement(By.id("hrefIncAdt"));
        wait.until(ExpectedConditions.visibilityOf(addAdult));
        for(int i = 0; i < 5; i ++) {
            addAdult.click();
        }
        driver.findElement(By.id("btnclosepaxoption")).click();
        System.out.println("After adding adults: " + addPassengersDropdown.getText());
    }


    @Test
    public void selectDynamicDropdown() {
        WebElement originInput =  driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT"));
        originInput.click();

        WebElement origin = driver.findElement(By.cssSelector("#ctl00_mainContent_ddl_originStation1_CTNR a[text*='BLR']"));
        wait.until(ExpectedConditions.visibilityOf(origin));
        origin.click();

        WebElement destinationInput = driver.findElement(By.id("ctl00_mainContent_ddl_destinationStation1_CTXT"));
        destinationInput.click();
        WebElement destination = driver.findElement(By.cssSelector("#ctl00_mainContent_ddl_destinationStation1_CTNR a[text*='MAA']"));
        wait.until(ExpectedConditions.visibilityOf(destination));
        destination.click();

        System.out.println(originInput.getAttribute("selectedtext"));
        System.out.println(destinationInput.getAttribute("selectedtext"));
    }
    @Test
    public void autoSuggestiveDropdown() throws InterruptedException {
        driver.findElement(By.id("autosuggest")).sendKeys("ind");
        Thread.sleep(2000);
        List<WebElement> countryOptions = driver.findElements(By.cssSelector("li.ui-menu-item a"));
        Thread.sleep(3000);
        for(WebElement option: countryOptions) {
            if(option.getText().equalsIgnoreCase("India")) {
                option.click();
                break;
            }
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}


