package Pages;

import Utils.BaseUITest;
import Utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class Dashboard extends BaseUITest {

    private WebDriver driver;
    private WaitUtils waitUtils;
    //PIM pim;
    private By dashboardText = By.xpath("//h6[text() = 'Dashboard']");

    private By pimLink = By.xpath("//span[text() = 'PIM']//ancestor::a");


    public Dashboard(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
      //  this.pim = new PIM(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickPim() {
        try {
            waitUtils.waitForElementToBeClickable(pimLink).click();
           // waitUtils.waitForElementToBeVisible(pim.getPimText());
            waitUtils.waitForElementToBeVisible(By.xpath("//h6[text() = 'PIM']"));
            System.out.println("Landed on the PIM page");
        }
        catch (TimeoutException e) {
            throw new RuntimeException("Failed to click on PIM or wait for PIM page to load", e);
        }
    }


}
