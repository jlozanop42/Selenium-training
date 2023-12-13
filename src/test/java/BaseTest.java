import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    WebDriver driver;

    @BeforeClass
    public void setupDriver() {
        driver = new ChromeDriver();
    }

    @BeforeMethod
    public void goToMainPage() {
        driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
    }
}
