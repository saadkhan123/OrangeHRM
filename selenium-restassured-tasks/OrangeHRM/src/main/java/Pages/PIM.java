package Pages;

import Utils.BaseUITest;
import Utils.WaitUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.UUID;

public class PIM extends BaseUITest {

    private WebDriver driver;
    private WaitUtils waitUtils;

    //Login login;

    private By pimText = By.xpath("//h6[text() = 'PIM']");

    private By addEmployeeTitle = By.xpath("//h6[text() = 'Add Employee']");

    private By employeeListNavBtn = By.xpath("//a[text() = 'Employee List']");
    private By addEmployeeBtn = By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--secondary']");

    private By firstNameTextField = By.cssSelector("input[name='firstName']");

    private By lastNameTextField = By.cssSelector("input[name='lastName']");

    public String employeeId() {
        By empIdInput = By.xpath("//label[text()='Employee Id']/following::div[1]//input");
        WebElement empIdElement = waitUtils.waitForElementToBeVisible(empIdInput);
        return empIdElement.getAttribute("value");
    }

    private By createLoginDetailsBtn = By.cssSelector("span[class='oxd-switch-input oxd-switch-input--active --label-right']");

    private By userNameTextField = By.xpath("//label[text()= 'Username']/ancestor::div/following-sibling::div/input");

    private By passwordTextField = By.xpath("//label[text()= 'Password']/ancestor::div/following-sibling::div/input");

    private By confirmPasswordTextField = By.xpath("//label[text()= 'Confirm Password']/ancestor::div/following-sibling::div/input");

    private By saveBtn = By.cssSelector("button[type='button']");

    private By employeeIdTextField = By.xpath("//label[text() = 'Employee Id']/ancestor::div/following-sibling::div/input");

    private By firstNameInRecords = By.xpath("//div[@class='oxd-table-card']/child::div/child::div[3]");
    private By profileDropDown = By.cssSelector("span[class='oxd-userdropdown-tab']");

    private By logoutBtn = By.xpath("//a[text() = 'Logout']");

    private By searchBtn = By.cssSelector("button[type='submit']");

    private By personalDetailsText = By.xpath("//h6[text() = 'Personal Details']");

    private By recordFoundText = By.xpath("//div[@class='orangehrm-horizontal-padding orangehrm-vertical-padding']/span");

    public PIM(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
        //this.login = new Login(driver);
        PageFactory.initElements(driver, this);
    }

    public void addNewEmployeeAndVerify() {

        String firstName = "First_" + UUID.randomUUID().toString().substring(0, 6);
        String lastName = "Last_" + UUID.randomUUID().toString().substring(0, 6);
        String uniqueUsername = "user_" + UUID.randomUUID().toString().substring(0, 8);
        String uniquePassword = "pass_" + UUID.randomUUID().toString().substring(0, 8);

        waitUtils.waitForElementToBeClickable(addEmployeeBtn).click();
        waitUtils.waitForElementToBeVisible(addEmployeeTitle);
        String getEmployeeId = employeeId();
        System.out.println("Employee id : " + getEmployeeId);
        waitUtils.waitForElementToBeVisible(firstNameTextField).sendKeys(firstName);
        waitUtils.waitForElementToBeVisible(lastNameTextField).sendKeys(lastName);

        String enteredFirstName = driver.findElement(firstNameTextField).getAttribute("value");
        String enteredLastName = driver.findElement(lastNameTextField).getAttribute("value");

        System.out.println("Entered First Name: " + enteredFirstName);
        System.out.println("Entered Last Name: " + enteredLastName);

        waitUtils.waitForElementToBeVisible(createLoginDetailsBtn).click();
        waitUtils.waitForElementToBeVisible(userNameTextField).sendKeys(uniqueUsername);
        waitUtils.waitForElementToBeVisible(passwordTextField).sendKeys(uniquePassword);
        waitUtils.waitForElementToBeVisible(confirmPasswordTextField).sendKeys(uniquePassword);
        waitUtils.waitForElementToBeVisible(confirmPasswordTextField).sendKeys(Keys.ENTER);

        waitUtils.waitForElementToBeVisible(personalDetailsText);

        waitUtils.waitForElementToBeClickable(By.xpath("//span[text() = 'PIM']//ancestor::a")).click();
        waitUtils.waitForElementToBeVisible(employeeIdTextField).sendKeys(getEmployeeId);
        waitUtils.waitForElementToBeClickable(searchBtn).click();

        waitUtils.waitForElementToBeVisible(recordFoundText);
        String firstNameInResultTable = waitUtils.waitForElementToBeVisible(firstNameInRecords).getText();
        Assert.assertEquals(firstNameInResultTable, enteredFirstName, "Record does not match !");
        System.out.println("Record Verified from the Employee list !");

    }

    public boolean logout() {
        waitUtils.waitForElementToBeClickable(profileDropDown).click();
        waitUtils.waitForElementToBeClickable(logoutBtn).click();
        try {
            //waitUtils.waitForElementToBeVisible(login.getLoginText());
            waitUtils.waitForElementToBeVisible(By.xpath("//h5[@class='oxd-text oxd-text--h5 orangehrm-login-title']"));
            System.out.println("Logged out Successfully");
            return true;
        } catch (TimeoutException e) {
            return false;
        }

    }
}