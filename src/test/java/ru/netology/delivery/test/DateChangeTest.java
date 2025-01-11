package ru.netology.delivery.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import ru.netology.delivery.data.DataGenerator;

public class DateChangeTest {
    static String locale = "ru";

    @BeforeEach
    void open() {
        Selenide.open("http://localhost:9999");
    }

    @Test
    public void sendFormAndChangeDate() {
        var user = DataGenerator.Registration.generateUser(locale);

        String firstDate = DataGenerator.generateDate(5);
        String secondDate = DataGenerator.generateDate(8);


        $("[data-test-id='city'] [type='text']").setValue(user.getCity()); //город

        $("[data-test-id='date'] .input__control").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME),
                Keys.BACK_SPACE);
        $("[data-test-id='date'] .input__control").setValue(firstDate); //дата

        $("[data-test-id='name'] [type='text']").setValue(user.getName()); //имя

        $("[data-test-id='phone'] [type='tel']").setValue(user.getPhone()); //телефон

        $("[data-test-id='agreement']").click(); //галочка

        $$("button").findBy(Condition.text("Запланировать")).click(); // Запланировать

        $("[data-test-id='success-notification']").shouldBe(Condition.visible).
                shouldHave(Condition.text("Встреча успешно запланирована на " + firstDate),
                        Condition.text("Успешно!")); // первое уведомление

        $("[data-test-id='date'] .input__control").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME),
                Keys.BACK_SPACE);
        $("[data-test-id='date'] .input__control").setValue(secondDate); //изменение даты

        $$("button").findBy(Condition.text("Запланировать")).click(); // Запланировать

        $("[data-test-id='replan-notification']").shouldBe(Condition.visible).
                shouldHave(Condition.text("У вас уже запланирована встреча на другую дату. Перепланировать?"),
                        Condition.text("Необходимо подтверждение")); // второе уведомление

        $$("button").findBy(Condition.text("Перепланировать")).click(); //Перепланировать

        $("[data-test-id='success-notification']").shouldBe(Condition.visible).
                shouldHave(Condition.text("Встреча успешно запланирована на " + secondDate),
                        Condition.text("Успешно!")); // первое уведомление
    }

}
