package Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;

public class ExtentManager {

    private static ExtentReports uiExtent;
    private static ExtentReports apiExtent;

    private static final String UI_REPORT_PATH = "reports/UITestReport.html";
    private static final String API_REPORT_PATH = "reports/APITestReport.html";

    public static ExtentReports getUIExtent() {
        if (uiExtent == null) {
            deleteExistingReport(UI_REPORT_PATH);
            uiExtent = createExtentReport(UI_REPORT_PATH);
        }
        return uiExtent;
    }

    public static ExtentReports getAPIExtent() {
        if (apiExtent == null) {
            deleteExistingReport(API_REPORT_PATH);
            apiExtent = createExtentReport(API_REPORT_PATH);
        }
        return apiExtent;
    }

    private static ExtentReports createExtentReport(String filePath) {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(filePath);

        // No setAppendExisting needed here for newer versions
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        return extent;
    }

    private static void deleteExistingReport(String filePath) {
        try {
            File reportFile = new File(filePath);
            if (reportFile.exists()) {
                reportFile.delete();
            }
        } catch (Exception e) {
            System.err.println("Could not delete existing report: " + filePath);
        }
    }
}
