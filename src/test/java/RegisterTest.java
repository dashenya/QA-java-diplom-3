import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import stellarburgers.helpingMethods.HelpingMethods;
import stellarburgers.pagecomponents.Header;
import stellarburgers.pages.AuthForm;
import stellarburgers.pages.RegistrationForm;

import static stellarburgers.rules.WebDriverCreator.createWebDriver;

/*
Проверки:
- успешная регистрация
- Ошибка для некорректного пароля. Минимальный пароль — 6 символов
*/

public class RegisterTest {
    private WebDriver driver;
    private String email;
    private String userName;
    private RegistrationForm registrationForm;
    private static final String BASE_URL = "https://stellarburgers.nomoreparties.site/register";

    @Before
    public void setUp() {

        driver = createWebDriver();
        driver.get(BASE_URL);

        email = HelpingMethods.generatingRandomEmail();
        userName = "userName";

        registrationForm = new RegistrationForm(driver);

    }

    @Test
    public void checkRegistrationWithCorrectFields() {

        String password = "Qwer12";

        registrationForm.waitRegistrationFormLoaded();
        registrationForm.scrollRegistrationFormToNameField();

        registrationForm.setEmail(email);
        registrationForm.setName(userName);
        registrationForm.setPassword(password);

        registrationForm.signUpButtonClick();

        // создаем объект класса формы авторизации
        AuthForm authForm = new AuthForm(driver);

        authForm.waitAuthFormLoaded();
        authForm.setEmail(email);
        authForm.setPassword(password);
        authForm.signInButtonClick();

        // создаем объект класса header
        Header header = new Header(driver);

        header.waitLogoLoaded();

        String accessToken = HelpingMethods.fetchAuthTokenFromLocalStorage(driver);
        System.out.println(accessToken);
        HelpingMethods.deleteUser(accessToken);

    }

    @Test
    public void checkRegistrationWithIncorrectPassword() throws InterruptedException {

        // некорректный пароль длиною меньше 6 символов
        String password = "Qwer1";

        registrationForm.waitRegistrationFormLoaded();
        registrationForm.scrollRegistrationFormToNameField();

        registrationForm.setEmail(email);
        registrationForm.setName(userName);
        registrationForm.setPassword(password);

        registrationForm.signUpButtonClick();
        registrationForm.checkIncorrectPasswordErrorIsDisplayed();

    }

    @After
    public void teardown() {
        // Закрыть браузер
        driver.quit();
    }
}