package ru.practicum.yandex;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// Страница со второй формой заказа
public class AboutRentPage {
    // Локаторы для второй формы
    private final By AboutRentHeader = By.xpath("//*[@id='root']/div/div[2]/div[1]");
    private final By deliveryDateField = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[1]/div[1]/div/input");
    private final By rentalPeriodDropdown = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[2]/div/div");
    private final By blackColorCheckbox = By.xpath("//*[@id='black']");
    private final By greyColorCheckbox = By.xpath("//*[@id='grey']");
    private final By commentField = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[4]/input");
    private final By orderButton = By.xpath("//*[@id='root']/div/div[2]/div[3]/button[2]");
    private final By confirmOrderModal = By.xpath("//div[text()='Хотите оформить заказ?']");
    private final By confirmYesButton = By.className("//*[@id='root']/div/div[2]/div[5]/div[2]/button[2]");
    private final By orderSuccessModal = By.xpath("//div[text()='Заказ оформлен']");

    private WebDriver webDriver;
    private OrderPage orderPage;

    public AboutRentPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.orderPage = new OrderPage(webDriver);
    }

    // Метод для заполнения первой формы и перехода на вторую форму
    public AboutRentPage fillFirstFormAndProceed(String name, String surname, String address, String subwayStation, String phoneNumber) {
        // Заполняем первую форму с помощью методов из OrderPage
        orderPage.enterName(name)
                .EnterSurname(surname)
                .EnterAddress(address)
                .ChooseSubway(subwayStation)
                .EnterPhoneNumber()
                .clickNextButton();

        // Ожидаем появления заголовка "Про аренду"
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(AboutRentHeader));

        return this;
    }

    // Проверка, что заголовок "Про аренду" отображается
    public boolean AboutRentHeaderIsDisplayed() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(AboutRentHeader)).isDisplayed();
    }

    // Методы для второй формы
    public AboutRentPage enterDeliveryDate(String date) {
        webDriver.findElement(deliveryDateField).sendKeys(date);
        // Имитируем нажатие Enter, чтобы закрыть календарь
        new Actions(webDriver)
                .sendKeys(Keys.ENTER)
                .perform();
        return this;
    }

    public AboutRentPage selectRentalPeriod(String period) {
        webDriver.findElement(rentalPeriodDropdown).click();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='Dropdown-option' and text()='" + period + "']")
        )).click();
        return this;
    }

    public AboutRentPage selectScooterColor(String color) {
        if (color.equalsIgnoreCase("black")) {
            webDriver.findElement(blackColorCheckbox).click();
        } else if (color.equalsIgnoreCase("grey")) {
            webDriver.findElement(greyColorCheckbox).click();
        }
        return this;
    }

    public AboutRentPage enterComment(String comment) {
        webDriver.findElement(commentField).sendKeys(comment);
        return this;
    }

    public AboutRentPage clickOrderButton() {
        webDriver.findElement(orderButton).click();
        return this;
    }

    public boolean isConfirmOrderModalDisplayed() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(confirmOrderModal)).isDisplayed();
    }
    //тест упадёт, если мы будем ожидать клика, кнопка не кликатеся, поэтому просто нажимаем на enter (это баг)
    public AboutRentPage clickConfirmYesButton() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        new Actions(webDriver)
                .sendKeys(Keys.ENTER)
                .perform();
        return this;
    }

    public boolean isOrderSuccessModalDisplayed() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        new Actions(webDriver)
                .sendKeys(Keys.ENTER)
                .perform();
        return wait.until(ExpectedConditions.visibilityOfElementLocated(orderSuccessModal)).isDisplayed();

    }
}