package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import utils.CommonMethods;
import utils.ConfigReader;

public class LoginSteps extends CommonMethods {
    @Then("admin user is successfully logged in")
    public void adminUserIsSuccessfullyLoggedIn() {
        Assert.assertTrue(dashboardPage.welcomeMessage.isDisplayed());
        //tearDown();
    }

    @When("user enters valid ess username and password")
    public void userEntersValidEssUsernameAndPassword() {
        sendText(loginPage.usernameField, ConfigReader.getPropertyValue("essUsername"));
        sendText(loginPage.passwordField, ConfigReader.getPropertyValue("essPassword"));
    }

    @Then("ess user is successfully logged in")
    public void essUserIsSuccessfullyLoggedIn() {
        Assert.assertTrue(dashboardPage.welcomeMessage.isDisplayed());
        //tearDown();
    }

    @When("user enters invalid username and password")
    public void userEntersInvalidUsernameAndPassword() {
        sendText(loginPage.usernameField, ConfigReader.getPropertyValue("wrongUsername"));
        sendText(loginPage.passwordField, ConfigReader.getPropertyValue("wrongPassword"));
    }

    @Then("user see invalid login error message on the screen")
    public void userSeeInvalidLoginErrorMessageOnTheScreen() {
        Assert.assertTrue(loginPage.invalidCredentialsErrorMessage.isDisplayed());
    }

    @When("user enters empty username and a password")
    public void userEntersEmptyUsernameAndAPassword() {
        sendText(loginPage.usernameField, ConfigReader.getPropertyValue("emptyUsername"));
        sendText(loginPage.passwordField, ConfigReader.getPropertyValue("password"));
    }

    @Then("user see username cannot be empty error message on the screen")
    public void userSeeUsernameCannotBeEmptyErrorMessageOnTheScreen() {
        Assert.assertTrue(loginPage.userNameCannotBeEmptyErrorMessage.isDisplayed());

    }

    @When("user enters a username and empty password")
    public void userEntersAUsernameAndEmptyPassword() {
        sendText(loginPage.usernameField, ConfigReader.getPropertyValue("username"));
        sendText(loginPage.passwordField, ConfigReader.getPropertyValue("emptyPassword"));
    }

    @Then("user see password cannot be empty error message on the screen")
    public void userSeePasswordCannotBeEmptyErrorMessageOnTheScreen() {
        Assert.assertTrue(loginPage.passwordCannotBeEmptyErrorMessage.isDisplayed());
    }
}
