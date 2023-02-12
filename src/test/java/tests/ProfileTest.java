package tests;

import api.AuthServices;
import api.pages.LoginPage;
import api.pages.MainPage;
import api.pages.ProfilePage;
import api.pojo.Credentials;
import api.pojo.User;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.*;
import static org.apache.hc.core5.http.HttpStatus.SC_OK;

public class ProfileTest {

    MainPage main;
    ProfilePage profile;
    LoginPage loginClass;
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

        loginClass = page(LoginPage.class);
        profile = page(ProfilePage.class);
        main = open(MainPage.URL, MainPage.class);
        main
                .clickSignInButton()
                .enterEmail(credentials.getEmail())
                .enterPassword(credentials.getPassword())
                .loginButtonClick();
    }

    @Test
    @DisplayName("Go to personal account")
    public void personalAccount() {

        main.
                goToProfilePage();
        profile.
                verifySignOutButtonVisibility();
    }

    @Test
    @DisplayName("Exit from personal account")
    public void exitFromPersonalAccount() {

        main.goToProfilePage();
        profile.clickSignOutButton();
        loginClass.checkAuthorizationVisibility();

    }

    @After
    public void tearDown() {
        userClient.deleteUser(accessToken);
        webdriver().driver().close();
    }
}
