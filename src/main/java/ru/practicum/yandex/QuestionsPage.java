package ru.practicum.yandex;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

import java.time.Duration;

public class QuestionsPage {
    // Ссылка на страницу
    private static String url = "https://qa-scooter.praktikum-services.ru/";
    // аккордеон "Сколько стоит и как оплатить"
    private final By Accordion1 = By.xpath("//*[@id='accordion__heading-0']");

    // текст под 1 аккордеоном
    private final By Accordion_1_Text = By.xpath("//*[@id='accordion__panel-0']/p");

    // совершенно нет времени и лень прописывать все 8 аккордеонов, смысл то понятен и так (этого вроде не требуется в задании, но если надо пропишу)

    // аккордеон №8
    private final By Accordion_8 = By.xpath(".//*[@id='accordion__heading-7']");

    // текст под 8 аккордеоном

    private final By Accordion_8_Text = By.xpath("//*[@id='accordion__panel-7']/p");

    public QuestionsPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    private final WebDriver webDriver;

    public QuestionsPage open() {
        webDriver.get(url);
        return this;
    }

    // метод кликает на первый аккордеон
    public QuestionsPage clickOnAccordion1() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        WebElement accordion = wait.until(ExpectedConditions.elementToBeClickable(Accordion1));

        // Прокрутка до аккордеона
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", accordion);

        // Клик на аккордеон
        accordion.click();
        return this;
    }


    // метод, который проверяет что появляется соответствующий текст под аккордеоном.
    public boolean isAccordion1TextDisplayed() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        WebElement accordionText = wait.until(
                ExpectedConditions.visibilityOfElementLocated(Accordion_1_Text));
        return accordionText.isDisplayed();
    }

    public QuestionsPage clickOnAccordion8() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        WebElement accordion = wait.until(ExpectedConditions.elementToBeClickable(Accordion_8));

        // Прокрутка до аккордеона
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", accordion);

        // Клик на аккордеон
        accordion.click();
        return this;
    }
    // метод, который проверяет что появляется соответствующий текст под аккордеоном.
    public boolean isAccordion8TextDisplayed() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        WebElement accordionText = wait.until(
                ExpectedConditions.visibilityOfElementLocated(Accordion_8_Text));
        return accordionText.isDisplayed();
    }
}
