package testClasses;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.NavigationPage;
import testClasses.ObjectModels.SignInModel;
import utils.GenericUtils;
import utils.SeleniumUtils;
import utils.WaitUtils;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class SignInTests extends BaseTest {


    @DataProvider(name = "negativeSignInDP")
    public Iterator<Object[]> SQLDpCollection() throws SQLException {
        Collection<Object[]> dataProvider = new ArrayList<>();

            Connection connection = DriverManager.getConnection("jdbc:mysql://" + dbHostname + ":" + dbPort
                    + "/" + dbSchema, dbUser, dbPassword);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM signin_negative");

            while (resultSet.next()) {
                SignInModel signInModel = new SignInModel(
                        resultSet.getString("email"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("password"),
                        resultSet.getString("confirmPassword"),
                        resultSet.getString("emailError"),
                        resultSet.getString("firstNameError"),
                        resultSet.getString("lastNameError"),
                        resultSet.getString("passwordError"),
                        resultSet.getString("confirmPasswordError"));
                dataProvider.add(new Object[] {signInModel});
            }

        return dataProvider.iterator();

    }

    @Test (groups = {"smoke"})
    public void positiveSignInTest() {
        String email = GenericUtils.createRandomString(7) + "@gmail.com";
        String firstName = GenericUtils.createRandomString(6);
        String lastName = GenericUtils.createRandomString(10);
        String password = GenericUtils.createRandomString(10);

        navigationPage = new NavigationPage(driver);
        signInPage = navigationPage.navigateToSignIn();
        Assert.assertEquals(signInPage.getPageTitle(), "Creează cont | Books Express");

        signInPage.signInWith(email, lastName, firstName, password, password);
        WaitUtils.waitForUrlToContain(driver, "/user/details", 5);
        Assert.assertEquals(SeleniumUtils.getCurrentPageTitle(driver), "Cont - Detalii personale - Books Express | Books Express");

        loginPage = navigationPage.navigateToLogin();
        loginPage.logout();

        navigationPage.navigateToSignIn();
        signInPage.signInWith(email, lastName, firstName, password, password);
        Assert.assertEquals(signInPage.getRegisterFailedError(), "Această adresă " +
                "este asociată unui cont. Dacă ai uitat parola o poți reseta din pagina de intrare în cont.");
    }


    @Test (dataProvider = "negativeSignInDP")
    public void negativeSignInTest(SignInModel signInModel) {
        navigationPage = new NavigationPage(driver);
        signInPage = navigationPage.navigateToSignIn();
        Assert.assertEquals(signInPage.getPageTitle(), "Creează cont | Books Express");
        signInPage.signInWith(signInModel.getEmail(), signInModel.getFirstName(), signInModel.getLastName(), signInModel.getPassword(),
                signInModel.getConfirmPassword());
        Assert.assertTrue(signInPage.checkError(signInModel.getEmailError(), "emailError"));
        Assert.assertTrue(signInPage.checkError(signInModel.getFirstNameError(), "firstNameError"));
        Assert.assertTrue(signInPage.checkError(signInModel.getLastNameError(), "lastNameError"));
        Assert.assertTrue(signInPage.checkError(signInModel.getPasswordError(), "passwordError"));
        Assert.assertTrue(signInPage.checkError(signInModel.getConfirmPasswordError(), "confirmPasswordError"));
        signInPage.goBackToHomePage();
    }

}
