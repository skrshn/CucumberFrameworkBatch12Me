package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.CommonMethods;
import utils.ConfigReader;


public class EmployeeSearchSteps extends CommonMethods {


    @Given("user is navigated to HRMS Application")
    public void user_is_navigated_to_hrms_application() {
        //openBrowserAndLaunchApplication();
    }

    @When("user enters valid admin credentials")
    public void user_enters_valid_admin_credentials() {
        sendText(loginPage.usernameField, ConfigReader.getPropertyValue("username"));
        sendText(loginPage.passwordField, ConfigReader.getPropertyValue("password"));

    }

    @When("user clicks on login button")
    public void user_clicks_on_login_button() {

        click(loginPage.loginButton);
    }

    @When("user navigates to employee list page")
    public void user_navigates_to_employee_list_page() {
        click(emSearchPage.pimOption);
        click(emSearchPage.empListOption);
    }

    @When("user enter valid employee id")
    public void user_enter_valid_employee_id() {
        sendText(emSearchPage.employeeIDField, "8510142");
    }

    @When("user enter valid employee name")
    public void userEnterValidEmployeeName() {
        sendText(emSearchPage.searchName, "zubair");
    }

    @When("clicks on search button")
    public void clicks_on_search_button() {
        click(emSearchPage.searchButton);
    }

    @Then("user is able to see employee information")
    public void user_is_able_to_see_employee_information() {
        System.out.println("Result displayed");
        //tearDown();
    }
}
