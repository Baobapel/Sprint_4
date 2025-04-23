package ru.practicum.yandex.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.practicum.yandex.AboutRentPage;
import ru.practicum.yandex.MainPage;
import ru.practicum.yandex.OrderPage;
import ru.practicum.yandex.QuestionsPage;

import java.time.Duration;

public abstract class BaseTest {
    protected WebDriver webDriver;
    protected QuestionsPage questionsPage;
    protected MainPage mainPage;
    protected OrderPage orderPage;
    protected AboutRentPage aboutRentPage;

    @Before
    public void setUp() {
        setupDriver();
        questionsPage = new QuestionsPage(webDriver);
        mainPage = new MainPage(webDriver);
        orderPage = new OrderPage(webDriver);
        aboutRentPage = new AboutRentPage(webDriver);
        mainPage.open();
    }

    protected void setupDriver() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        webDriver = new ChromeDriver(options);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }



    @After
    public void tearDown() {

            webDriver.quit();

    }
}
