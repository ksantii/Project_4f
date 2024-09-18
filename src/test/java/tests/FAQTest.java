package tests;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.HomePage;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

// Класс FAQTest предназначен для тестирования раздела FAQ на главной странице
@RunWith(Parameterized.class)
public class FAQTest {

    private ChromeOptions options;
    private WebDriver driver;
    private HomePage homePage;

    // Параметры для теста: вопрос и ожидаемый ответ
    private final String question;
    private final String expectedAnswer;

    // Конструктор класса, принимающий параметры вопроса и ответа
    public FAQTest(String question, String expectedAnswer) {
        this.question = question;
        this.expectedAnswer = expectedAnswer;
    }

    // Метод для предоставления данных для параметризованного теста
    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                {"Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {"Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {"Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {"Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {"Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {"Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {"Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {"Я жизу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }

    // Метод, выполняемый перед каждым тестом, для настройки браузера и инициализации объектов страницы
    @Before
    public void setup() {
        options = new ChromeOptions().addArguments("--disable-cookies");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(HomePage.HOME_PAGE_URL);
        homePage = new HomePage(driver);
        // Установка неявного ожидания в 5 секунд
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    // Тестовый метод для проверки соответствия ответа вопросу
    @Test
    public void validateFaqAnswer() {
        WebElement questionElement = homePage.getFaqQuestion(question);
        // Прокрутка к вопросу
        homePage.scrollToElement(questionElement);
        // Клик по вопросу для раскрытия ответа
        questionElement.click();
        // Задержка, чтобы убедиться, что ответ успел появиться
        try {
            Thread.sleep(1000); // Задержка в 1 секунду
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Получение раскрытого ответа
        WebElement answerElement = homePage.getExpandedFaqAnswer();
        // Проверка соответствия текста ответа ожидаемому
        assertEquals("Ответ не сходится", expectedAnswer, answerElement.getText());
    }

    // Метод, выполняемый после каждого теста, для закрытия браузера
    @After
    public void teardown() {
        driver.quit();
    }
}