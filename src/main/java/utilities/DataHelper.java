package utilities;

import com.github.javafaker.Faker;

import java.util.Date;
import java.util.Locale;

public class DataHelper {
    Faker faker = new Faker(Locale.US);

    public static DataHelper getDataHelper() {
        return new DataHelper();
    }

    public String getFirstName() {
        return faker.name().firstName();
    }

    public String getLastName() {
        return faker.name().lastName();
    }

    public String getUserEmail() {
        return faker.internet().emailAddress();
    }

    public String getPassword() {
        return faker.internet().password();
    }

    public Date getBirthday() {
        return faker.date().birthday();
    }

    public String getCompanyName() {
        return faker.company().name();
    }

    public String getHumanGender() {
        return faker.demographic().sex();
    }

    public String getCountry() {
        return faker.country().name();
    }

    public String getProvince() {
        return faker.address().state();
    }

    public String getProductName() {
        return faker.commerce().productName();
    }

    public String getCity() {
        return faker.address().city();
    }

    public String getAddress() {
        return faker.address().streetAddress();
    }

    public String getPhoneNumber() {
        return faker.phoneNumber().phoneNumber();
    }

    public String getPostalCode() {
        return faker.address().zipCode();
    }

    public String getFaxNumber() {
        return "0" + faker.phoneNumber().phoneNumber().substring(1);
    }


}
