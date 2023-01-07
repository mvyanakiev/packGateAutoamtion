package Utils;

import Constants.ConstantsTests;
import Constants.SetupTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class LogIn {
    public WebDriver loginAsAdmin(WebDriver driver) {

        driver = goToLogin(driver);
        driver.findElement(By.id("unique1")).sendKeys(ConstantsTests.ADMIN_USER_MAIL);
        driver.findElement(By.id("unique2")).sendKeys(ConstantsTests.PASSWORD);
        driver = logMeIn(driver);

        return driver;
    }

    public WebDriver loginAsUser(WebDriver driver) {

        driver = goToLogin(driver);
        driver.findElement(By.id("unique1")).sendKeys(ConstantsTests.USER_MAIL);
        driver.findElement(By.id("unique2")).sendKeys(ConstantsTests.PASSWORD);
        driver = logMeIn(driver);

        return driver;
    }

    public WebDriver logout(WebDriver driver) throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("user-container")));

        WebElement menu = driver.findElement(By.cssSelector("#menu-user-container-js > svg:nth-child(2)"));

        Actions action = new Actions(driver);
        action.moveToElement(menu).click().release().perform();
        Thread.sleep(1000);

        WebElement logOutLink = driver.findElement(By.linkText("Log out"));
        logOutLink.click();
        Thread.sleep(500);

        driver.switchTo().activeElement();
        driver.findElement(By.linkText("OK")).click();

        return driver;
    }

    private static WebDriver goToLogin(WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver, 2);
        driver.navigate().to(ConstantsTests.URL_TEST);

        if (ConstantsTests.OS_WINDOWS.equals(SetupTest.checkOs())) {
            driver.manage().window().maximize();
        }

        driver.findElement(By.linkText("Login")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("form-control")));
        driver.switchTo().activeElement();

        return driver;
    }

    private static WebDriver logMeIn(WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver, 2);

        driver.findElement(By.xpath("//saf-page/div[@class='saf-page saf-page-main']/saf-modal-dialog[1]/div[@role='dialog']/div[@class='modal-dialog modal-lg']//div[@class='modal-footer']/div[2]/a[1]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("user-name-inner-container")));

        return driver;
    }
}
