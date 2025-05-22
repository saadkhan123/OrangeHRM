package Selenium;

import Pages.Dashboard;
import Pages.Login;
import Pages.PIM;
import Utils.BaseUITest;
import Utils.ExtentManager;
import Utils.ScreenShotUtil;
import listeners.TestExecutionListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestExecutionListener.class)
public class UiTest extends BaseUITest {

    private Login loginPage;
    private Dashboard dashboardPage;
    private PIM pimPage;



    @Test
    public void testAddingEmployeeAndVerify() {
        test = ExtentManager.getUIExtent().createTest("Add a new employee and verify in the list");
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        loginPage = new Login(driver);
        dashboardPage = new Dashboard(driver);
        pimPage = new PIM(driver);
        test.info("Entering Credentials");
        boolean loginSuccess = loginPage.login("Admin", "admin123");

        if (loginSuccess) {
            test.pass("Login successful!");
        } else {
            test.fail("Login failed!");
            ScreenShotUtil.captureScreenshot(driver, "Login Failed");
        }

        Assert.assertTrue(loginSuccess, "Login should be successful");

        test.info("Redirecting to the Dashboard");
            dashboardPage.clickPim();
            test.info("Adding new Employee and Verifying if it exist in the record");
            pimPage.addNewEmployeeAndVerify();
            test.info("Logging out");
            pimPage.logout();



    }
}