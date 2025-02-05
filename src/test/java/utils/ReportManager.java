package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportManager {
    private static ExtentReports extent;
    private static ExtentTest test;

    public static ExtentReports getExtentReports() {
        if (extent == null) {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String reportDirectory = "/home/nsona/anos/Selenium/test-output/reports/";
            String reportPath = reportDirectory + "ExtentReport_" + timestamp + ".html";

            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
            sparkReporter.config().setReportName("Automation Test Results");
            sparkReporter.config().setDocumentTitle("Automation Test Report");
            sparkReporter.config().setTheme(Theme.DARK);
            sparkReporter.config().setTimeStampFormat("dd-MM-yyyy HH:mm:ss");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);

            // Add system information
            extent.setSystemInfo("Environment", "192.168.4.204");
            extent.setSystemInfo("Tester", "Sona N");
            extent.setSystemInfo("Browser", "Chrome");
        }
        return extent;
    }

    public static void setTest(ExtentTest activeTest) {
        test = activeTest;
    }

    public static void logError(String message) {
        if (test != null) {
            test.fail(message);
        }
        else {
            System.out.println("ExtentTest is not initialized. Unable to log info message: " + message);

        }
    }
    public static void pass(String message) {
        if (test != null) {
            test.pass(message);
        }
        else{
            System.out.println("ExtentTest is not initialized. Unable to log info message: " + message);
        }
    }

    public static void info(String s) {
        if (test != null) {
            test.info(s);  // Use the parameter 's' here, not 'message'
        } else {
            System.out.println("ExtentTest is not initialized. Unable to log info message: " + s);
        }
    }
    public static void logException(Exception e) {
        if (test != null) {
            test.fail("An exception occurred: " + e.getMessage());
            for (StackTraceElement element : e.getStackTrace()) {
                test.fail(element.toString());
            }
        } else {
            System.err.println("ExtentTest is not initialized. Unable to log exception: " + e.getMessage());
            e.printStackTrace();
        }
    }


    /**
     * Captures and attaches a screenshot to the report.
     *
     * @return
     */

    public static String captureScreenshot(WebDriver driver, String stepDetails) {
        if (test != null) {
            try {
                String sanitizedStepDetails = stepDetails.replaceAll("[^a-zA-Z0-9]", "_");
                String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String screenshotPath = "/home/nsona/anos/Selenium/test-output/screenshots/"
                        + sanitizedStepDetails + "_" + timestamp + ".png";
                File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                File destFile = new File(screenshotPath);
                FileUtils.copyFile(srcFile, destFile);
                 return screenshotPath;
            } catch (IOException e) {
                System.err.println("Failed to save screenshot: " + e.getMessage());
                e.printStackTrace();
            } catch (Exception e) {
                System.err.println("Unexpected error while capturing screenshot: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.err.println("Test object is null. Screenshot not taken.");
        }
        return null;
    }

}