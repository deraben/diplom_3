package ui.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import ui.config.AppConfig;

public class RegisterPage extends BasePage {

    private final By registerTitle = By.xpath(".//h2[text()='Регистрация']");
    private final By nameInput = By.xpath(".//label[text()='Имя']/following-sibling::input");
    private final By emailInput = By.xpath(".//label[text()='Email']/following-sibling::input");
    private final By passwordInput = By.xpath(".//label[text()='Пароль']/following-sibling::input");
    private final By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");
    private final By loginLink = By.xpath(".//a[text()='Войти']");
    private final By passwordError = By.xpath(".//p[text()='Некорректный пароль']");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    @Step("Open the Registration page directly")
    public RegisterPage open() {
        driver.get(AppConfig.REGISTER_PAGE_URL);
        waitForElementToBeVisible(registerTitle);
        return this;
    }

    @Step("Enter name: {name}")
    public RegisterPage setName(String name) {
        waitForElementToBeVisible(nameInput);
        driver.findElement(nameInput).sendKeys(name);
        return this;
    }

    @Step("Enter email: {email}")
    public RegisterPage setEmail(String email) {
        waitForElementToBeVisible(emailInput);
        driver.findElement(emailInput).sendKeys(email);
        return this;
    }

    @Step("Enter password: {password}")
    public RegisterPage setPassword(String password) {
        waitForElementToBeVisible(passwordInput);
        driver.findElement(passwordInput).sendKeys(password);
        return this;
    }

    @Step("Click the Register button")
    public LoginPage clickRegisterButton() {
        waitForElementToBeClickable(registerButton);
        WebElement buttonElement = driver.findElement(registerButton);
        try {
            buttonElement.click();
        } catch (ElementClickInterceptedException e) {
            System.out.println("Normal click intercepted, trying JavaScript click for Register button...");
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", buttonElement);
        }
        return new LoginPage(driver);
    }

    @Step("Click the Register button (expecting error)")
    public RegisterPage clickRegisterButtonExpectingError() {
        waitForElementToBeClickable(registerButton);
        WebElement buttonElement = driver.findElement(registerButton);
        try {
            buttonElement.click();
        } catch (ElementClickInterceptedException e) {
            System.out.println(
                    "Normal click intercepted, trying JavaScript click for Register button (expecting error)...");
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", buttonElement);
        }
        return this;
    }

    @Step("Perform registration with name: {name}, email: {email}, password: {password}")
    public LoginPage register(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        return clickRegisterButton();
    }

    @Step("Perform registration expecting password error: name: {name}, email: {email}, password: {password}")
    public RegisterPage registerExpectingPasswordError(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        return clickRegisterButtonExpectingError();
    }

    @Step("Click the 'Login' link")
    public LoginPage clickLoginLink() {
        waitForElementToBeClickable(loginLink);
        driver.findElement(loginLink).click();
        return new LoginPage(driver);
    }

    @Step("Check if password error message is visible")
    public boolean isPasswordErrorVisible() {
        waitForElementToBeVisible(passwordError);
        return driver.findElement(passwordError).isDisplayed();
    }
}
