package ui.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import ui.pageobjects.HomePage;

import static org.junit.Assert.assertEquals;

public class ConstructorTest extends BaseTest {

    @Test
    @DisplayName("Switch to 'Sauces' section in Constructor")
    @Description("Verify that clicking the 'Sauces' tab makes it the active section.")
    public void testSwitchToSauces() {
        HomePage homePage = new HomePage(driver).open();
        homePage.clickSaucesTab();

        assertEquals("Sauces tab should be active", "Соусы", homePage.getActiveTabText());
    }

    @Test
    @DisplayName("Switch to 'Fillings' section in Constructor")
    public void testSwitchToFillings() {
        HomePage homePage = new HomePage(driver).open();
        homePage.clickSaucesTab();
        homePage.clickFillingsTab();

        homePage.waitForActiveTabText("Начинки");

        assertEquals("Fillings tab should be active", "Начинки", homePage.getActiveTabText());
    }

    @Test
    @DisplayName("Switch back to 'Buns' section in Constructor")
    public void testSwitchToBuns() {
        HomePage homePage = new HomePage(driver).open();
        homePage.clickSaucesTab();
        homePage.clickBunsTab();

        homePage.waitForActiveTabText("Булки");

        assertEquals("Buns tab should be active", "Булки", homePage.getActiveTabText());
    }
}
