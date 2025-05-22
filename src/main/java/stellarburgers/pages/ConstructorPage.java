package stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ConstructorPage {
    private WebDriver driver;

    // Раздел "Булки"
    private By bunSection = By.xpath(".//section[starts-with(@class,'BurgerIngredients_ingredients')]//span[text()='Булки']");
    // Раздел "Соусы"
    private By sauceSection = By.xpath(".//section[starts-with(@class,'BurgerIngredients_ingredients')]//span[text()='Соусы']");
    // Раздел "Начинки"
    private By fillingSection = By.xpath(".//section[starts-with(@class,'BurgerIngredients_ingredients')]//span[text()='Начинки']");
    // Секция "Начинки" в окне конструктора
    private By fillingSectionInConstructorWindow = By.xpath(".//h2[text()='Начинки']");
    // Кнопка "Войти в аккаунт"
    private By signInButton = By.xpath(".//button[text()='Войти в аккаунт']");
    // Вкладка Начинки
    private By fillingTab = By.cssSelector(".tab_tab__1SPyG:nth-child(3)");
    // Вкладка Соусы
    private By saucesTab = By.cssSelector(".tab_tab__1SPyG:nth-child(2)");
    // Вкладка Булки
    private By bunsTab = By.cssSelector(".tab_tab__1SPyG:nth-child(1)");


    public ConstructorPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Wait Constructor Page loaded")
    public void waitConstructorPageLoaded() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.numberOfElementsToBeMoreThan(
                        By.cssSelector("a[class^='BurgerIngredient']"), 2));
    }

    @Step("Click sign in button from Constructor Page")
    public void signInButtonClick() {
        driver.findElement(signInButton).click();
    }

    @Step("Scroll to filling tab")
    public void scrollConstructorToFillings() {
        WebElement element = driver.findElement(fillingSectionInConstructorWindow);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }

    @Step("Click Buns tab")
    public void bunsTabClick() {
        driver.findElement(bunSection).click();
    }

    @Step("Click Sauces tab")
    public void saucesTabClick() {
        driver.findElement(sauceSection).click();
    }

    @Step("Click Fillings tab")
    public void fillingsTabClick() {
        driver.findElement(fillingSection).click();
    }

    @Step("Wait filling tab loaded")
    public void waitFillingsTabLoaded() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.attributeContains(fillingTab, "class", "current"));
    }

    @Step("Wait sauces tab loaded")
    public void waitSaucesTabLoaded() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.attributeContains(saucesTab, "class", "current"));
    }

    @Step("Wait buns tab loaded")
    public void waitBunsTabLoaded() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.attributeContains(bunsTab, "class", "current"));
    }

}
