package api.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class MainPage {

    public static final String URL = "https://stellarburgers.nomoreparties.site/";

    @FindBy(how = How.CSS, using = ".AppHeader_header__logo__2D0X2 ~ a")
    protected SelenideElement linkToProfilePage;

    @FindBy(how = How.XPATH, using = "//p[text()='Конструктор']")
    protected SelenideElement constructorLabel;

    @FindBy(how = How.XPATH, using = ".//span [@class='text text_type_main-default'][text()='Булки']")
    protected SelenideElement bunsTab;

    @FindBy(how = How.XPATH, using = ".//span[text()='Булки']")
    protected SelenideElement bunsTitle;

    @FindBy(how = How.XPATH, using = ".//span [@class='text text_type_main-default'][text()='Соусы']")
    protected SelenideElement saucesTab;

    @FindBy(how = How.XPATH, using = ".//span[text()='Соусы']")
    protected SelenideElement saucesTitle;

    @FindBy(how = How.XPATH, using = ".//span [@class='text text_type_main-default'][text()='Начинки']")
    protected SelenideElement fillingsTab;

    @FindBy(how = How.XPATH, using = ".//span[text()='Начинки']")
    protected SelenideElement fillingsTitle;

    @FindBy(how = How.XPATH, using = ".//button [text()='Войти в аккаунт']")
    private SelenideElement loginButton;

    @FindBy(how = How.XPATH, using = "//* [@href='/account']")
    private SelenideElement cabinetButton;

    @FindBy(how = How.XPATH, using = ".//button[text()='Оформить заказ']")
    private SelenideElement checkout;

    @Step("Click 'Sign in' button")
    public LoginPage clickSignInButton() {
        loginButton.click();
        return page(LoginPage.class);
    }

    @Step("Click 'Personal account' button")
    public LoginPage clickPersonalAccountButton() {
        cabinetButton.click();
        return page(LoginPage.class);
    }

    @Step("Go to profile page")
    public LoginPage goToProfilePage() {
        linkToProfilePage.click();
        return page(LoginPage.class);
    }

    @Step("Click on the Buns section")
    public MainPage clickOnBunsSection() {
        bunsTab.click();
        return page(MainPage.class);
    }

    @Step("Check visibility of Buns section")
    public boolean isBunsSectionVisible() {
        return bunsTitle.getAttribute("class").contains("tab_tab__1SPyG tab_tab_type_current__2BEPc");
    }

    @Step("Click on the Sauces section")
    public MainPage clickOnSaucesSection() {
        saucesTab.click();
        return page(MainPage.class);
    }

    @Step("Check visibility of Sauces section")
    public boolean isSaucesSectionVisible() {
        return saucesTitle.getAttribute("class").contains("tab_tab__1SPyG tab_tab_type_current__2BEPc");
    }

    @Step("Click on the Fillings section")
    public MainPage clickOnFillingsSection() {
        fillingsTab.click();
        return page(MainPage.class);
    }

    @Step("Check visibility of Fillings section")
    public boolean isFillingsSectionVisible() {
        return fillingsTitle.getAttribute("class").contains("tab_tab__1SPyG tab_tab_type_current__2BEPc");
    }

    @Step("Check visibility of Constructor block")
    public LoginPage checkConstructorBlockVisibility() {
        constructorLabel.shouldBe(visible);
        return page(LoginPage.class);
    }

    @Step("Check visibility of 'Place order' button")
    public boolean isPlaceOrderButtonVisible() {
        checkout.is(Condition.visible);
        return true;
    }

}
