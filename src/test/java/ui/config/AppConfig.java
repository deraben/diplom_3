package ui.config;

import io.github.cdimascio.dotenv.Dotenv;

public class AppConfig {
    public static final String BASE_URL = "https://stellarburgers.nomoreparties.site";

    public static final String LOGIN_PAGE_URL = BASE_URL + "/login";
    public static final String REGISTER_PAGE_URL = BASE_URL + "/register";
    public static final String FORGOT_PASSWORD_PAGE_URL = BASE_URL + "/forgot-password";
    public static final String PROFILE_PAGE_URL = BASE_URL + "/account/profile";
    public static final int WAIT_TIMEOUT_SECONDS = 10;

    private static final Dotenv dotenv = Dotenv.configure().load();

    public static String getChromeDriverPath() {
        return dotenv.get("CHROME_DRIVER_PATH");
    }

    public static String getYandexDriverPath() {
        return dotenv.get("YANDEX_DRIVER_PATH");
    }

    public static String getBrowserName() {
        String browserFromProperty = System.getProperty("browser");
        if (browserFromProperty != null && !browserFromProperty.isEmpty()) {
            return browserFromProperty.toLowerCase();
        }
        String browserFromEnv = System.getenv("BROWSER");
        if (browserFromEnv != null && !browserFromEnv.isEmpty()) {
            return browserFromEnv.toLowerCase();
        }
        return "chrome";
    }
}