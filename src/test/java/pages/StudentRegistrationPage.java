package pages;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class StudentRegistrationPage {

    String firstName = "Alex",
            lastName = "Alexov",
            userEmail = "aa@aa.com",
            gender = "Other",
            userPhoneNumber = "1234567890",
            dateOfBirth = "10",
            monthOfBirth = "May",
            yearOfBirth = "1988",
            subject = "Chemistry",
            hobbies = "Sports",
            picture = "1.PNG",
            currentAddress = "Montenegro 123",
            state = "Uttar Pradesh",
            city = "Merrut";

    @Step("Open students registration form")
    public void openPage() {
        open("https://demoqa.com/automation-practice-form");
    }

    @Step("Fill students registration form")
    public void fillForm() {
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
    }

    @Step("Send students registration form")
    public void sendForm() {
        $("#submit").click();
    }

    @Step("Verify successful form submit")
    public void verifyForm() {
        $(".table-responsive").shouldHave(text(firstName), text(lastName),
                text(userEmail), text(gender), text(userPhoneNumber),
                text(dateOfBirth + " " + monthOfBirth + "," + yearOfBirth), text(subject),
                text(hobbies), text(picture), text(currentAddress), text(state + " " + city));
    }
}
