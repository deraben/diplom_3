package ui.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ui.config.AppConfig;

import java.time.Duration;

import static ui.config.AppConfig.getBrowserName;

public class WebDriverFactory {

    public static WebDriver getDriver() {
        WebDriver driver;
        String browserName = getBrowserName();
        System.out.println("Selected browser: " + browserName);

        switch (browserName) {
            case "yandex":
                System.out.println("Attempting Yandex WebDriver setup...");
                try {

                    System.setProperty("webdriver.chrome.driver", AppConfig.YANDEX_DRIVER_PATH);
                    System.out.println("Set webdriver.chrome.driver for Yandex: " + AppConfig.YANDEX_DRIVER_PATH);

                } catch (Exception e) {

                    System.err.println("WebDriver setup failed for Yandex path: " + e.getMessage());
                    throw new RuntimeException("Failed to setup WebDriver for Yandex path", e);
                }

                ChromeOptions yandexOptions = new ChromeOptions();
                yandexOptions.addArguments("--remote-allow-origins=*");

                try {

                    driver = new ChromeDriver(yandexOptions);
                    System.out.println("YandexDriver (ChromeDriver with options) started successfully.");
                } catch (Exception e) {
                    System.err.println("Failed to instantiate ChromeDriver for Yandex: " + e.getMessage());

                    System.err.println("Checked Driver Path: " + AppConfig.YANDEX_DRIVER_PATH);

                    throw new RuntimeException(
                            "Could not start YandexDriver. Check driver/binary paths and compatibility.", e);
                }
                break;

            case "chrome":
            default:
                System.out.println("Attempting Chrome WebDriver setup...");
                try {

                    System.setProperty("webdriver.chrome.driver", AppConfig.CHROME_DRIVER_PATH);
                    System.out.println("Set webdriver.chrome.driver for Chrome: " + AppConfig.CHROME_DRIVER_PATH);

                } catch (Exception e) {

                    System.err.println("WebDriver setup failed for Chrome path: " + e.getMessage());
                    throw new RuntimeException("Failed to setup WebDriver for Chrome path", e);
                }
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*");

                try {
                    driver = new ChromeDriver(chromeOptions);
                    System.out.println("ChromeDriver started successfully.");
                } catch (Exception e) {
                    System.err.println("Failed to instantiate ChromeDriver: " + e.getMessage());

                    System.err.println("Checked Driver Path: " + AppConfig.CHROME_DRIVER_PATH);
                    throw new RuntimeException("Could not start ChromeDriver. Check driver path and compatibility.", e);
                }
                break;
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(AppConfig.WAIT_TIMEOUT_SECONDS));
        driver.manage().window().maximize();
        System.out.println("WebDriver configured and maximized.");

        return driver;
    }
}
