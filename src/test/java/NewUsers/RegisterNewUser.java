package NewUsers;

import Constants.ConstantsTests;
import Constants.SetupTest;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RegisterNewUser {
    static WebDriver driver;

    @BeforeClass
    public static void setupTest() {
        driver = SetupTest.setupDriver();
    }

    @Before
    public void navigateToWebPage() {
        driver.navigate().to(ConstantsTests.URL_TEST);

        if (ConstantsTests.OS_WINDOWS.equals(SetupTest.checkOs()))
            driver.manage().window().maximize();
    }

    @Test
    public void T01_Register() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 2);
        Actions action = new Actions(driver);
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        String userMail = timeStamp + ConstantsTests.NEW_USER_EMAIL;

        driver.findElement(By.linkText("Register")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("saf-label-input")));

        WebElement titleDropDown = driver.findElement(By.xpath("/html/body/saf-page/div[1]/div[1]/saf-content/saf-body/saf-menu/div/div/div/div/form/saf-label-select-input[1]/div/div/div/div/span/span[1]/span/span[2]"));
        Thread.sleep(500);

        action.moveToElement(titleDropDown).click().release().perform();
        Thread.sleep(500);
        action.moveToElement(titleDropDown).sendKeys(Keys.ENTER).perform();

        WebElement acceptCookiesButton = driver.findElement(By.cssSelector("body > saf-page > cookie-container > div > button"));
        acceptCookiesButton.click();

        driver.findElement(By.id("unique5")).sendKeys(timeStamp + ConstantsTests.NEW_USER_FIRST_NAME);
        driver.findElement(By.id("unique6")).sendKeys(ConstantsTests.NEW_USER_LAST_NAME + timeStamp);
        driver.findElement(By.id("unique7")).sendKeys(userMail);
        driver.findElement(By.id("unique8")).sendKeys(ConstantsTests.NEW_USER_PHONE);
        driver.findElement(By.id("unique10")).sendKeys(ConstantsTests.NEW_USER_NOTES);

        System.out.println(userMail);

        WebElement registerButton = driver.findElement(By.xpath("/html//div[@id='saf-menu-page-content-wrapper']//form[@class='form-horizontal simple user-edit']//button[@type='submit']"));
        Thread.sleep(1000);

//        registerButton.click();
        Thread.sleep(2000);

        WebElement buttonOK = driver.findElement(By.cssSelector("body > saf-page > div.saf-page.saf-page-main > saf-confirm > div.modal-confirm.modal.fade.in > div > div > div.modal-footer > a.btn.btn-primary"));
        Thread.sleep(500);
        buttonOK.click();

        //todo approve user
    }


    @AfterClass
    public static void quitDriver() {
        driver.quit();
    }
}
