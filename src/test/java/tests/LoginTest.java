package tests;

import api.AuthServices;
import api.pages.MainPage;
import api.pojo.Credentials;
import api.pojo.User;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;
import static org.apache.hc.core5.http.HttpStatus.SC_OK;
import static org.junit.Assert.assertTrue;

public class LoginTest {
    private MainPage main;
    private Credentials credentials;
    private AuthServices userClient;
    private String accessToken;

    @Before
    public void setUp() {
        userClient = new AuthServices();
        User user = User.randomUser();
        credentials = Credentials.getCredentials(user);
        accessToken = userClient.accessToken(userClient.registerUser(user)
                .assertThat()
                .statusCode(SC_OK));
        main = open(MainPage.URL, MainPage.class);
    }

    @Test
    @DisplayName("Login Through 'Sign In' Button on Main Page")
    public void checkLoginThroughSignInButtonOnMainPage() {
        main
                .clickSignInButton()
                .enterEmail(credentials.getEmail())
                .enterPassword(credentials.getPassword())
                .loginButtonClick();
        boolean button = main.isPlaceOrderButtonVisible();
        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/"));
        assertTrue("Button invisible", button);
    }

    @Test
    @DisplayName("Login Through 'Personal Account' Button")
    public void checkLoginThroughPersonalAccountButton() {
        main
                .clickPersonalAccountButton()
                .enterEmail(credentials.getEmail())
                .enterPassword(credentials.getPassword())
                .loginButtonClick();
        boolean button = main.isPlaceOrderButtonVisible();
        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/"));
        assertTrue("Button invisible", button);
    }

    @Test
    @DisplayName("Login Through Button in Registration Form")
    public void checkLoginThroughButtonInRegistrationForm() {
        main
                .clickSignInButton()
                .clickLoginButton()
                .navigateToLoginFromRegistration()
                .enterEmail(credentials.getEmail())
                .enterPassword(credentials.getPassword())
                .loginButtonClick();
        boolean button = main.isPlaceOrderButtonVisible();
        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/"));
        assertTrue("Button invisible", button);
    }

    @Test
    @DisplayName("Login Through Button in Password Recovery Form")
    public void checkLoginThroughButtonInPasswordRecoveryForm() {
        main
                .clickSignInButton()
                .resetPasswordLinkClick()
                .clickSignUpLink()
                .enterEmail(credentials.getEmail())
                .enterPassword(credentials.getPassword())
                .loginButtonClick();
        boolean button = main.isPlaceOrderButtonVisible();
        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/"));
        assertTrue("Button invisible", button);
    }

    @After
    public void tearDown() {
        userClient.deleteUser(accessToken);
        webdriver().driver().close();
    }
}
