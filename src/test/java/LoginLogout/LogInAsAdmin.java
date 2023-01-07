package LoginLogout;
import Constants.SetupTest;
import Utils.LogIn;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertNotNull;

public class LogInAsAdmin extends LogIn {
    static WebDriver driver;

    @BeforeClass
    public static void setupTest() {
        driver = SetupTest.setupDriver();
    }

    @Test
    public void T01_loginAsAdmin() throws InterruptedException {
        driver = super.loginAsAdmin(driver);
        WebElement hasOrdersLink = null;
        driver.switchTo().activeElement();

        try {
            hasOrdersLink = driver.findElement(By.xpath("//*[@id=\"top-menu\"]/ul/li[5]/a"));
        } catch (NoSuchElementException e) {
        }
        assertNotNull(hasOrdersLink);



        driver = super.logout(driver);
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("login-link")));

        WebElement loginLink = null;
        try {
            loginLink = driver.findElement(By.className("login-link"));
        } catch (NoSuchElementException e) {
        }
        assertNotNull(loginLink);
    }

    @AfterClass
    public static void quitDriver() {
        driver.quit();
    }
}
