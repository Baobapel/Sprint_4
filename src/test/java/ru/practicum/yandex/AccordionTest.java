package ru.practicum.yandex;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class AccordionTest {
    private WebDriver webDriver;

    private final int accordionNumber;
    private final String expectedText;


    public AccordionTest(int accordionNumber, String expectedText) {
        this.accordionNumber = accordionNumber;
        this.expectedText = expectedText;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {1, "Сколько это стоит? И как оплатить?"}, // Аккордеон №1
                {8, "Да, обязательно. Всем самокатов! И Москве, и Московской области."} // Аккордеон №8
        });
    }

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @Test
    public void DisplaysTextBelowTheAccordion() {
        QuestionsPage questionsPage = new QuestionsPage(webDriver);

        // Открываем страницу
        questionsPage.open();

        // Выбираем аккордеон в зависимости от параметра
        if (accordionNumber == 1) {
            questionsPage.clickOnAccordion1();
            assertTrue("Текст под аккордеоном №1 не отображается", questionsPage.isAccordion1TextDisplayed());
        } else if (accordionNumber == 8) {
            questionsPage.clickOnAccordion8();
            assertTrue("Текст под аккордеоном №8 не отображается", questionsPage.isAccordion8TextDisplayed());
        }
    }


    @After
    public void tearDown() {

        webDriver.quit();
    }
}
