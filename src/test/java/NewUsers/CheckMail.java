package NewUsers;

import Constants.ConstantsTests;
import Constants.SetupTest;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CheckMail {
    static WebDriver driver;

    @BeforeClass
    public static void setupTest() {
        driver = SetupTest.setupDriver();
    }

    @Before
    public void navigateToWebPage() {
        if (ConstantsTests.OS_WINDOWS.equals(SetupTest.checkOs()))
            driver.manage().window().maximize();
    }


//todo -> to work with user e-mail
    @Test
    public void checkWelcomeMail(String userMail) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 2);

        driver.navigate().to("https://www.mailinator.com/");

        driver.findElement(By.id("inboxfield")).sendKeys(userMail);

        driver.findElement(By.xpath("/html/body/section[1]/div/div[3]/div[2]/div[2]/div[1]/span/button")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ng-binding")));
        Thread.sleep(2000);

        // fixme -> will not work with any mail
        WebElement sender = driver.findElement(By.xpath("//*[@id=\"row_20190108_131432_automation-1546946095-18963034\"]/td[3]"));

        assertThat("No confirm mail for pending approval", sender.getText(), is(ConstantsTests.SENDER_CONFIRM_MAIL));
    }

    @AfterClass
    public static void quitDriver() {
        driver.quit();
    }
}
