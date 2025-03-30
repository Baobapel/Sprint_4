package ru.practicum.yandex;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.practicum.yandex.utils.BaseTest;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderTestAboutRentForm extends BaseTest {


    // Параметры для первой формы
    private final String name;
    private final String surname;
    private final String address;
    private final String subwayStation;
    private final String phoneNumber;

    // Параметры для второй формы
    private final String deliveryDate;
    private final String rentalPeriod;
    private final String scooterColor;
    private final String comment;

    // Конструктор для параметров
    public OrderTestAboutRentForm(String name, String surname, String address, String subwayStation, String phoneNumber,
                                  String deliveryDate, String rentalPeriod, String scooterColor, String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.subwayStation = subwayStation;
        this.phoneNumber = phoneNumber;
        this.deliveryDate = deliveryDate;
        this.rentalPeriod = rentalPeriod;
        this.scooterColor = scooterColor;
        this.comment = comment;
    }

    // Метод для предоставления данных
    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                // Первый набор данных
                {"Иван", "Иванов", "ул. Ленина, 10", "Сокольники", "81234567890",
                        "25.12.2023", "сутки", "black", "Позвонить за час до доставки"},
                // Второй набор данных
                {"Петр", "Петров", "ул. Пушкина, 5", "Черкизовская", "89876543210",
                        "30.12.2023", "двое суток", "grey", "Оставить у двери"}
        };
    }

    @Test
    public void testOrderAboutRentForm() {
        // Открываем главную страницу

        // Создаем объект страницы "Про аренду"

        // нажимаем на кнопку заказать на главной странице

        orderPage.clickOrderButtonTop();

        // Заполняем первую форму и переходим на вторую форму
        aboutRentPage.fillFirstFormAndProceed(name, surname, address, subwayStation, phoneNumber);

        // Проверяем, что заголовок "Про аренду" отображается
        assertTrue("Заголовок 'Про аренду' не отображается", aboutRentPage.AboutRentHeaderIsDisplayed());

        // Заполняем вторую форму
        aboutRentPage.enterDeliveryDate(deliveryDate)
                .selectRentalPeriod(rentalPeriod)
                .selectScooterColor(scooterColor)
                .enterComment(comment)
                .clickOrderButton();

        // Проверяем, что всплывающее окно с вопросом "Хотите оформить заказ?" отображается
        assertTrue("Всплывающее окно с вопросом не отображается", aboutRentPage.isConfirmOrderModalDisplayed());

        // Нажимаем кнопку "Да" и тут она в хроме не отображается и тест падает, это баг.
        aboutRentPage.clickConfirmYesButton();

        // Проверяем, что всплывающее окно с надписью "Заказ оформлен" отображается
        assertTrue("Всплывающее окно с подтверждением заказа не отображается", aboutRentPage.isOrderSuccessModalDisplayed());
    }
}