package ForgotPassword;

import Bases.BaseTest;
import Constants.ConstantsTests;
import Pages.LoginPage;
import org.junit.Test;

public class RealPasswordForgot extends BaseTest {

    @Test
    public void T01_RealPassword() {
        LoginPage page = new LoginPage(driver);

        page
                .goToHome()
                .goToLoginPage()
                .goToActive()
                .click(page.forgotPassLink);

        page.waitVisibility(page.submitButton);
        page.writeText(page.emailField, ConstantsTests.USER_MAIL);
        page.click(page.submitButton);

        page.verifyErrorMessage("Request finished. Check your email.");
    }

    //todo check gmail via api?
}
