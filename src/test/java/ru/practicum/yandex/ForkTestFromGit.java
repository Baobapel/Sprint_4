package ru.practicum.yandex;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.Assert.assertTrue;
import static ru.practicum.yandex.utils.Utils.randomString;

public class ForkTestFromGit {

    private WebDriver webDriver;

    @Before
    public void setUp() {
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @Test
    public void notExistingOrderMessage() {
        MainPage mainPage = new MainPage(webDriver);

        mainPage.open()
                .clickOrderStatusButton()
                .enterOrderInput(randomString())
                .clickGoButton();

        assertTrue(mainPage.checkNotFound());
    }

    @After
    public void tearDown() {
        webDriver.quit();
    }
}

