package stellarburgers.pagecomponents;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Header {
    private WebDriver driver;

    // Раздел "Конструктор"
    private By constructorSection = By.xpath(".//p[text()='Конструктор']");
    // Раздел "Лента Заказов"
    private By orderFeedSection = By.xpath(".//p[text()='Лента Заказов']");

    // Лого
    private By logo = By.xpath(".//*[starts-with(@class,'AppHeader_header__logo')]");

    // Личный кабинет
    private By myAccount = By.xpath(".//p[text()='Личный Кабинет']");

    public Header(WebDriver driver) {
        this.driver = driver;
    }
    @Step("Wait auth form loaded")
    public void waitLogoLoaded() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(logo));
    }

    @Step("Click constructor button from Header")
    public void constructorButtonClick() {
        driver.findElement(constructorSection).click();
    }

    @Step("Click sign in button from Header")
    public void signInButtonClick() {
        driver.findElement(myAccount).click();
    }

    @Step("Click logo in Header")
    public void logoButtonClick() {
        driver.findElement(logo).click();
    }

}
