import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Value
public class DataGenerator {
    public String generateDate(int days) {
        String pattern = "dd.MM.yyyy";
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern(pattern));
    }

    public String generateCity(String locale) {
        var faker = new Faker(new Locale(locale));
        return faker.address().city();
    }

    public String generateName(String locale) {
        var faker = new Faker(new Locale(locale));
        return faker.name().lastName() + " " + faker.name().firstName();
    }

    public String generatePhone(String locale) {
        var faker = new Faker(new Locale(locale));
        return faker.phoneNumber().phoneNumber();
    }
}
