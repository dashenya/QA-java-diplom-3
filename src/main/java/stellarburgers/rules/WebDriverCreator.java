package stellarburgers.rules;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverCreator {

    public static WebDriver createWebDriver() {

        String browser = System.getProperty("browser");
        if (browser == null) {
            return initChrome();
        }
        if (browser.equals("yandex")) {
            return initYandex();
        }
        return initChrome();
    }

    private static WebDriver initChrome() {
        ChromeOptions options = new ChromeOptions();
        return new ChromeDriver(options);
    }

    private static WebDriver initYandex() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\reisk\\IdeaProjects\\drivers\\yandexdriver.exe");
        ChromeOptions options = new ChromeOptions();
        return new ChromeDriver(options);
    }
}
