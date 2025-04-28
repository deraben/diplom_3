package ui.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import ui.pageobjects.*;

import static org.junit.Assert.assertTrue;

public class LoginTest extends BaseTest {

    @Before
    @Override
    public void setUp() {
        super.setUp();
        createTestUser();
        if (testUser == null) {
            org.junit.Assert.fail("Prerequisite: Failed to create test user via API for login tests.");
        }
    }

    @Test
    @DisplayName("Login via 'Login to Account' button on Home page")
    @Description("Verify successful login using the main button on the home page.")
    public void testLoginViaHomePageButton() {
        HomePage homePage = new HomePage(driver).open();
        LoginPage loginPage = homePage.clickLoginButtonMain();
        homePage = loginPage.login(testUser.getEmail(), testUser.getPassword());

        assertTrue("Should be logged in and see the Order button on Home page", homePage.isOrderButtonVisible());
    }

    @Test
    @DisplayName("Login via 'Personal Account' link in header")
    @Description("Verify successful login using the 'Personal Account' link in the header.")
    public void testLoginViaHeaderLink() {
        HomePage homePage = new HomePage(driver).open();
        LoginPage loginPage = homePage.clickPersonalAccountLinkUnauthenticated();
        homePage = loginPage.login(testUser.getEmail(), testUser.getPassword());

        assertTrue("Should be logged in and see the Order button on Home page", homePage.isOrderButtonVisible());
    }

    @Test
    @DisplayName("Login via 'Login' link on Registration page")
    @Description("Verify successful login using the 'Login' link on the registration page.")
    public void testLoginViaRegisterPageLink() {
        RegisterPage registerPage = new RegisterPage(driver).open();
        LoginPage loginPage = registerPage.clickLoginLink();
        HomePage homePage = loginPage.login(testUser.getEmail(), testUser.getPassword());

        assertTrue("Should be logged in and see the Order button on Home page", homePage.isOrderButtonVisible());
    }

    @Test
    @DisplayName("Login via 'Login' link on Forgot Password page")
    @Description("Verify successful login using the 'Login' link on the password recovery page.")
    public void testLoginViaForgotPasswordPageLink() {
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver).open();
        LoginPage loginPage = forgotPasswordPage.clickLoginLink();
        HomePage homePage = loginPage.login(testUser.getEmail(), testUser.getPassword());

        assertTrue("Should be logged in and see the Order button on Home page", homePage.isOrderButtonVisible());
    }
}
