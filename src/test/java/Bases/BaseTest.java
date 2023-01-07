package Bases;

import Constants.CheckOS;
import Constants.ConstantsTests;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public abstract class BaseTest {
    public static WebDriver driver;

    @BeforeClass
    public static void setup() {

        if (ConstantsTests.OS_WINDOWS.equals(CheckOS.execute())) {
            System.setProperty("webdriver.chrome.driver", ConstantsTests.CHROMEDRIVER_PATH);
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        } else {
            driver = new ChromeDriver();
        }
    }

    @AfterClass
    public static void teardown() {
        driver.quit();
    }
}
