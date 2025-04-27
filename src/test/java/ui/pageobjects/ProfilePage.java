package ui.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ui.config.AppConfig;

public class ProfilePage extends BasePage {

    private final By profileLinkActive = By.xpath(".//a[contains(@class, 'Account_link_active') and text()='Профиль']");
    private final By logoutButton = By.xpath(".//button[text()='Выход']");
    private final By constructorLink = By.xpath(".//p[text()='Конструктор']/parent::a");
    private final By logoLink = By.xpath(".//div[contains(@class, 'AppHeader_header__logo')]/a");

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    @Step("Open the Profile page directly (requires prior login)")
    public ProfilePage open() {
        driver.get(AppConfig.PROFILE_PAGE_URL);
        return this;
    }

    @Step("Check if the Profile link is active/visible")
    public boolean isProfileLinkActive() {
        return driver.findElement(profileLinkActive).isDisplayed();
    }

    @Step("Check if the Logout button is visible")
    public boolean isLogoutButtonVisible() {
        waitForElementToBeVisible(logoutButton);
        return driver.findElement(logoutButton).isDisplayed();
    }

    @Step("Click the Logout button")
    public LoginPage clickLogoutButton() {
        waitForElementToBeClickable(logoutButton);
        driver.findElement(logoutButton).click();
        return new LoginPage(driver);
    }

    @Step("Click the 'Constructor' link")
    public HomePage clickConstructorLink() {
        waitForElementToBeClickable(constructorLink);
        driver.findElement(constructorLink).click();
        return new HomePage(driver);
    }

    @Step("Click the Stellar Burgers logo")
    public HomePage clickLogoLink() {
        waitForElementToBeClickable(logoLink);
        driver.findElement(logoLink).click();
        return new HomePage(driver);
    }
}
