package ru.netology.delivery.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    private DataGenerator() {
    }

    public static String generateDate(int days) {
        String pattern = "dd.MM.yyyy";
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String generateCity() {
        Random rand = new Random();
        String[] cities = new String[] {
                "Тула", "Москва", "Санкт-Петербург",
                "Белгород", "Пермь", "Якутск",
                "Симферополь", "Екатеринбург", "Чебоксары",
                "Владивосток", "Владимир", "Южно-Сахалинск",
                "Йошкар-Ола", "Петропавловск-Камчатский"
        };
        return cities[rand.nextInt(cities.length)];
    }

    public static String generateName(String locale) {
        var faker = new Faker(new Locale(locale));
        return faker.name().lastName() + " " + faker.name().firstName();
    }

    public static String generatePhone(String locale) {
        var faker = new Faker(new Locale(locale));
        return faker.phoneNumber().phoneNumber();
    }

    public static class Registration {
        private Registration() {
        }

        public static UserInfo generateUser(String locale) {
            String city = generateCity();
            String name = generateName(locale);
            String phone = generatePhone(locale);

            UserInfo user = new UserInfo(city, name, phone);
            return user;
        }
    }

    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;
    }
}
