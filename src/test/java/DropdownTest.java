import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class DropdownTest extends BaseTest {

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

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement addAdult = driver.findElement(By.id("hrefIncAdt"));
        wait.until(ExpectedConditions.visibilityOf(addAdult));
        for(int i = 0; i < 5; i ++) {
            addAdult.click();
        }
        driver.findElement(By.id("btnclosepaxoption")).click();
        System.out.println("After adding adults: " + addPassengersDropdown.getText());
    }
}
