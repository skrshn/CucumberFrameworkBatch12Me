package utils;


import io.cucumber.java.Scenario;
import org.testng.asserts.IAssert;
import org.testng.asserts.SoftAssert;

public class CustomSoftAsserts extends SoftAssert {

    @Override
    public void onAssertFailure(IAssert<?> assertCommand, AssertionError ex) {
         CommonMethods.takeScreenShot("failedSteps/");
    }

    @Override
    public void onAssertSuccess(IAssert<?> assertCommand) {
        CommonMethods.takeScreenShot("passedSteps/");
    }
}
