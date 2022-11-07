package TestClasses;

import Pages.SignInPage;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class SignInTests extends BaseTest {

    String email = "testemail10@test.com";
    String prenume = "Test";
    String nume = "Test";
    String parola = "testpass00";
    String afterSignInURL = "user/details";

    @Test
    public void testSignIn() {
        signInPage = new SignInPage(driver);
        signInPage.open();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        signInPage.signInWith(email, prenume, nume, parola);
        driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
        Assert.assertTrue(driver.getCurrentUrl().contains(afterSignInURL));
    }

    @Test
    @Ignore
    public void open() {
        signInPage = new SignInPage(driver);
        signInPage.open();
        Assert.assertTrue(signInPage.isOpen());
    }
}
