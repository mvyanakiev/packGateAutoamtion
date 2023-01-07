package Constants;

import static Constants.ConstantsTests.OS_MAC;
import static Constants.ConstantsTests.OS_WINDOWS;

public class CheckOS {

    public static String execute(){
        String osType = "unknown";
        String type = System.getProperty("os.name").toLowerCase();

        if (type.startsWith("win")) {
            osType = OS_WINDOWS;
        } else if (type.startsWith("mac")) {
            osType = OS_MAC;
        }
        return osType;
    }
}
