package ui.tests;

import api.helpers.UserGenerator;
import api.models.User;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import ui.pageobjects.HomePage;
import ui.pageobjects.LoginPage;
import ui.pageobjects.RegisterPage;

import static org.junit.Assert.*;

public class RegistrationTest extends BaseTest {

        @Test
        @DisplayName("Successful user registration")
        @Description("Verify that a new user can register successfully using valid credentials.")
        public void testSuccessfulRegistration() {

                User userToRegister = UserGenerator.randomUser();

                RegisterPage registerPage = new HomePage(driver)
                                .open()
                                .clickLoginButtonMain()
                                .clickRegisterLink();

                LoginPage loginPage = registerPage.register(
                                userToRegister.getName(),
                                userToRegister.getEmail(),
                                userToRegister.getPassword());

                assertTrue("Should be on the Login page after successful registration",
                                loginPage.isLoginTitleVisible());

                HomePage homePage = loginPage.login(userToRegister.getEmail(), userToRegister.getPassword());
                assertTrue("Should be logged in and see the Order button on Home page",
                                homePage.isOrderButtonVisible());

                testUser = userToRegister;
                loginTestUserAPI();
        }

        @Test
        @DisplayName("Registration with incorrect password (too short)")
        @Description("Verify that registration fails and shows an error if the password is less than 6 characters.")
        public void testRegistrationWithShortPassword() {

                User userToRegister = UserGenerator.randomUser();
                String shortPassword = "12345";

                RegisterPage registerPage = new HomePage(driver)
                                .open()
                                .clickLoginButtonMain()
                                .clickRegisterLink();

                registerPage = registerPage.registerExpectingPasswordError(
                                userToRegister.getName(),
                                userToRegister.getEmail(),
                                shortPassword);

                assertTrue("Password error message should be visible", registerPage.isPasswordErrorVisible());

        }
}
