package test;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static utils.RandomUtils.*;

public class FormTestWithFakersWithSteps extends TestBase {

    @Test
    @DisplayName("Successful fill form test with Steps")
    void dataAfterSubmitForm() {
        Faker faker = new Faker();

        String firstName = faker.name().firstName(),
                lastName = faker.name().lastName(),
                userEmail = faker.internet().emailAddress(),
                gender = getRandomGender(),
                userPhoneNumber = faker.number().digits(10),
                yearOfBirth = faker.number().numberBetween(1900, 2020) + "",
                monthOfBirth = getRandomMonth(),
                dateOfBirth = faker.number().numberBetween(1, 29) + "",
                subject = getRandomSubject(),
                hobbies = getRandomHobbie(),
                picture = "1.PNG",
                currentAddress = faker.address().fullAddress(),
                state = "NCR",
                city = "Delhi";

        step("Open students registration form", () -> open("https://demoqa.com/automation-practice-form"));

        step("Fill students registration form", () -> {
            $("#firstName").setValue(firstName);
            $("#lastName").setValue(lastName);
            $("#userEmail").setValue(userEmail);
            // set gender checkbox
            $("#genterWrapper").$(byText(gender)).click();
            // set phoneNumber
            $("#userNumber").setValue(userPhoneNumber);
            // set date
            $("#dateOfBirthInput").click();
            $(".react-datepicker__year-select").click();
            $(byText(yearOfBirth)).click();
            $(".react-datepicker__month-select").click();
            $(byText(monthOfBirth)).click();
            $(byText(dateOfBirth)).click();
            //  Set subjects
            $("#subjectsInput").val(subject);
            $(".subjects-auto-complete__menu-list").$(byText(subject)).click();
            // set hobbies
            $(byText(hobbies)).click();
            // upload picture
            $("#uploadPicture").uploadFile(new File("src/test/resources/" + picture));
            //set address
            $("#currentAddress").setValue(currentAddress);
            // set state and city
            $("#state").click();
            $(byText(state)).click();
            $("#city").click();
            $(byText(city)).click();
        });

        step("Send students registration form", () -> $("#submit").click());

        step("Verify successful form submit", () -> $(".table-responsive").shouldHave(text(firstName), text(lastName),
                text(userEmail), text(gender), text(userPhoneNumber),
                text(dateOfBirth + " " + monthOfBirth + "," + yearOfBirth), text(subject),
                text(hobbies), text(picture), text(currentAddress), text(state + " " + city)));

    }
}
