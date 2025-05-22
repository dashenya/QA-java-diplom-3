package stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ForgotPasswordForm {
    private WebDriver driver;

    // Заголовок "Восстановление пароля"
    private By forgotPasswordHeader = By.xpath(".//h2[text()='Восстановление пароля']");

    // Ссылка "Восстановить пароль"
    private By signInLink = By.xpath(".//a[text()='Войти']");


    public ForgotPasswordForm(WebDriver driver) {
        this.driver = driver;
    }
    @Step("Wait forgot password form loaded")
    public void waitForgotPasswordFormLoaded() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(forgotPasswordHeader));
    }

    @Step("Click sign in link")
    public void signInLinkClick() {
        driver.findElement(signInLink).click();
    }
}
