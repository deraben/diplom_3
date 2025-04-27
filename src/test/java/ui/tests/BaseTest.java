package ui.tests;

import api.clients.UserClient;
import api.helpers.UserGenerator;
import api.models.Credentials;
import api.models.User;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import ui.helpers.WebDriverFactory;

public abstract class BaseTest {

    protected WebDriver driver;
    protected UserClient userClient;
    protected User testUser;
    protected String accessToken;

    @Before
    @Step("Setup WebDriver and API Client")
    public void setUp() {
        driver = WebDriverFactory.getDriver();
        userClient = new UserClient();
        testUser = null;
        accessToken = null;
    }

    @After
    @Step("Tear down WebDriver and delete test user if created")
    public void tearDown() {

        if (accessToken != null && !accessToken.isEmpty()) {
            try {
                Response deleteResponse = userClient.deleteUser(accessToken);
                if (deleteResponse != null) {

                    System.out.println("User deletion attempted. Status code: " + deleteResponse.getStatusCode());
                }
            } catch (Exception e) {
                System.err.println("Could not delete user: " + e.getMessage());

            }
        }

        if (driver != null) {
            driver.quit();
            System.out.println("WebDriver quit.");
        }
    }

    @Step("Create a new unique user via API")
    protected void createTestUser() {
        testUser = UserGenerator.randomUser();
        Response registerResponse = userClient.register(testUser);

        if (registerResponse.getStatusCode() == 200) {
            accessToken = registerResponse.jsonPath().getString("accessToken");

            if (accessToken != null && accessToken.startsWith("Bearer ")) {
                accessToken = accessToken.substring(7).trim();
            }
            System.out.println("Test user created successfully via API: " + testUser.getEmail());
        } else {
            System.err.println("Failed to create test user via API. Status: " + registerResponse.getStatusCode()
                    + " Body: " + registerResponse.getBody().asString());

            accessToken = null;
            testUser = null;
        }
    }

    @Step("Login test user via API to get token")
    protected void loginTestUserAPI() {
        if (testUser == null) {
            createTestUser();
        }
        if (testUser != null) {
            Credentials credentials = Credentials.fromUser(testUser);
            Response loginResponse = userClient.login(credentials);
            if (loginResponse.getStatusCode() == 200) {
                accessToken = loginResponse.jsonPath().getString("accessToken");
                if (accessToken != null && accessToken.startsWith("Bearer ")) {
                    accessToken = accessToken.substring(7).trim();
                }
                System.out.println("Test user logged in successfully via API: " + testUser.getEmail());
            } else {
                System.err.println("Failed to log in test user via API. Status: " + loginResponse.getStatusCode()
                        + " Body: " + loginResponse.getBody().asString());
                accessToken = null;
            }
        } else {
            System.err.println("Cannot log in API - test user is null.");
            accessToken = null;
        }
    }
}
