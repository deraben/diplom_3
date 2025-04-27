package ui.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ui.config.AppConfig;

public class LoginPage extends BasePage {

    private final By loginTitle = By.xpath(".//h2[text()='Вход']");
    private final By emailInput = By.xpath(".//label[text()='Email']/following-sibling::input");
    private final By passwordInput = By.xpath(".//label[text()='Пароль']/following-sibling::input");
    private final By loginButton = By.xpath(".//button[text()='Войти']");
    private final By registerLink = By.xpath(".//a[text()='Зарегистрироваться']");
    private final By forgotPasswordLink = By.xpath(".//a[text()='Восстановить пароль']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Open the Login page directly")
    public LoginPage open() {
        driver.get(AppConfig.LOGIN_PAGE_URL);
        waitForElementToBeVisible(loginTitle);
        return this;
    }

    @Step("Check if Login title is visible")
    public boolean isLoginTitleVisible() {
        return driver.findElement(loginTitle).isDisplayed();
    }

    @Step("Enter email: {email}")
    public LoginPage setEmail(String email) {
        waitForElementToBeVisible(emailInput);
        driver.findElement(emailInput).sendKeys(email);
        return this;
    }

    @Step("Enter password: {password}")
    public LoginPage setPassword(String password) {
        waitForElementToBeVisible(passwordInput);
        driver.findElement(passwordInput).sendKeys(password);
        return this;
    }

    @Step("Click the Login button")
    public HomePage clickLoginButtonSuccess() {
        waitForElementToBeClickable(loginButton);
        driver.findElement(loginButton).click();
        return new HomePage(driver);
    }

    @Step("Click the Login button (expecting failure)")
    public LoginPage clickLoginButtonFailure() {
        waitForElementToBeClickable(loginButton);
        driver.findElement(loginButton).click();
        return this;
    }

    @Step("Perform login with email: {email} and password: {password}")
    public HomePage login(String email, String password) {
        setEmail(email);
        setPassword(password);
        return clickLoginButtonSuccess();
    }

    @Step("Click the 'Register' link")
    public RegisterPage clickRegisterLink() {
        waitForElementToBeClickable(registerLink);
        driver.findElement(registerLink).click();
        return new RegisterPage(driver);
    }

    @Step("Click the 'Forgot Password' link")
    public ForgotPasswordPage clickForgotPasswordLink() {
        waitForElementToBeClickable(forgotPasswordLink);
        driver.findElement(forgotPasswordLink).click();
        return new ForgotPasswordPage(driver);
    }
}
