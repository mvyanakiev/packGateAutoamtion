package Pages;

import Bases.BasePage;
import Constants.ConstantsTests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public By submitButton = By.xpath("/html/body/saf-page/div[1]/saf-modal-dialog[2]/div[1]/div/div/div[3]/input[1]");
    public By forgotPassLink = By.linkText("Forgotten Password?");
    public By emailField = By.id("unique3");
    public By errorMessage = By.xpath("/html/body/saf-page/div[1]/saf-modal-dialog[2]/div[1]/div/div/div[2]/saf-form-info/div/div/div");
    public By loginLink = By.linkText("Login");

    public LoginPage goToHome() {
        driver.get(ConstantsTests.URL_TEST);
        return this;
    }

    public LoginPage goToLoginPage() {
        click(loginLink);
        return new LoginPage(driver);
    }

    public LoginPage verifyErrorMessage(String expectedText) {
        assertEquals(errorMessage, expectedText);
        return this;
    }
}

