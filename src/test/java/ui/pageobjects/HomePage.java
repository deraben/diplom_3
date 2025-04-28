package ui.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.config.AppConfig;

public class HomePage extends BasePage {

    private final By loginButtonMain = By.xpath(".//button[text()='Войти в аккаунт']");
    private final By personalAccountLink = By.xpath(".//p[text()='Личный Кабинет']/parent::a");
    private final By assembleBurgerTitle = By.xpath(".//h1[text()='Соберите бургер']");
    private final By orderButton = By.xpath(".//button[text()='Оформить заказ']");

    private final By bunsTab = By.xpath(".//span[text()='Булки']/parent::div");
    private final By saucesTab = By.xpath(".//span[text()='Соусы']/parent::div");
    private final By fillingsTab = By.xpath(".//span[text()='Начинки']/parent::div");
    private final By activeTab = By.xpath(".//div[contains(@class, 'tab_tab_type_current')]");

    private final By logoLink = By.xpath(".//div[contains(@class, 'AppHeader_header__logo')]/a");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Step("Open the Home page")
    public HomePage open() {
        driver.get(AppConfig.BASE_URL);
        waitForElementToBeVisible(assembleBurgerTitle);
        return this;
    }

    @Step("Click the main 'Login to Account' button")
    public LoginPage clickLoginButtonMain() {
        waitForElementToBeClickable(loginButtonMain);
        driver.findElement(loginButtonMain).click();
        return new LoginPage(driver);
    }

    @Step("Click the 'Personal Account' link in the header")
    public LoginPage clickPersonalAccountLinkUnauthenticated() {
        waitForElementToBeClickable(personalAccountLink);
        driver.findElement(personalAccountLink).click();
        return new LoginPage(driver);
    }

    @Step("Click the 'Personal Account' link in the header (when authenticated)")
    public ProfilePage clickPersonalAccountLinkAuthenticated() {
        waitForElementToBeClickable(personalAccountLink);
        driver.findElement(personalAccountLink).click();
        return new ProfilePage(driver);
    }

    @Step("Check if 'Assemble Burger' title is visible")
    public boolean isAssembleBurgerTitleVisible() {
        waitForElementToBeVisible(assembleBurgerTitle);
        return driver.findElement(assembleBurgerTitle).isDisplayed();
    }

    @Step("Check if 'Order' button is visible (indicates successful login)")
    public boolean isOrderButtonVisible() {
        try {
            waitForElementToBeVisible(orderButton);
            return driver.findElement(orderButton).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Click on the 'Buns' tab")
    public HomePage clickBunsTab() {
        waitForElementToBeClickable(bunsTab);
        driver.findElement(bunsTab).click();
        wait.until(ExpectedConditions.attributeContains(bunsTab, "class", "tab_tab_type_current"));
        return this;
    }

    @Step("Click on the 'Sauces' tab")
    public HomePage clickSaucesTab() {
        waitForElementToBeClickable(saucesTab);
        driver.findElement(saucesTab).click();
        wait.until(ExpectedConditions.attributeContains(saucesTab, "class", "tab_tab_type_current"));
        return this;
    }

    @Step("Click on the 'Fillings' tab")
    public HomePage clickFillingsTab() {
        waitForElementToBeClickable(fillingsTab);
        driver.findElement(fillingsTab).click();
        wait.until(ExpectedConditions.attributeContains(fillingsTab, "class", "tab_tab_type_current"));
        return this;
    }

    @Step("Get the text of the currently active constructor tab")
    public String getActiveTabText() {
        waitForElementToBeVisible(activeTab);
        return driver.findElement(activeTab).getText();
    }

    @Step("Click the Stellar Burgers logo")
    public HomePage clickLogo() {
        waitForElementToBeClickable(logoLink);
        driver.findElement(logoLink).click();
        return this;
    }

    @Step("Wait for active tab text to become '{expectedText}'")
    public void waitForActiveTabText(String expectedText) {
        try {
            wait.until(ExpectedConditions.textToBe(activeTab, expectedText));
        } catch (TimeoutException e) {
            System.err.println("Timeout waiting for active tab text to become '" + expectedText + "'. Found: '"
                    + getActiveTabText() + "'");
            throw e;
        }
    }
}
