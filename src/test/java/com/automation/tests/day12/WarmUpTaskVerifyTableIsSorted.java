package com.automation.tests.day12;

import com.automation.utilities.BrowserUtils;
import com.automation.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

public class WarmUpTaskVerifyTableIsSorted {

    /**
     * Go to http://practice.cybertekschool.com/tables
     * Click on "Last name" column name
     * Verify that table is sorted by last name in alphabetic order.
     * <p>
     * The Java String compareTo() method is used for comparing two strings lexicographically.
     * Each character of both the strings is converted into a Unicode value for comparison.
     * If both the strings are equal then this method returns 0 else it returns positive or negative value.
     * The result is positive if the first string is lexicographically greater than
     * the second string else the result would be negative.
     * <p>
     * This method is coming from Comparable interface.
     * If you want ot be able to sort collection of some class
     * you need to implement this interface
     * and override compareTo method
     * <p>
     * "a".compareTo("b") = -1
     * 61 - 62 = -1
     * a is before b
     * "a".compareTo("a")
     * 61 - 61 = 0
     * 0 means 2 strings are equals
     */

    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = DriverFactory.createDriver("chrome");
        driver.manage().window().maximize();
        driver.get("http://practice.cybertekschool.com/");
        BrowserUtils.wait(2);
    }

    /**
     * table - tag that is used to create a web table in HTML
     * <table>
     *     <thead>
     *         <tr>
     *             <th>First Name</th>
     *             <th>Last Name</th>
     *             <th>Address</th>
     *         </tr>
     *     </thead>
     *     <tbody>
     *         <tr>
     *             <td>James</td>
     *             <td>May</td>
     *             <td>101 Groove St.</td>
     *         </tr>
     *         <tr>
     *             <td>Mark</td>
     *             <td>Portman</td>
     *             <td>974 Green Ave</td>
     *         </tr>
     *     </tbody>
     * </table>
     * //table[1] - get the first table on the page
     * //table[1]//tr - get all rows from first table
     * //table[1]//tbody//tr - get all rows from the first table, excluding table header
     * //table//tbody//td[1] - get first column
     * //table//tbody//tr[1] - get first row
     * //table//tbody//tr[2]//td[1] - get data from second row, first column
     * //table//th - get all column names
     * //table//tbody//td[last()] - get last column
     * //table//tbody//tr[last()] - get last row
     */

    @Test
    public void verifyTableIsSorted() {
        driver.findElement(By.linkText("Sortable Data Tables")).click();
        BrowserUtils.wait(2);

        //click on column name
        driver.findElement(By.xpath("//table[1]//*[text()='Last Name']")).click();
        BrowserUtils.wait(2);

        //collect all values from the first column
        List<WebElement> column = driver.findElements(By.xpath("//table[1]//tbody//td[1]"));
        for (int i = 0; i < column.size() - 1; i++) {

            String value = column.get(i).getText();
            String nextValue = column.get(i + 1).getText();
            System.out.println(value.compareTo(nextValue));
            //if difference is negative - order value is before nextValue in alphabetic order
            //if difference is positive - order value is after nextValue in alphabetic order
            //if difference is 0 - value and nextValue are equals

            Assert.assertTrue(value.compareTo(nextValue) <= 0);

        }

    }

    @Test
    public void verifyTableIsSortedV2() {
        driver.findElement(By.linkText("Sortable Data Tables")).click();
        BrowserUtils.wait(2);

        List<String> actual = driver.findElements(By.xpath("//table[1]//tbody//tr//td[1]"))
                .stream().map(each -> each.getText()).sorted().collect(Collectors.toList());

        driver.findElement(By.xpath("(//span[text()='Last Name'])[1]")).click();
        List<String> expected = driver.findElements(By.xpath("//table[1]//tbody//tr//td[1]"))
                .stream().map(each -> each.getText()).collect(Collectors.toList());

        Assert.assertEquals(actual, expected);
    }


    @AfterMethod
    public void tearDown() {
        BrowserUtils.wait(2);
        driver.quit();
    }

}
