package ui.config;

public class AppConfig {
    public static final String BASE_URL = "https://stellarburgers.nomoreparties.site";

    public static final String LOGIN_PAGE_URL = BASE_URL + "/login";
    public static final String REGISTER_PAGE_URL = BASE_URL + "/register";
    public static final String FORGOT_PASSWORD_PAGE_URL = BASE_URL + "/forgot-password";
    public static final String PROFILE_PAGE_URL = BASE_URL + "/account/profile";

    public static final String BASE_API_URL = "https://stellarburgers.nomoreparties.site/api";
    public static final String AUTH_REGISTER_ENDPOINT = "/auth/register";
    public static final String AUTH_LOGIN_ENDPOINT = "/auth/login";
    public static final String AUTH_USER_ENDPOINT = "/auth/user";
    public static final int WAIT_TIMEOUT_SECONDS = 10;

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

    public static final String CHROME_DRIVER_PATH = "E:\\Programs\\ChromeDriver\\chromedriver-win64\\chromedriver.exe";

    public static final String YANDEX_DRIVER_PATH = "E:\\Programs\\YandexDriver-25.2.6-stable\\yandexdriver.exe";
}