package tests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.HomePage;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;
    protected HomePage homePage;

    @Before
    public void setup() {
        ChromeOptions options = new ChromeOptions().addArguments("--disable-cookies");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(HomePage.HOME_PAGE_URL);
        homePage = new HomePage(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3)); // Установка неявного ожидания
    }

    @After
    public void teardown() {
        driver.quit();
    }
}