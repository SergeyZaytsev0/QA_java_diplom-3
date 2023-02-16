package api.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class ProfilePage {

    @FindBy(how = How.CSS, using = ".Account_listItem__35dAP button")
    private SelenideElement signOutButton;

    @FindBy(how = How.CSS, using = ".AppHeader_header__logo__2D0X2")
    private SelenideElement logoButton;

    @Step("Sign Out")
    public LoginPage clickSignOutButton() {
        signOutButton.click();
        return page(LoginPage.class);
    }

    @Step("Check Sign Out Button Visibility")
    public void verifySignOutButtonVisibility() {
        signOutButton.shouldHave(Condition.text("Выход"));
    }

    @Step("Go to Logo Builder page through Logo")
    public void navigateToLogoBuilderPage() {
        logoButton.click();
    }

}
