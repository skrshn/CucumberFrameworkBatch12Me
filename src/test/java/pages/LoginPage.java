package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class LoginPage extends CommonMethods {

    //object repository
    @FindBy(id = "txtUsername")
    public WebElement usernameField;

    @FindBy(name = "txtPassword")
    public WebElement passwordField;

    @FindBy(id = "btnLogin")
    public WebElement loginButton;

    @FindBy(xpath = "//span[text()='Invalid credentials']")
    public WebElement invalidCredentialsErrorMessage;

    @FindBy(xpath = "//span[text()='Username cannot be empty']")
    public WebElement userNameCannotBeEmptyErrorMessage;

    @FindBy(xpath = "//span[text()='Password cannot be empty']")
    public WebElement passwordCannotBeEmptyErrorMessage;

    public LoginPage() {
        PageFactory.initElements(driver,this);
    }
}
