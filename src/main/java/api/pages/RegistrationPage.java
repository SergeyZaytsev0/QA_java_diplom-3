package api.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import model.UserProfile;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class RegistrationPage {
    public static final String URL = "https://stellarburgers.nomoreparties.site/register";

    @FindBy(how = How.XPATH, using = "//label[text()='Имя']/following-sibling::input")
    private SelenideElement nameInput;

    @FindBy(how = How.XPATH, using = "//label[text()='Email']/following-sibling::input")
    private SelenideElement emailInput;

    @FindBy(how = How.XPATH, using = "//*[text()='Пароль']/following-sibling::input")
    private SelenideElement passwordInput;

    @FindBy(how = How.CSS, using = ".Auth_form__3qKeq > button")
    private SelenideElement registrationButton;

    @FindBy(how = How.CSS, using = ".input__error ")
    private SelenideElement invalidPasswordText;

    @FindBy(how = How.XPATH, using = ".//a[text()='Войти']")
    private SelenideElement loginLink;

    @Step("Enter Name")
    public void enterName(String name) {
        nameInput.setValue(name);
    }

    @Step("Enter Password")
    public void enterPassword(String password) {
        passwordInput.setValue(password);
    }

    @Step("Enter email")
    public void enterEmail(String email) {
        emailInput.setValue(email);
    }

    @Step("Navigate to Login Page from Registration Page")
    public LoginPage navigateToLoginFromRegistration() {
        loginLink.click();
        return page(LoginPage.class);
    }

    @Step("Register a New User")
    public void registerNewUser(UserProfile profile) {
        enterName(profile.getName());
        enterEmail(profile.getEmail());
        enterPassword(profile.getPassword());
        registrationButton.click();
    }

    @Step("Check Invalid Password Notification")
    public boolean checkInvalidPasswordNotificationDisplayed() {
        return invalidPasswordText.isDisplayed();
    }
}
