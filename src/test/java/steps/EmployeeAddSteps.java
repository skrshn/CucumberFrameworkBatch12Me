package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.CommonMethods;
import utils.Constants;
import utils.DBUtils;
import utils.ExcelReader;

import java.util.*;

public class EmployeeAddSteps extends CommonMethods {
    String empId;
    String dbEmpId;
    String firstName;
    String dbFirstName;

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

    @When("user adds multiple employees from excel file using {string} sheet and verify the user added")
    public void userAddsMultipleEmployeesFromExcelFileUsingSheetAndVerifyTheUserAdded(String sheetName) {
        List<Map<String, String>> newEmployees = ExcelReader.excelIntoMap(Constants.TESTDATA_FILEPATH, sheetName);
        Iterator<Map<String, String>> itr = newEmployees.iterator();
        while (itr.hasNext()) {
            Map<String, String> newEmployee = itr.next();
            sendText(emAddPage.firstName, newEmployee.get("FirstName"));
            sendText(emAddPage.middleName, newEmployee.get("MiddleName"));
            sendText(emAddPage.lastName, newEmployee.get("LastName"));
            String employeeID = emAddPage.employeeID.getAttribute("value");
            sendText(emAddPage.photoFile, newEmployee.get("Photograph"));
            if (!emAddPage.loginCheckbox.isSelected()) {
                click(emAddPage.loginCheckbox);
            }
            sendText(emAddPage.employeeUsername, newEmployee.get("Username"));
            sendText(emAddPage.employeePassword, newEmployee.get("Password"));
            sendText(emAddPage.employeeRePassword, newEmployee.get("Password"));
            selectByVisibleText("Enabled");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            click(emAddPage.saveButton);
            click(emSearchPage.empListOption);
            sendText(emSearchPage.employeeIDField, employeeID);
            click(emSearchPage.searchButton);


            List<WebElement> rowData = driver.findElements(By.xpath("//*[@id='resultTable']/tbody/tr"));
            for (int i = 0; i < rowData.size(); i++) {
                String rowText = rowData.get(i).getText();
                String expectedData = employeeID + " " + newEmployee.get("FirstName") + " "
                        + newEmployee.get("MiddleName") + " " + newEmployee.get("LastName");

                //new CustomSoftAsserts().assertEquals(expectedData, rowText);
                Assert.assertEquals(expectedData, rowText);
            }
            click(emSearchPage.addEmpOption);
        }
    }

    @And("user grabs the employee ID")
    public void userGrabsTheEmployeeID() {
        empId = getTextByValue(emAddPage.employeeID);
        firstName = getTextByValue(emAddPage.firstName);
    }

    @And("user queries the database for same employee ID")
    public void userQueriesTheDatabaseForSameEmployeeID() {
        String query = "Select * FROM syntaxhrm_mysql.hs_hr_employees where employee_id = " + empId;
        dbFirstName = DBUtils.getListOfMapFromResultSet(query).get(0).get("emp_firstname");
        dbEmpId = DBUtils.getListOfMapFromResultSet(query).get(0).get("employee_id");
    }

    @Then("user verifies the results")
    public void userVerifiesTheResults() {
        System.out.println("First name from Front-End : " + firstName);
        System.out.println("First name from DB : " + dbFirstName);
        System.out.println("Employee ID from Front-End : " + empId);
        System.out.println("Employee ID from DB : " + dbEmpId);
        Assert.assertEquals(firstName,dbFirstName);
        Assert.assertEquals(empId,dbEmpId);
    }

}
