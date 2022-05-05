package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import utils.CommonMethods;
import utils.ConfigReader;
import utils.Constants;
import utils.ExcelReader;

import java.io.FileInputStream;
import java.util.*;

public class EmployeeAddSteps extends CommonMethods {
    @When("user clicks on PIM option")
    public void userClicksOnPIMOption() {
        click(emSearchPage.pimOption);
    }

    @And("user navigates to add employee option")
    public void userNavigatesToAddEmployeeOption() {
        click(emSearchPage.addEmpOption);
    }

    @When("user enters firstname middlename and lastname")
    public void userEntersFirstnameMiddlenameAndLastname() {
        sendText(emAddPage.firstName, "sakir");
        sendText(emAddPage.middleName, "sako");
        sendText(emAddPage.lastName, "sahin");
    }

    @And("user clicks on save button")
    public void userClicksOnSaveButton() {
        click(emAddPage.saveButton);
    }

    @Then("user is able to add employee successfully")
    public void userIsAbleToAddEmployeeSuccessfully() {
        System.out.println("Employee Added Successfully");
        // tearDown();
    }

    @When("user enters {string} {string} and {string}")
    public void userEntersAnd(String firstName, String middleName, String lastName) {

        sendText(emAddPage.firstName, firstName);
        sendText(emAddPage.middleName, middleName);
        sendText(emAddPage.lastName, lastName);
    }

    @When("user provides {string} {string} and {string}")
    public void userProvidesAnd(String firstName, String middleName, String lastName) {
        sendText(emAddPage.firstName, firstName);
        sendText(emAddPage.middleName, middleName);
        sendText(emAddPage.lastName, lastName);
    }

    @When("user enters multiple employee data and verify they are added")
    public void user_enters_multiple_employee_data_and_verify_they_are_added(DataTable dataTable) {
        List<Map<String, String>> employeeNames = dataTable.asMaps();

        for (Map<String, String> employeeName : employeeNames
        ) {
            sendText(emAddPage.firstName, employeeName.get("firstName"));
            sendText(emAddPage.middleName, employeeName.get("middleName"));
            sendText(emAddPage.lastName, employeeName.get("lastName"));
            click(emAddPage.saveButton);
            String fullEmployeeName = employeeName.get("firstName") + " " + employeeName.get("middleName") + " " + employeeName.get("lastName");
            if (emAddPage.employeeNameVerifyText.getText().equals(fullEmployeeName)) {
                System.out.println(fullEmployeeName + " " + "is added successfully");
            }
            click(emSearchPage.addEmpOption);
        }
    }

    @Then("user is able to add all employees successfully")
    public void userIsAbleToAddAllEmployeesSuccessfully() {
        System.out.println("All employees in the data table has been added successfully");
    }

    @When("user enters multiple employee data from excel and verify they are added")
    public void userEntersMultipleEmployeeDataFromExcelAndVerifyTheyAreAdded() {
        for (Map<String, String> employee : ExcelReader.excelIntoMap(Constants.TESTDATA_FILEPATH, ConfigReader.getPropertyValue("sheetName"))
        ) {
            sendText(emAddPage.firstName, employee.get("FirstName"));
            sendText(emAddPage.middleName, employee.get("MiddleName"));
            sendText(emAddPage.lastName, employee.get("LastName"));
            sendText(emAddPage.photoFile, employee.get("Photograph"));
            click(emAddPage.loginCheckbox);
            sendText(emAddPage.employeeUsername, employee.get("Username"));
            sendText(emAddPage.employeePassword, employee.get("Password"));
            sendText(emAddPage.employeeRePassword, employee.get("Password"));
            selectByVisibleText("Disabled");
            click(emAddPage.saveButton);

            String fullEmployeeName = employee.get("FirstName") + " " + employee.get("MiddleName") + " " + employee.get("LastName");
            Assert.assertEquals(fullEmployeeName, emAddPage.employeeNameVerifyText.getText());

            click(emSearchPage.addEmpOption);
        }
    }
}
