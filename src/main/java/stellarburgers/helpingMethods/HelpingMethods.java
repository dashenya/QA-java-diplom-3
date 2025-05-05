package stellarburgers.helpingMethods;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.hc.core5.http.HttpStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.remote.html5.RemoteWebStorage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import stellarburgers.pagecomponents.Header;
import stellarburgers.pages.AuthForm;

import java.time.Duration;

import static io.restassured.RestAssured.given;

public class HelpingMethods {
    public static String generatingRandomEmail() {

        int length = 20;
        boolean useLetters = true;
        boolean useNumbers = true;
        String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
        return generatedString + "@yandex.ru";

    }

    @Step("Fetch Authorization token from local storage")
    public static String fetchAuthTokenFromLocalStorage(WebDriver driver) {

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.numberOfElementsToBeMoreThan(
                        By.cssSelector("a[class^='BurgerIngredient']"), 2));
        LocalStorage localStorage = ((WebStorage) driver).getLocalStorage();
        String accessToken = localStorage.getItem("accessToken");
        return accessToken;

    }

    @Step("Create test user")
    public static void createUser(String email, String password, String name) {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
        String json = "{\"email\": \"" + email + "\", \"password\": \"" + password + "\", \"name\": \"" + name + "\"}";
        given().log().all()
                .contentType(ContentType.JSON)
                .and()
                .body(json)
                .when()
                .post("/api/auth/register");
    }

    @Step("Delete test user")
    public static void deleteUser(String accessToken) {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
        given()
                .contentType(ContentType.JSON)
                .header("Authorization", accessToken)
                .when()
                .delete("/api/auth/user")
                .then().log().all()
                .assertThat()
                .statusCode(HttpStatus.SC_ACCEPTED);
    }

    public static void login(WebDriver driver, String email, String password) {
        driver.get("https://stellarburgers.nomoreparties.site/login");

        // создаем объект класса формы авторизации
        AuthForm authForm = new AuthForm(driver);

        authForm.waitAuthFormLoaded();
        authForm.setEmail(email);
        authForm.setPassword(password);
        authForm.signInButtonClick();

        // создаем объект класса header
        Header header = new Header(driver);
        header.waitLogoLoaded();

    }
}
