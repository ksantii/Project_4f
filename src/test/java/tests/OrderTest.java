package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.HomePage;
import pages.UserOrderPage;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

// Класс OrderTest предназначен для тестирования позитивных сценариев заказа
@RunWith(Parameterized.class)
public class OrderTest {

    private ChromeOptions options;
    private WebDriver driver;
    private HomePage homePage;
    private UserOrderPage orderPage;

    // Параметры для теста
    private String name;
    private String surname;
    private String address;
    private String metroStation;
    private String phoneNumber;
    private int deliveryDays;
    private int rentalPeriodIndex;

    // Конструктор класса, принимающий параметры для заполнения формы заказа
    public OrderTest(
            String name,
            String surname,
            String address,
            String metroStation,
            String phoneNumber,
            int deliveryDays,
            int rentalPeriodIndex
    ) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroStation = metroStation;
        this.phoneNumber = phoneNumber;
        this.deliveryDays = deliveryDays;
        this.rentalPeriodIndex = rentalPeriodIndex;
    }

    // Метод для предоставления данных для параметризованного теста
    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {"Игорь", "Андреев", "Самолетная", "Сокольники", "1111111111111", 2, 4},
                {"Антон", "Мирд", "Терешкова", "Лубянка", "+222222222222", 1, 3},
        };
    }

    // Метод, выполняемый перед каждым тестом, для настройки браузера и инициализации объектов страниц
    @Before
    public void setup() {
        options = new ChromeOptions().addArguments("--disable-cookies");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(HomePage.HOME_PAGE_URL);
        homePage = new HomePage(driver);
        orderPage = new UserOrderPage(driver);
        driver.manage().timeouts().implicitlyWait(deliveryDays, TimeUnit.SECONDS);
    }
    // Метод для добавления задержки
    private void delay(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    // Тестовый метод для выполнения заказа через верхнюю кнопку заказа на главной странице
    @Test
    public void orderViaTopButton() {
        homePage.clickTopOrder();
        delay(1000); // Задержка в 1 секунду
        orderPage.enterName(name);
        orderPage.enterSurname(surname);
        orderPage.enterAddress(address);
        orderPage.chooseMetroStation(metroStation);
        orderPage.enterPhoneNumber(phoneNumber);
        orderPage.clickNext();
        delay(1000); // Задержка в 1 секунду
        orderPage.selectDeliveryDate();
        orderPage.selectRentPeriod(rentalPeriodIndex);
        orderPage.clickOrder();
        orderPage.clickConfirmYes();
        // Проверка, что заказ подтвержден
        assertTrue(orderPage.getOrderConfirmedHeader().isDisplayed());
    }

    // Тестовый метод для выполнения заказа через нижнюю кнопку заказа на главной странице
    @Test
    public void orderViaBottomButton() {
        homePage.clickBottomOrder();
        delay(1000); // Задержка в 1 секунду
        orderPage.enterName(name);
        orderPage.enterSurname(surname);
        orderPage.enterAddress(address);
        orderPage.chooseMetroStation(metroStation);
        orderPage.enterPhoneNumber(phoneNumber);
        orderPage.clickNext();
        delay(1000); // Задержка в 1 секунду
        orderPage.selectDeliveryDate();
        orderPage.selectRentPeriod(rentalPeriodIndex);
        orderPage.clickOrder();
        orderPage.clickConfirmYes();
        // Проверка, что заказ подтвержден
        assertTrue(orderPage.getOrderConfirmedHeader().isDisplayed());
    }

    // Метод, выполняемый после каждого теста, для закрытия браузера
    @After
    public void teardown() {
        driver.quit();
    }
}