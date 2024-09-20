package tests;

import pages.UserOrderPage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.assertTrue;

import java.time.Duration;


// Класс OrderTest предназначен для тестирования позитивных сценариев заказа
@RunWith(Parameterized.class)
public class OrderTest extends BaseTest {

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
        super.setup();
        orderPage = new UserOrderPage(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    // Тестовый метод для выполнения заказа через верхнюю кнопку заказа на главной странице
    @Test
    public void orderViaTopButton() {
        homePage.clickTopOrder();
        orderPage.enterName(name);
        orderPage.enterSurname(surname);
        orderPage.enterAddress(address);
        orderPage.chooseMetroStation(metroStation);
        orderPage.enterPhoneNumber(phoneNumber);
        orderPage.clickNext();
        orderPage.selectDeliveryDate();
        orderPage.selectRentPeriod(rentalPeriodIndex);
        orderPage.clickOrder();
        orderPage.clickConfirmYes();
        assertTrue(new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOf(orderPage.getOrderConfirmedHeader())).isDisplayed()); // Используем явное ожидание
    }

    // Тестовый метод для выполнения заказа через нижнюю кнопку заказа на главной странице
    @Test
    public void orderViaBottomButton() {
        homePage.clickBottomOrder();
        orderPage.enterName(name);
        orderPage.enterSurname(surname);
        orderPage.enterAddress(address);
        orderPage.chooseMetroStation(metroStation);
        orderPage.enterPhoneNumber(phoneNumber);
        orderPage.clickNext();
        orderPage.selectDeliveryDate();
        orderPage.selectRentPeriod(rentalPeriodIndex);
        orderPage.clickOrder();
        orderPage.clickConfirmYes();
        assertTrue(new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOf(orderPage.getOrderConfirmedHeader())).isDisplayed()); // Используем явное ожидание
    }
}