package Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BaseApiTest {

    protected static ExtentReports extent;
    protected ExtentTest test;

    @BeforeSuite(alwaysRun = true)
    public void setupReport() {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String reportPath = "reports/API_Report_" + timestamp + ".html";

        ExtentSparkReporter spark = new ExtentSparkReporter(new File(reportPath));
        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Framework", "RestAssured");
        extent.setSystemInfo("Test Type", "API Automation");
        extent.setSystemInfo("Author", "Saad Ahmed");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDownReport() {
        if (ExtentManager.getAPIExtent() != null) {
            ExtentManager.getAPIExtent().flush();
        }
    }
}
