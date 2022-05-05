package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class EmSearchPage extends CommonMethods {
    @FindBy(id = "menu_pim_viewPimModule")
    public WebElement pimOption;

    @FindBy(id = "menu_pim_viewEmployeeList")
    public WebElement empListOption;

    @FindBy(id = "menu_pim_addEmployee")
    public WebElement addEmpOption;

    @FindBy(id = "empsearch_id")
    public WebElement employeeIDField;

    @FindBy(xpath = "(//*[@type='text'])[1]")
    public WebElement searchName;

    @FindBy(id = "searchBtn")
    public WebElement searchButton;

    public EmSearchPage() {
        PageFactory.initElements(driver,this);
    }
}
