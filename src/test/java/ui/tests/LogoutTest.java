package ui.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import ui.pageobjects.HomePage;
import ui.pageobjects.LoginPage;
import ui.pageobjects.ProfilePage;

import static org.junit.Assert.assertTrue;

public class LogoutTest extends BaseTest {

    @Before
    @Override
    public void setUp() {
        super.setUp();
        createTestUser();
        if (testUser == null) {
            org.junit.Assert.fail("Prerequisite: Failed to create test user via API for logout test.");
        }

        new LoginPage(driver)
                .open()
                .login(testUser.getEmail(), testUser.getPassword());

        assertTrue("Prerequisite: Login failed before logout test", new HomePage(driver).isOrderButtonVisible());

        new HomePage(driver).clickPersonalAccountLinkAuthenticated();
        assertTrue("Prerequisite: Failed to navigate to profile page", new ProfilePage(driver).isLogoutButtonVisible());
    }

    @Test
    @DisplayName("Logout from Personal Account")
    @Description("Verify clicking 'Logout' in the personal account logs the user out and redirects to the login page.")
    public void testLogoutFromProfile() {
        ProfilePage profilePage = new ProfilePage(driver);
        LoginPage loginPage = profilePage.clickLogoutButton();

        assertTrue("Should be on the Login page after logout", loginPage.isLoginTitleVisible());

        accessToken = null;
    }
}
