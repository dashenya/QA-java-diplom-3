import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import stellarburgers.pages.ConstructorPage;

import static stellarburgers.rules.WebDriverCreator.createWebDriver;

/*
Проверка, что работают переходы к разделам «Булки», «Соусы», «Начинки»
*/

public class ConstructorTest {

    private static final String BASE_URL = "https://stellarburgers.nomoreparties.site/";
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = createWebDriver();
    }

    @Test
    public void checkBurgerTabs() {
        driver.get(BASE_URL);

        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.waitConstructorPageLoaded();

        constructorPage.fillingsTabClick();
        constructorPage.waitFillingsTabLoaded();

        constructorPage.saucesTabClick();
        constructorPage.waitSaucesTabLoaded();

        constructorPage.bunsTabClick();
        constructorPage.waitBunsTabLoaded();

    }

    @After
    public void teardown() {
        driver.quit();
    }
}
