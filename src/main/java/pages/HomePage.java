package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// Класс HomePage представляет главную страницу сайта
public class HomePage {

    private WebDriver driver;
    public static final String HOME_PAGE_URL = "https://qa-scooter.praktikum-services.ru";

    // Локаторы для элементов на главной странице
    private static By lowerfaq = By.xpath(".//div[@class='accordion__panel' and not(@hidden)]");
    private static By upbottom = By.xpath(".//button[@class='Button_Button__ra12g']");
    private static By downbottom = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button");

    // Конструктор класса, инициализирующий WebDriver
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Метод для получения элемента вопроса FAQ по тексту
    public WebElement getFaqQuestion(String questionText) {
        By questionLocator = By.xpath(String.format(".//div[@aria-expanded and text()='%s']", questionText));
        return new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(questionLocator)); // Используем явное ожидание
    }

    // Метод для получения раскрытого ответа FAQ
    public WebElement getExpandedFaqAnswer() {
        return new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(lowerfaq)); // Используем явное ожидание
    }

    public WebElement getTopOrderButton() {
        return driver.findElement(upbottom);
    }

    public WebElement getBottomOrderButton() {
        return driver.findElement(downbottom);
    }

    // Метод для нажатия на верхнюю кнопку заказа
    public void clickTopOrder() {
        getTopOrderButton().click();
    }

    // Метод для нажатия на нижнюю кнопку заказа
    public void clickBottomOrder() {
        scrollToElement(getBottomOrderButton());
        getBottomOrderButton().click();
    }

    // Метод для прокрутки к элементу
    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }
}