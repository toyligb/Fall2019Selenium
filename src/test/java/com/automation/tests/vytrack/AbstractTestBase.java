package com.automation.tests.vytrack;

import com.automation.utilities.BrowserUtils;
import com.automation.utilities.ConfigurationReader;
import com.automation.utilities.Driver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;

/**
 * TestBase - abstract class that is used as a basement for all test classes.
 * This class provides minimum essential setup and cleanup for every test of out project.
 * Class is abstract because has to be extended. Doesn't contain tests itself.
 * In this class, we initialize WebDriverWait, Actions class objects and open browser.
 * Should be stored under tests package or some kind of base package. But not under utilities package.
 * We can also add @BeforeMethod and @AfterMethod test, class, suit methods if needed.
 * <p>
 * It is a blueprint for all test classes.
 * <p>
 * TestBase class => we do not call any single method from this class or we do not instantiate it!
 * <p>
 * Driver.getDriver().get(URL);
 * We instantiate webDriver (=new className) in Driver class under utilities package.
 * getDriver();  method will return us driver object that comes from driver class.
 * call the static getDriver() method through the classNAme
 * By doing this; we will know that we are always calling the same driver
 */

public abstract class AbstractTestBase {
    //will be visible in the subclass, regardless on subclass location (same package or no)
    protected WebDriverWait wait;
    protected Actions actions;
    protected static ExtentReports report;
    protected static ExtentHtmlReporter htmlReporter;
    protected static ExtentTest test;
    //@Optional - to make parameter optional
    //if you don't specify it, testing will require to specify this parameter
    //for every test, in xml runner

    @BeforeTest
    @Parameters("reportName")
    public void setupTest(@Optional String reportName) {
        System.out.println("Report name = " + reportName);

        reportName = reportName == null ? "report.html" : reportName + ".html";

        report = new ExtentReports();

        String reportPath = "";

        //location of report file
        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            reportPath = System.getProperty("user.dir") + "\\test-output\\" + reportName;
        } else {
            reportPath = System.getProperty("user.dir") + "/test-output/" + reportName;
        }
        //is a HTML report itself
        htmlReporter = new ExtentHtmlReporter(reportPath);
        //add it to the reporter
        report.attachReporter(htmlReporter);
        htmlReporter.config().setReportName("VYTRACK Test Automation Results");
    }

    @AfterTest
    public void afterTest() {
        report.flush();//to release a report
    }

    @BeforeMethod
    public void setup() {
        String URL = ConfigurationReader.getProperty("qa1");
        Driver.getDriver().get(URL);
        Driver.getDriver().manage().window().maximize();
        wait = new WebDriverWait(Driver.getDriver(), 25);
        actions = new Actions(Driver.getDriver());
    }

    @AfterMethod
    public void teardown(ITestResult iTestResult) throws IOException {
        //ITestResult class describes the result of a test.
        //if test failed, take a screenshot
        //no failure - no screenshot
        if (iTestResult.getStatus() == ITestResult.FAILURE) {
            //screenshot will have a name of the test
            String screenshotPath = BrowserUtils.getScreenshot(iTestResult.getName());
            test.fail(iTestResult.getName());//attach test name that failed
            BrowserUtils.wait(2);
            test.addScreenCaptureFromPath(screenshotPath, "Failed");//attach screenshot
            test.fail(iTestResult.getThrowable());//attach console output
        }
        BrowserUtils.wait(1);
        Driver.closeDriver();
    }
}