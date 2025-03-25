package ru.practicum.yandex;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderTestForm1 {
    private WebDriver webDriver;

    private final String name;
    private final String surname;
    private final String address;
    private final String subwayStation;
    private final String phoneNumber;
    private final By orderButton;

    public OrderTestForm1(String name, String surname, String address, String subwayStation, String phoneNumber, By orderButton) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.subwayStation = subwayStation;
        this.phoneNumber = phoneNumber;
        this.orderButton = orderButton;
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                // Первый набор данных: верхняя кнопка "Заказать"
                {"Иван", "Иванов", "ул. Ленина, 10", "Сокольники", "81234567890", MainPage.orderButtonAtTheTop},
                // Второй набор данных: нижняя кнопка "Заказать"
                {"Петр", "Петров", "ул. Пушкина, 5", "Черкизовская", "89876543210", MainPage.orderButtonAtTheBottom}
        };
    }

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @Test // тест первой формы заказа, до нажатия на кнопку "Далее"
    public void testOrderScooterForm1() {

        MainPage mainPage = new MainPage(webDriver);

        mainPage.open();

        OrderPage orderPage = new OrderPage(webDriver);

        if (orderButton.equals(MainPage.orderButtonAtTheTop)) {
            // Кликаем на верхнюю кнопку
            orderPage.clickOrderButtonTop();
        } else {
            // Прокручиваем страницу до нижней кнопки
            WebElement bottomOrderButton = webDriver.findElement(MainPage.orderButtonAtTheBottom);
            ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", bottomOrderButton);

            orderPage.clickOrderButtonBottom();

        }

        orderPage.enterName(name)
                .EnterSurname(surname)
                .EnterAddress(address)
                .ChooseSubway(subwayStation)
                .EnterPhoneNumber()
                .clickNextButton();

        AboutRentPage aboutRentPage = new AboutRentPage(webDriver);

// проверяем что после заполнения первой формы и нажатия кнопки далее, отображается хедер следующей формы, а значит все поля первой формы заполнены
        assertTrue("Следующий шаг (Про аренду) не отображается", aboutRentPage.AboutRentHeaderIsDisplayed());

    }

    @After
    public void tearDown() {
        // Закрываем браузер после выполнения теста
        webDriver.quit();
    }
}


