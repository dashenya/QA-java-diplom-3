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
    private ConstructorPage constructorPage;

    @Before
    public void setUp() {
        driver = createWebDriver();
        driver.get(BASE_URL);
        constructorPage = new ConstructorPage(driver);
        constructorPage.waitConstructorPageLoaded();
    }

    @Test
    public void checkFillingsTab() {

        constructorPage.fillingsTabClick();
        constructorPage.waitFillingsTabLoaded();

    }

    @Test
    public void checkSaucesTab() {

        constructorPage.saucesTabClick();
        constructorPage.waitSaucesTabLoaded();

    }

    @Test
    public void checkBunsTab() {

        constructorPage.scrollConstructorToFillings();

        constructorPage.bunsTabClick();
        constructorPage.waitBunsTabLoaded();

    }

    @After
    public void teardown() {
        driver.quit();
    }
}
