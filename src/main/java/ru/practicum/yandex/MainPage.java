package ru.practicum.yandex;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {

    protected static final String url = "https://qa-scooter.praktikum-services.ru/";


    protected final By orderStatusButton = By.xpath(".//button[@class='Header_Link__1TAG7']");
    private final By orderInputField = By.xpath(".//input[contains(@class, 'Header_Input__xIoUq')]");
    private final By goButton = By.xpath(".//button[text()='Go!']");
    private final By notFoundMessage = By.xpath(".//img[@alt='Not found']");

    // кнопка "Заказать", которая находится вверху страницы
    protected static final By orderButtonAtTheTop = By.xpath("//*[@id='root']/div/div/div[1]/div[2]/button[1]");
    // кнопка "Заказать", которая находится внизу страницы
    protected static final By orderButtonAtTheBottom = By.xpath("//*[@id='root']/div/div/div[4]/div[2]/div[5]/button");


    private final WebDriver webDriver;

    public MainPage(WebDriver webDriver) {
        this.webDriver = webDriver;

    }

    public MainPage open() {
        webDriver.get(url);
        return this;
    }

    public MainPage clickOrderStatusButton() {
        webDriver.findElement(orderStatusButton).click();
        return this;
    }

    public MainPage enterOrderInput(String text) {
        webDriver.findElement(orderInputField).sendKeys(text);
        return this;
    }

    public MainPage clickGoButton() {
        webDriver.findElement(goButton).click();
        return this;
    }

    public boolean checkNotFound() {
        return !webDriver.findElements(notFoundMessage).isEmpty();
    }
}

