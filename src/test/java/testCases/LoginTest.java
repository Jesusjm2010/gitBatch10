package testCases;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.DashBoardPage;
import pages.LoginPage;
import utils.CommonMethods;
import utils.ConfigReader;

public class LoginTest extends CommonMethods {
    public LoginTest() {
    }

    @Test(groups = {"regression"})
    public void adminLogin() {
        LoginPage login = new LoginPage();
        sendText(login.usernameBox, ConfigReader.getPropertyValue("username"));
        sendText(login.passwordBox, ConfigReader.getPropertyValue("password"));
        click(login.loginBtn);

        //assertion
        DashBoardPage dashBoardPage = new DashBoardPage();
        Assert.assertTrue(dashBoardPage.welcomeMessage.isDisplayed());
    }

    @DataProvider
    public Object[][] invalidData() {
        Object[][] data = new Object[][]{{"James", "123!", "Invalid credentials"}, {"Admin1", "Hum@nhrm123", "Invalid credentials"}, {"Admin", "", "Password cannot be empty"}, {"", "Hum@nhrm123", "Username cannot be empty"}};
        return data;
    }

    @Test(dataProvider = "invalidData",groups = "smoke")
    public void invalidLoginErrorMessageValidation(String username, String password, String message) {
        LoginPage loginPage = new LoginPage();
        loginPage.login(username, password);
        String actualError = loginPage.errorMessage.getText();
        Assert.assertEquals(actualError, message, "Error message does not match");
    }
}
