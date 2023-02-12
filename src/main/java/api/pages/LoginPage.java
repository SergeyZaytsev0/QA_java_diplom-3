package api.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Selenide.page;

public class LoginPage {


    @FindBy(how = How.CSS, using = "[name=\"name\"]")
    protected SelenideElement emailInput;
    @FindBy(how = How.CSS, using = ".Auth_login__3hAey > h2")
    protected SelenideElement loginHeader;
    @FindBy(how = How.CSS, using = "[name=\"Пароль\"]")
    protected SelenideElement passwordInput;
    @FindBy(how = How.XPATH, using = ".//button [text()='Войти']")
    private SelenideElement loginButton;
    @FindBy(how = How.XPATH, using = ".//fieldset[1]//input")
    private SelenideElement inputName;

    @FindBy(how = How.XPATH, using = ".//input[@name='name']")
    private SelenideElement inputEmail;

    @FindBy(how = How.XPATH, using = ".//input[@name='Пароль']")
    private SelenideElement inputPassword;

    @FindBy(how = How.XPATH, using = ".//a[text()='Войти']")
    private SelenideElement loginLink;

    @FindBy(how = How.XPATH, using = ".//a[text()='Зарегистрироваться']")
    private SelenideElement regLink;

    @FindBy(how = How.XPATH, using = ".//a[text()='Восстановить пароль']")
    private SelenideElement resetPasswordLink;

    @FindBy(how = How.XPATH, using = "//*[@class='AppHeader_header__logo__2D0X2']")
    private SelenideElement burgerButton;

    @FindBy(how = How.XPATH, using = "//*[text()='Конструктор']")
    private SelenideElement constructorButton;

    @FindBy(how = How.XPATH, using = ".//button[text()='Выход']")
    private SelenideElement exitButton;

    @Step("Enter email")
    public LoginPage enterEmail(String email) {
        inputEmail.shouldBe(empty).click();
        inputEmail.setValue(email);
        return this;
    }

    @Step("Enter password")
    public LoginPage enterPassword(String password) {
        inputPassword.click();
        inputPassword.setValue(password);
        return this;
    }

    @Step("Click 'Login' button")
    public MainPage loginButtonClick() {
        loginButton.click();
        return page(MainPage.class);
    }

    @Step("Click 'Sign Up' link")
    public RegistrationPage clickLoginButton() {
        regLink.click();
        return page(RegistrationPage.class);
    }

    @Step("Click 'Login' link")
    public LoginPage clickSignUpLink() {
        loginLink.click();
        return this;
    }

    @Step("Click 'Reset Password' link")
    public LoginPage resetPasswordLinkClick() {
        resetPasswordLink.scrollIntoView(true);
        resetPasswordLink.click();
        return this;
    }


    @Step("Click 'Constructor' button")
    public LoginPage clickConstructorButton() {
        constructorButton.click();
        return this;
    }

    @Step("Click 'Stellar Burger' logo")
    public LoginPage clickStellarBurger() {
        burgerButton.click();
        return this;
    }

    @Step("Click 'Exit' button")
    public LoginPage clickExitButton() {
        exitButton.click();
        return this;
    }

    @Step("Check 'Login' header")
    public void checkLoginHeader() {
        loginHeader.shouldHave(Condition.text("Вход"));
    }

    @Step("Check if authorization is visible")
    public void checkAuthorizationVisibility() {
        loginButton.shouldBe(Condition.visible);
    }


}
