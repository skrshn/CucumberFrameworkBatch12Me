package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utils.CommonMethods;

public class EmAddPage extends CommonMethods {

    @FindBy(id = "firstName")
    public WebElement firstName;

    @FindBy(id = "middleName")
    public WebElement middleName;

    @FindBy(id = "lastName")
    public WebElement lastName;

    @FindBy(id = "btnSave")
    public WebElement saveButton;

    @FindBy(id = "employeeId")
    public WebElement employeeID;

    @FindBy(xpath = "//div[@id='profile-pic']/h1")
    public WebElement employeeNameVerifyText;

    @FindBy(xpath = "//input[@id='photofile']")
    public WebElement photoFile;

    @FindBy(xpath = "//input[@id='chkLogin']")
    public WebElement loginCheckbox;

    @FindBy(xpath = "//input[@id='user_name']")
    public WebElement employeeUsername;

    @FindBy(xpath = "//input[@id='user_password']")
    public WebElement employeePassword;

    @FindBy(xpath = "//input[@id='re_password']")
    public WebElement employeeRePassword;

    @FindBy(xpath = " //select[@id='status']")
    public WebElement employeeStatus;


    public EmAddPage() {
        PageFactory.initElements(driver, this);
    }
}
