package stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class RegistrationForm {
    private WebDriver driver;

    // Заголовок "Регистрация"
    private By registrationHeader = By.xpath(".//h2[text()='Регистрация']");
    // Поле "Имя"
    private By nameInput = By.xpath(".//*[text()='Имя']/following-sibling::input");
    // Поле "Email"
    private By emailInput = By.xpath(".//*[text()='Email']/following-sibling::input");
    // Поле "Пароль"
    private By passwordInput = By.xpath(".//*[text()='Пароль']/following-sibling::input");
    // Ошибка "Некорректный пароль"
    private By incorrectPasswordError = By.xpath(".//*[text()='Некорректный пароль']");

    // Кнопка "Зарегистрироваться"
    private By signUpButton = By.xpath(".//button[text()='Зарегистрироваться']");
    // Ссылка "Войти"
    private By signInLink = By.xpath(".//a[text()='Войти']");

    public RegistrationForm(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Wait registration form loaded")
    public void waitRegistrationFormLoaded() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(registrationHeader));
    }

    @Step("Scroll to name field in registration form")
    public void scrollRegistrationFormToNameField() {
        WebElement element = driver.findElement(nameInput);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    @Step("Scroll to password field in registration form")
    public void scrollRegistrationFormToPasswordField() {
        WebElement element = driver.findElement(passwordInput);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    @Step("Input user name")
    public void setName(String username) {
        driver.findElement(nameInput).sendKeys(username);
    }

    @Step("Input email")
    public void setEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }

    @Step("Input password")
    public void setPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    @Step("Click sign up button")
    public void signUpButtonClick() {
        driver.findElement(signUpButton).click();
    }

    @Step("Check incorrect password error")
    public void checkIncorrectPasswordErrorIsDisplayed() {
        assertTrue(driver.findElement(incorrectPasswordError).isDisplayed());
    }

    @Step("Scroll to signIn link in registration form")
    public void scrollRegistrationFormToSignInLink() {
        WebElement element = driver.findElement(signInLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    @Step("Click sign in link")
    public void signInLinkClick() {
        driver.findElement(signInLink).click();
    }
}