import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import stellarburgers.helpingMethods.HelpingMethods;
import stellarburgers.pagecomponents.Header;
import stellarburgers.pages.AccountPage;
import stellarburgers.pages.AuthForm;
import stellarburgers.pages.ConstructorPage;

import static stellarburgers.rules.WebDriverCreator.createWebDriver;

/*
Проверки:
- переход в личный кабинет
- переход из личного кабинета в конструктор
- выход из личного кабинета
*/

public class MyAccountTest {
    private WebDriver driver;
    private ConstructorPage constructorPage;
    private AccountPage accountPage;
    private Header header;
    private String accessToken;

    @Before
    public void setUp() {

        driver = createWebDriver();
        header = new Header(driver);

        String email = HelpingMethods.generatingRandomEmail();
        String userName = "userName";
        String password = "Qwer1234";

        HelpingMethods.createUser(email, password, userName);
        HelpingMethods.login(driver, email, password);

        accessToken = HelpingMethods.fetchAuthTokenFromLocalStorage(driver);

        header.signInButtonClick();

        accountPage = new AccountPage(driver);
        accountPage.checkAccountPageLoaded();

    }

    @Test
    public void checkGoToConstructorFromMyAccountByLogoButtonInHeader() {

        header.logoButtonClick();

        constructorPage = new ConstructorPage(driver);
        constructorPage.waitConstructorPageLoaded();

    }

    @Test
    public void checkGoToConstructorFromMyAccountByConstructorButtonInHeader() {

        header.constructorButtonClick();

        constructorPage = new ConstructorPage(driver);
        constructorPage.waitConstructorPageLoaded();

    }

    @Test
    public void checkLogOutFromMyAccount() {

        accountPage.logOutButtonClick();

        AuthForm authForm = new AuthForm(driver);
        authForm.waitAuthFormLoaded();


    }

    @After
    public void teardown() {
        // Закрыть браузер
        driver.quit();
        // Удалить тестового пользователя
        HelpingMethods.deleteUser(accessToken);
    }
}
