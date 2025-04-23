package ru.practicum.yandex;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

import java.time.Duration;

public class QuestionsPage {
    // Ссылка на страницу
    private static String url = "https://qa-scooter.praktikum-services.ru/";
    // Метод возвращает xpath соответствующего локатора
    private By getAccordionLocator(int number) {
        return By.xpath(String.format("//*[@id='accordion__heading-%d']", number - 1));
    }
    // Метод возвращает xpath соответствующего текста под локатором
    private By getAccordionTextLocator(int number) {
        return By.xpath(String.format("//*[@id='accordion__panel-%d']/p", number - 1));
    }

    public String getAccordionText(int number) {
        By textLocator = getAccordionTextLocator(number);
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        WebElement accordionText = wait.until(ExpectedConditions.visibilityOfElementLocated(textLocator));
        return accordionText.getText();
    }

    public QuestionsPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    private final WebDriver webDriver;

    public QuestionsPage open() {
        webDriver.get(url);
        return this;
    }

    // Метод ждёт, прокручивает до аккордеонов, кликает на аккордеон
    private void clickAccordion(By accordionLocator) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        WebElement accordion = wait.until(ExpectedConditions.elementToBeClickable(accordionLocator));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", accordion);
        accordion.click();
    }

    // метод, который проверяет что появляется соответствующий текст под аккордеоном.
    private boolean isTextDisplayed(By textLocator) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        WebElement accordionText = wait.until(ExpectedConditions.visibilityOfElementLocated(textLocator));
        return accordionText.isDisplayed();
    }

    public void clickAccordionByNumber(int number) {
        By accordionLocator = getAccordionLocator(number);
        clickAccordion(accordionLocator);
    }

    public boolean isAccordionTextDisplayed(int number) {
        By textLocator = getAccordionTextLocator(number);
        return isTextDisplayed(textLocator);
    }

}


