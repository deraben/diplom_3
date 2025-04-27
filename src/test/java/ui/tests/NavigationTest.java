package ui.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import ui.pageobjects.HomePage;
import ui.pageobjects.LoginPage;
import ui.pageobjects.ProfilePage;

import static org.junit.Assert.assertTrue;
import static ui.config.AppConfig.PROFILE_PAGE_URL;
import static ui.config.AppConfig.BASE_URL;

public class NavigationTest extends BaseTest {

    @Before
    @Override
    public void setUp() {
        super.setUp();
        createTestUser();
        if (testUser == null) {
            org.junit.Assert.fail("Prerequisite: Failed to create test user via API for navigation tests.");
        }

        new LoginPage(driver)
                .open()
                .login(testUser.getEmail(), testUser.getPassword());

        assertTrue("Prerequisite: Login failed before navigation tests", new HomePage(driver).isOrderButtonVisible());
    }

    @Test
    @DisplayName("Navigate to Personal Account")
    @Description("Verify clicking 'Personal Account' navigates to the profile page when logged in.")
    public void testNavigateToPersonalAccount() {
        HomePage homePage = new HomePage(driver);
        ProfilePage profilePage = homePage.clickPersonalAccountLinkAuthenticated();

        assertTrue("Should be on the Profile page", profilePage.isProfileLinkActive());

        assertTrue("URL should be the profile page URL", profilePage.getCurrentUrl().contains(PROFILE_PAGE_URL));
    }

    @Test
    @DisplayName("Navigate from Personal Account to Constructor via link")
    @Description("Verify clicking 'Constructor' link in the header navigates from profile to home/constructor page.")
    public void testNavigateFromProfileToConstructorLink() {

        HomePage homePageInitial = new HomePage(driver);
        ProfilePage profilePage = homePageInitial.clickPersonalAccountLinkAuthenticated();

        HomePage homePage = profilePage.clickConstructorLink();

        assertTrue("Should be on the Home/Constructor page", homePage.isAssembleBurgerTitleVisible());
    }

    @Test
    @DisplayName("Navigate from Personal Account to Constructor via Logo")
    @Description("Verify clicking the Stellar Burgers logo navigates from profile to home/constructor page.")
    public void testNavigateFromProfileToConstructorLogo() {

        HomePage homePageInitial = new HomePage(driver);
        ProfilePage profilePage = homePageInitial.clickPersonalAccountLinkAuthenticated();

        HomePage homePage = profilePage.clickLogoLink();

        assertTrue("Should be on the Home/Constructor page", homePage.isAssembleBurgerTitleVisible());

        String currentUrl = homePage.getCurrentUrl();
        String expectedUrl = BASE_URL.endsWith("/") ? BASE_URL : BASE_URL + "/";
        assertTrue("URL should be the base URL", currentUrl.equals(expectedUrl));
    }
}
