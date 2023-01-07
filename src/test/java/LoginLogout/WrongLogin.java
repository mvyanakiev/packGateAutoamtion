package LoginLogout;

import Constants.ConstantsTests;
import Constants.SetupTest;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class WrongLogin {
    static WebDriver driver;

    private static void setupTest() {
        driver = SetupTest.setupDriver();
        driver.navigate().to(ConstantsTests.URL_TEST);
        if (ConstantsTests.OS_WINDOWS.equals(SetupTest.checkOs()))
            driver.manage().window().maximize();
    }

    private static String logUser(String user, String password) throws InterruptedException {

        setupTest();

        driver.findElement(By.linkText("Login")).click();
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("form-control")));
        driver.switchTo().activeElement();

        driver.findElement(By.id("unique1")).sendKeys(user);
        driver.findElement(By.id("unique2")).sendKeys(password);

        driver.findElement(By.xpath("//saf-page/div[@class='saf-page saf-page-main']/saf-modal-dialog[1]/div[@role='dialog']/div[@class='modal-dialog modal-lg']//div[@class='modal-footer']/div[2]/a[1]")).click();

        WebElement errorMessage = null;
        driver.switchTo().activeElement();
        Thread.sleep(500);

        try {
            errorMessage = driver.findElement(By.xpath("/html/body/saf-page/div[1]/saf-modal-dialog[1]/div[1]/div/div/div[2]/saf-form-info/div/div/div"));
        } catch (NoSuchElementException e) {
        }

        if (errorMessage.getText() == null) {
            return "Something is wrong";
        }

        return errorMessage.getText();


    }


    @Test
    public void T01_WrongPassword() throws InterruptedException {
        String message = logUser(ConstantsTests.ADMIN_USER_MAIL, ConstantsTests.WRONG_PASSWORD);

        assertThat("No error message", message, is("Invalid password or email"));
    }


    @Test
    public void T02_WrongUser() throws InterruptedException {
        String message = logUser(ConstantsTests.WRONG_USER_MAIL, ConstantsTests.PASSWORD);

        assertThat("No error message", message, is("Invalid password or email"));
    }


    @Test
    public void T03_WrongUserAndPassword() throws InterruptedException {
        String message = logUser(ConstantsTests.WRONG_USER_MAIL, ConstantsTests.WRONG_PASSWORD);

        assertThat("No error message", message, is("Invalid password or email"));
    }


    @After
    public void closeBrowser() {
        driver.quit();
    }
}
