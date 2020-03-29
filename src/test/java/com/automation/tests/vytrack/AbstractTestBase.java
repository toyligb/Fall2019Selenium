package com.automation.tests.vytrack;

import com.automation.utilities.ConfigurationReader;
import com.automation.utilities.Driver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * TestBase - abstract class that is used as a basement for all test classes.
 * This class provides minimum essential setup and cleanup for every test of out project.
 * Class is abstract because has to be extended. Doesn't contain tests itself.
 * In this class, we initialize WebDriverWait, Actions class objects and open browser.
 * Should be stored under tests package or some kind of base package. But not under utilities package.
 * We can also add @BeforeMethod and @AfterMethod test, class, suit methods if needed.
 *
 * It is a blueprint for all test classes.
 *
 * TestBase class => we do not call any single method from this class or we do not instantiate it!
 *
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

    @BeforeMethod
    public void setup() {
        String URL = ConfigurationReader.getProperty("qa1");
        Driver.getDriver().get(URL);
        Driver.getDriver().manage().window().maximize();
        wait = new WebDriverWait(Driver.getDriver(), 15);
        actions = new Actions(Driver.getDriver());
    }

    @AfterMethod
    public void teardown() {
        Driver.closeDriver();
    }
}
