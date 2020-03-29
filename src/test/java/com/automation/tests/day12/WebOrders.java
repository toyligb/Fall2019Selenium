package com.automation.tests.day12;

import com.automation.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class WebOrders {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setup() {
        driver = DriverFactory.createDriver("chrome");
        wait = new WebDriverWait(driver, 10);
        driver.manage().window().maximize();
        driver.get("http://secure.smartbearsoftware.com/samples/testcomplete12/weborders");
        driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
        driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test", Keys.ENTER);
    }

    /**
     * Go to web orders page
     * Click on "Check All" button
     * Verify that all records are selected
     */

    @Test
    public void checkBoxTest() {
        driver.findElement(By.id("ctl00_MainContent_btnCheckAll")).click();

        List<WebElement> chkBoxes = driver.findElements(By.id("ctl00_MainContent_orderGrid_ctl02_OrderSelector"));

        chkBoxes.forEach(each -> Assert.assertTrue(each.isSelected()));

//        for (WebElement eachChkBox : chkBoxes) {
//            Assert.assertTrue(eachChkBox.isSelected());
//        }

    }

    /**
     * Go to web orders page
     * Verify that Steve Johns zip code is 21233
     * The update his zip code to 20002
     * Verify it
     */
    @Test
    public void updateZipCode() {
        String actual = driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_orderGrid\"]/tbody/tr[4]/td[9]")).getText();
        String expected = "21233";
        if (actual.equals(expected)) {
            driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_orderGrid\"]/tbody/tr[4]/td[13]/input")).click();
            driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder_TextBox5\"]")).clear();
            driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder_TextBox5\"]")).sendKeys("20002");
            driver.findElement(By.id("ctl00_MainContent_fmwOrder_UpdateButton")).click();
        }

        String actualAfterUpdate = driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_orderGrid\"]/tbody/tr[4]/td[9]")).getText();

        Assert.assertEquals(actualAfterUpdate, "20002");
    }

    @Test
    public void updateZipCodeV2() {
        WebElement zipcode = driver.findElement(By.xpath("//td[text()='Steve Johns']//following-sibling::td[7]"));
        Assert.assertEquals(zipcode.getText(), "21233");
        //click on update image
        driver.findElement(By.xpath(" //td[text()='Steve Johns']//following-sibling::td/input")).click();
        WebElement zipcodeInput = driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox5"));
        zipcodeInput.clear();
        zipcodeInput.sendKeys("20002");
        driver.findElement(By.id("ctl00_MainContent_fmwOrder_UpdateButton")).click();
        zipcode = driver.findElement(By.xpath("//td[text()='Steve Johns']//following-sibling::td[7]"));
        Assert.assertEquals(zipcode.getText(), "20002");
    }


    @AfterMethod
    public void killDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

}
