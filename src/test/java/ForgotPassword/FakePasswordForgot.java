package ForgotPassword;

import Bases.BaseTest;
import Constants.ConstantsTests;
import Pages.LoginPage;
import org.junit.Test;

public class FakePasswordForgot extends BaseTest {

    @Test
    public void T01_FakePassword() {
        LoginPage page = new LoginPage(driver);

         page
                .goToHome()
                .goToLoginPage()
                .goToActive()
                .click(page.forgotPassLink);

        page.waitVisibility(page.submitButton);
        page.writeText(page.emailField, ConstantsTests.WRONG_USER_MAIL);
        page.click(page.submitButton);

        page.verifyErrorMessage("No user found with the given email");
    }
}