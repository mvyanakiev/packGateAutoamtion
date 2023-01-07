package Constants;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SetupTest {

    public static String checkOs(){
        String osType = "unknown";
        String type = System.getProperty("os.name").toLowerCase();

        if (type.startsWith("win")) {
            osType = ConstantsTests.OS_WINDOWS;
        } else if (type.startsWith("mac")) {
            osType = ConstantsTests.OS_MAC;
        }
        return osType;
    }

    public static WebDriver setupDriver() {
        if (ConstantsTests.OS_WINDOWS.equals(CheckOS.execute())) {
            System.setProperty("webdriver.chrome.driver", ConstantsTests.CHROMEDRIVER_PATH);
        }
        return new ChromeDriver();
    }
}
