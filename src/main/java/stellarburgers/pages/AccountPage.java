package stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class AccountPage {
    private WebDriver driver;

    // Кнопка "Профиль"
    private By profileButton = By.xpath(".//a[text()='Профиль']");
    // Кнопка "Выход"
    private By exitButton = By.xpath(".//button[text()='Выход']");

    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Wait account page loaded")
    public void checkAccountPageLoaded() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(profileButton));
    }

    @Step("Click logout button")
    public void logOutButtonClick() {
        driver.findElement(exitButton).click();
    }
}
