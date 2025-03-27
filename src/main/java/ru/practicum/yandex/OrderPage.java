package ru.practicum.yandex;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static ru.practicum.yandex.MainPage.orderButtonAtTheBottom;
import static ru.practicum.yandex.MainPage.orderButtonAtTheTop;


public class OrderPage {
    // поле с именем
    private final By nameInputField = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[1]/input");
    // поле с фамилией
    private final By surnameInputField = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[2]/input");
    // поле с адресом
    private final By addressField = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[3]/input");
    // выпадающий список с метро
    private final By subwayField = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[4]/div/div/input");
    // поле с номером телефона
    private final By phoneNumberField = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[5]/input");
    // кнопка далее
    private final By nextButton = By.xpath("//*[@id='root']/div/div[2]/div[3]/button");
    // заголовок "Для кого самокат" на странице с формой заказа
    private final By forWhomOrderHeader = By.xpath("//*[@id='root']/div/div[2]/div[1]");

    private WebDriver webDriver;

    public OrderPage(WebDriver webDriver) {
        this.webDriver = webDriver;

    }

    // нажимаем на верхнюю кнопку заказать и убеждаемся что появился хедер "Для кого самокат", а значит и открылась страница с формой заказа.
    public OrderPage clickOrderButtonTop() {
        webDriver.findElement(orderButtonAtTheTop).click();

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(forWhomOrderHeader));

        return this;
    }

    // нажимаем на нижнюю кнопку заказать и убеждаемся что появился хедер "Для кого самокат", а значит и открылась страница с формой заказа.
    public OrderPage clickOrderButtonBottom() {
        webDriver.findElement(orderButtonAtTheBottom).click();

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(forWhomOrderHeader));

        return this;
    }


    // вводим Имя
    public OrderPage enterName(String name) {
        webDriver.findElement(nameInputField).sendKeys(name);
        return this;
    }

    // вводим Фамилию
    public OrderPage EnterSurname(String surname) {
        webDriver.findElement(surnameInputField).sendKeys(surname);
        return this;
    }

    // вводим адрес
    public OrderPage EnterAddress(String address) {
        webDriver.findElement(addressField).sendKeys(address);
        return this;
    }

    public OrderPage ChooseSubway(String stationName) {

        WebElement subwayInput = webDriver.findElement(subwayField);
        subwayInput.click();

        subwayInput.sendKeys(stationName);

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        WebElement subwayOptionElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@class='select-search__select']//div[text()='" + stationName + "']")
                )
        );
        subwayOptionElement.click();

        return this;
    }

    // вводим номер телефона
    public OrderPage EnterPhoneNumber() {
        webDriver.findElement(phoneNumberField).sendKeys("81234567890");
        return this;
    }

    // жмём кнопку далее
    public OrderPage clickNextButton() {
        webDriver.findElement(nextButton).click();
        return this;
    }


}

