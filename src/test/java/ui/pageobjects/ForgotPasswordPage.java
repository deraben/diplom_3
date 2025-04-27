package ui.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ui.config.AppConfig;

public class ForgotPasswordPage extends BasePage {

    private final By forgotPasswordTitle = By.xpath(".//h2[text()='Восстановление пароля']");
    private final By emailInput = By.xpath(".//label[text()='Email']/following-sibling::input");
    private final By recoverButton = By.xpath(".//button[text()='Восстановить']");
    private final By loginLink = By.xpath(".//a[text()='Войти']");

    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }

    @Step("Open the Forgot Password page directly")
    public ForgotPasswordPage open() {
        driver.get(AppConfig.FORGOT_PASSWORD_PAGE_URL);
        waitForElementToBeVisible(forgotPasswordTitle);
        return this;
    }

    @Step("Click the 'Login' link")
    public LoginPage clickLoginLink() {
        waitForElementToBeClickable(loginLink);
        driver.findElement(loginLink).click();
        return new LoginPage(driver);
    }
}
