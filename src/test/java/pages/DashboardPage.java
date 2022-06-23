package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

import java.util.List;

public class DashboardPage extends CommonMethods {

    @FindBy(xpath = "//*[@class='menu']/ul/li")
    public static List<WebElement> navTabs;

    @FindBy(xpath = "//a[@id='welcome']")
    public static WebElement welcomeMessage;


    public DashboardPage() {
        PageFactory.initElements(driver, this);
    }
}
