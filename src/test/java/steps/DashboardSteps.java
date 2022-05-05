package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import pages.DashboardPage;
import utils.CommonMethods;

import java.util.ArrayList;
import java.util.List;

public class DashboardSteps extends CommonMethods {
    @Then("user verifies all the dashboard tabs")
    public void userVerifiesAllTheDashboardTabs(DataTable dataTable) {
        List<String> expectedTabs = dataTable.asList();
        List<String> actualTabs = new ArrayList<>();

        for (WebElement navTab: dashboardPage.navTabs
             ) {
            actualTabs.add(navTab.getText());
        }

        //if assertion is passed it will not give you any information and will execute our code
        //if assertion is failed it will give you an error with comparison
        Assert.assertEquals(actualTabs,expectedTabs);

        //System.out.println("Navigation Tab is verified");
    }
}
