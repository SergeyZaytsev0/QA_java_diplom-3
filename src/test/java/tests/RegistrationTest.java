package tests;

import api.AuthServices;
import api.pages.LoginPage;
import api.pages.MainPage;
import api.pages.RegistrationPage;
import api.pojo.Credentials;
import io.qameta.allure.junit4.DisplayName;
import model.ProfileGenerator;
import model.UserProfile;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.*;
import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;

public class RegistrationTest {

    private RegistrationPage registration;
    private LoginPage login;
    private AuthServices authServices;
    private String accessToken;
    private UserProfile profile;

    @Before
    public void setUp() {
        authServices = new AuthServices();
        MainPage mainPage = open(MainPage.URL, MainPage.class);
        registration = open(RegistrationPage.URL, RegistrationPage.class);
        profile = ProfileGenerator.getRandom();
        login = page(LoginPage.class);
    }

    @Test
    @DisplayName("Successful registration")
    public void successfulRegistration() {
        registration.registerNewUser(profile);
        login.checkLoginHeader();
        Credentials credentials = new Credentials(profile.getEmail(), profile.getPassword());
        accessToken = authServices.accessToken(authServices.loginUser(credentials)
                .assertThat()
                .statusCode(SC_OK));
    }

    @Test
    @DisplayName("Enter short password")
    public void enterShortPassword() {
        profile.setPassword("false");
        registration.registerNewUser(profile);
        registration.checkInvalidPasswordNotificationDisplayed();
        Credentials credentials = new Credentials(profile.getEmail(), profile.getPassword());
        accessToken = authServices.accessToken(authServices.loginUser(credentials)
                .assertThat()
                .statusCode(SC_UNAUTHORIZED));
    }

    @After
    public void tearDown() {

        if (accessToken != null) {
            authServices.deleteUser(accessToken);
        }
        webdriver().driver().close();
    }
}
