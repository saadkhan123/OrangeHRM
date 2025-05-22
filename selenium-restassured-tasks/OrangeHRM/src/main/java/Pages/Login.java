package Pages;

import Utils.BaseUITest;
import Utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class Login extends BaseUITest {

    private WebDriver driver;
    private WaitUtils waitUtils;

    //Dashboard dashboard;

    private By userName = By.cssSelector("input[name='username']");
    private By password = By.cssSelector("input[name='password']");
    private By loginBtn = By.cssSelector("button[type='submit']");
    private By forgetPass = By.xpath("//div[@class='orangehrm-login-forgot']/p");
    private By errorMessage = By.xpath("//div[@class='oxd-alert oxd-alert--error']/div/child::p");

    private By logoutBtn = By.cssSelector("a[href='/logout']");

    private By loginText = By.xpath("//h5[@class='oxd-text oxd-text--h5 orangehrm-login-title']");

    public By getLoginText() {
        return loginText;
    }


    public Login(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
       // this.dashboard = new Dashboard(driver);
        PageFactory.initElements(driver, this);
    }

    public boolean login(String username, String pass) {
        waitUtils.waitForElementToBeVisible(userName).sendKeys(username);
        waitUtils.waitForElementToBeVisible(password).sendKeys(pass);
        waitUtils.waitForElementToBeClickable(loginBtn).click();

        try {
           // waitUtils.waitForElementToBeVisible(dashboard.getDashboardText());
            waitUtils.waitForElementToBeVisible(By.xpath("//h6[text() = 'Dashboard']"));
            System.out.println("Landed on the dashboard");
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}