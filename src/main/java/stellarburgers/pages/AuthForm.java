package stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import stellarburgers.pagecomponents.Header;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class AuthForm {
    private WebDriver driver;

    // Заголовок "Вход"
    private By authHeader = By.xpath(".//h2[text()='Вход']");

    // Поле "Email"
    private By emailInput = By.xpath(".//input[@name='name']");
    // Поле "Пароль"
    private By passwordInput = By.xpath(".//input[@name='Пароль']");

    // Кнопка "Войти"
    private By signInButton = By.xpath(".//button[text()='Войти']");
    // Ссылка "Зарегистрироваться"
    private By signUpLink = By.xpath(".//a[text()='Зарегистрироваться']");
    // Ссылка "Восстановить пароль"
    private By recoverPasswordLink = By.xpath(".//a[text()='Восстановить пароль']");


    public AuthForm(WebDriver driver) {
        this.driver = driver;
    }
    @Step("Wait auth form loaded")
    public void waitAuthFormLoaded() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(authHeader));
    }

    @Step("Input email")
    public void setEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }

    @Step("Input password")
    public void setPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    @Step("Click sign in button")
    public void signInButtonClick() {
        driver.findElement(signInButton).click();
    }

}
