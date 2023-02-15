package tests;

import api.AuthServices;
import api.pages.MainPage;
import api.pages.ProfilePage;
import api.pojo.Credentials;
import api.pojo.User;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static org.apache.hc.core5.http.HttpStatus.SC_OK;

public class ConstructorTest {
    private MainPage main;
    private AuthServices userClient;
    private String accessToken;

    @Before
    public void setUp() {
        userClient = new AuthServices();
        User user = User.randomUser();
        Credentials credentials = Credentials.getCredentials(user);
        accessToken = userClient.accessToken(userClient.registerUser(user)
                .assertThat()
                .statusCode(SC_OK));
        main = open(MainPage.URL, MainPage.class);
        main
                .clickSignInButton()
                .enterEmail(credentials.getEmail())
                .enterPassword(credentials.getPassword())
                .loginButtonClick();
    }

    @Test
    @DisplayName("Check transition via constructor and logo")
    public void checkTransitionConstructorLogo() {
        main.goToProfilePage();
        ProfilePage profile = page(ProfilePage.class);
        profile.navigateToLogoBuilderPage();
        main.checkConstructorBlockVisibility();
    }

    @Test
    @DisplayName("Check transition to Buns section")
    public void checkTransitionBuns() {
        main
                .clickOnFillingsSection()
                .clickOnBunsSection()
                .isBunsSectionVisible();
    }

    @Test
    @DisplayName("Check transition to Sauces section")
    public void checkTransitionSauces() {
        main
                .clickOnSaucesSection()
                .isSaucesSectionVisible();
    }

    @Test
    @DisplayName("Check transition to Fillings section")
    public void checkTransitionFillings() {
        main
                .clickOnFillingsSection()
                .isFillingsSectionVisible();
    }

    @After
    public void tearDown() {
        userClient.deleteUser(accessToken);
        Selenide.closeWebDriver();
    }
}
