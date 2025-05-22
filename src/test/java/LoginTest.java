import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import stellarburgers.helpingMethods.HelpingMethods;
import stellarburgers.pagecomponents.Header;
import stellarburgers.pages.AuthForm;
import stellarburgers.pages.ConstructorPage;
import stellarburgers.pages.ForgotPasswordForm;
import stellarburgers.pages.RegistrationForm;

import static stellarburgers.rules.WebDriverCreator.createWebDriver;

/*
Проверки:
- вход по кнопке «Войти в аккаунт» на главной,
- вход через кнопку «Личный кабинет»,
- вход через кнопку в форме регистрации,
- вход через кнопку в форме восстановления пароля.
*/

public class LoginTest {
    private WebDriver driver;
    private String email;
    private String userName;
    private String password;
    private ConstructorPage constructorPage;
    private Header header;


    @Before
    public void setUp() {

        driver = createWebDriver();

        email = HelpingMethods.generatingRandomEmail();
        userName = "userName";
        password = "Qwer1234";

        constructorPage = new ConstructorPage(driver);
        header = new Header(driver);

    }

    @Test
    public void SignInFromButtonInConstructorPage() throws InterruptedException {

        driver.get("https://stellarburgers.nomoreparties.site/");

        HelpingMethods.createUser(email, password, userName);

        constructorPage.signInButtonClick();

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
    public void SignInFromMyAccountButtonInHeader() throws InterruptedException {

        driver.get("https://stellarburgers.nomoreparties.site/");

        HelpingMethods.createUser(email, password, userName);

        header.signInButtonClick();

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
    public void SignInFromLinkInRegistrationForm() throws InterruptedException {

        driver.get("https://stellarburgers.nomoreparties.site/register");

        HelpingMethods.createUser(email, password, userName);

        RegistrationForm registrationForm = new RegistrationForm(driver);

        registrationForm.waitRegistrationFormLoaded();
        registrationForm.scrollRegistrationFormToSignInLink();
        registrationForm.signInLinkClick();

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
    public void SignInFromButtonInForgotPasswordForm() {

        driver.get("https://stellarburgers.nomoreparties.site/forgot-password");

        HelpingMethods.createUser(email, password, userName);

        ForgotPasswordForm forgotPasswordForm = new ForgotPasswordForm(driver);

        forgotPasswordForm.waitForgotPasswordFormLoaded();
        forgotPasswordForm.signInLinkClick();

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

    @After
    public void teardown() {
        // Закрыть браузер
        driver.quit();
    }
}