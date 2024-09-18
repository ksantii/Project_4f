package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

// Класс UserOrderPage представляет страницу оформления заказа
public class UserOrderPage {

    private WebDriver driver;


    // Локаторы для элементов формы заказа
    private static By nameField = By.xpath(".//input[@placeholder='* Имя']");
    private static By surnameField = By.xpath(".//input[@placeholder='* Фамилия']");
    private static By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private static By metroField = By.xpath(".//input[@placeholder='* Станция метро']/parent::*/parent::*");
    private static By metroList = By.xpath(".//div[@class='Order_Text__2broi']/parent::*/parent::*");
    private static By phoneField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private static By nextButton = By.xpath(".//button[text()='Далее']");
    public static By calendarField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private static By rentDropdown = By.xpath(".//div[@class='Dropdown-root']");
    private static By rentOptions = By.xpath(".//div[@class='Dropdown-menu']/div");
    private static By orderButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']");
    private static By confirmYesButton = By.xpath(".//button[text()='Да']");
    private static By orderConfirmedHeader = By.xpath(".//div[text()='Заказ оформлен']");

    // Конструктор класса, инициализирующий WebDriver
    public UserOrderPage(WebDriver driver) {
        this.driver = driver;
    }

    // Методы для получения элементов формы
    public WebElement getNameField() {
        return driver.findElement(nameField);
    }

    public WebElement getSurnameField() {
        return driver.findElement(surnameField);
    }

    public WebElement getAddressField() {
        return driver.findElement(addressField);
    }

    public WebElement getMetroField() {
        return driver.findElement(metroField);
    }

    // Метод для получения элемента метро по названию станции
    public WebElement getMetroStation(String stationName) {
        return driver
                .findElements(metroList)
                .stream()
                .filter(element -> element.getText().equalsIgnoreCase(stationName))
                .findFirst().orElse(null);
    }

    public WebElement getPhoneField() {
        return driver.findElement(phoneField);
    }

    public WebElement getNextButton() {
        return driver.findElement(nextButton);
    }

    public WebElement getCalendarField() {
        return driver.findElement(calendarField);
    }

    // Метод для установки даты доставки на 15 число следующего месяца
    public void setDeliveryDate() {
        // Получаем текущую дату и время
        ZonedDateTime now = ZonedDateTime.now(ZoneId.systemDefault());

        // Переходим к следующему месяцу
        ZonedDateTime nextMonth = now.plusMonths(1).withDayOfMonth(1);

        // Устанавливаем дату на 15 число следующего месяца
        ZonedDateTime targetDate = nextMonth.withDayOfMonth(15);
        int year = targetDate.getYear();
        String month = targetDate.getMonth().getDisplayName(TextStyle.FULL_STANDALONE, new Locale("ru"));
        int day = targetDate.getDayOfMonth();

        WebElement currentMonth = driver.findElement(By.xpath(".//div[@class='react-datepicker__current-month']"));
        WebElement nextMonthBtn = driver.findElement(By.xpath(".//button[@aria-label='Next Month']"));

        // Переход к нужному месяцу
        while (!currentMonth.getText().equals(String.format("%s %d", month, year))) {
            nextMonthBtn.click();
        }

        // Выбор нужного дня
        driver.findElement(By.xpath(String.format(".//div[@class='react-datepicker__month']/div/div[not(contains(@class, '--outside-month')) and text()='%d']", day)))
                .click();
    }
    public WebElement getRentDropdown() {
        return driver.findElement(rentDropdown);
    }

    // Метод для выбора срока аренды по индексу
    public WebElement getRentOption(int index) {
        return driver.findElements(rentOptions).get(index);
    }

    public WebElement getOrderButton() {
        return driver.findElement(orderButton);
    }

    public WebElement getConfirmYesButton() {
        return driver.findElement(confirmYesButton);
    }

    public WebElement getOrderConfirmedHeader() {
        return driver.findElement(orderConfirmedHeader);
    }

    // Метод для прокрутки к элементу
    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    // Методы для заполнения полей формы
    public void enterName(String name) {
        getNameField().sendKeys(name);
    }

    public void enterSurname(String surname) {
        getSurnameField().sendKeys(surname);
    }

    public void enterAddress(String address) {
        getAddressField().sendKeys(address);
    }

    // Метод для выбора станции метро
    public void chooseMetroStation(String station) {
        getMetroField().click();
        WebElement stationElement = getMetroStation(station);
        scrollToElement(stationElement);
        if (stationElement != null) {
            stationElement.click();
        }
    }

    public void enterPhoneNumber(String phone) {
        getPhoneField().sendKeys(phone);
    }

    // Метод для выбора даты доставки
    public void selectDeliveryDate() {
        getCalendarField().click();
        setDeliveryDate();
    }

    // Метод для выбора срока аренды
    public void selectRentPeriod(int index) {
        getRentDropdown().click();
        WebElement rentOption = getRentOption(index);
        scrollToElement(rentOption);
        rentOption.click();
    }

    // Методы для нажатия кнопок
    public void clickNext() {
        getNextButton().click();
    }

    public void clickOrder() {
        getOrderButton().click();
    }

    public void clickConfirmYes() {
        getConfirmYesButton().click();
    }
}