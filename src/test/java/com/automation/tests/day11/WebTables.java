package com.automation.tests.day11;

import com.automation.utilities.BrowserUtils;
import com.automation.utilities.DriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class WebTables {

    private WebDriver driver;

    //*[@id="table1"]//tbody//td  ==>> gives all cells
    //but if you specify row   ==>> //*[@id="table1"]//tbody//tr[3]//td  : it goes that exact data

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().version("79").setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        //headless mode makes execution twice faster
        //it does everything except file uploading
        //set it true to make it work
        chromeOptions.setHeadless(false); //to run browser without GUI. Makes browser invisible.
        driver = new ChromeDriver(chromeOptions);
        //driver = DriverFactory.createDriver("chrome");
        driver.get("http://practice.cybertekschool.com/tables");
        driver.manage().window().maximize();
    }

    @Test
    public void getColumnNames() {
        List<String> extected = Arrays.asList("Last Name", "First Name", "Email", "Due", "Web Site", "Action");
        // th - represents table header cells
        List<WebElement> columnNames = driver.findElements(By.xpath("//table[1]//th"));

        for (WebElement eachColumnName : columnNames) {
            System.out.println("eachColumnName.getText() = " + eachColumnName.getText());
        }
        //BrowserUtils.getTextFromWebElements(columnNames) ==>
        //this method takes the text of every single webElement and puts it into collection of strings

        Assert.assertEquals(BrowserUtils.getTextFromWebElements(columnNames), extected);

    }

    @Test
    public void verifyRowCount() {
        // //tbody//tr - to get all rows from table body, excluding table header
        List<WebElement> rows = driver.findElements(By.xpath("//table[1]//tbody//tr"));
        //if we will get a size of this collection, it automatically equals to number of elements
        Assert.assertEquals(rows.size(), 4);
        Assert.assertTrue(rows.size() == 4);
    }

    /**
     * To get specific column, skip row index and just provide td index
     */
    @Test
    public void getSpecificColumn() {
        // td[5] - column with links
        List<WebElement> links = driver.findElements(By.xpath("//table[1]//tbody//td[5]"));
        System.out.println(BrowserUtils.getTextFromWebElements(links));
    }

    // once you find email cell in the first table that has this email (jdoe@hotmail.com) then go to following sibling has linkText delete :
    // //td[text()='jdoe@hotmail.com']//following-sibling::td/a[text()='delete']
    // //table[1]//td[text()='fbach@yahoo.com']/..//a[text()='delete']  -->>  /.. - means goto parent
    // //table[1]//td[text()='fbach@yahoo.com']/..//a[2] -->> hardcoded version

    /**
     * Go to tables example page
     * Delete record with jsmith@gmail.com email
     * Verify that number of rows is equals to 3
     * Verify that jsmith@gnail.com doesn't exists anymore in the table
     */

    @Test
    public void deleteRowTest() {
        BrowserUtils.wait(3);
        driver.findElement(By.xpath("//table[1]//td[text()='jsmith@gmail.com']/..//a[text()='delete']")).click();

        BrowserUtils.wait(3);
        //get count of rows
        int rows = driver.findElements(By.xpath("//table[1]//tbody//tr")).size();

        Assert.assertEquals(rows, 3);
        List<WebElement> emails = driver.findElements(By.xpath("//table[1]//td[text()='jsmith@gmail.com']"));
        Assert.assertTrue(emails.isEmpty());
    }

    /**
     * Let's write a function that will return column index based on column name
     */
    @Test
    public void getColumnIndexByName() {
        String columnName = "Email";

        List<WebElement> columnNames = driver.findElements(By.xpath("//table[1]//th"));

        int index = 0;
        for (int i = 0; i < columnNames.size(); i++) {
            String actualColumnName = columnNames.get(i).getText();

            System.out.println(String.format("Column name: %s, position %s", actualColumnName, i));
            //System.out.printf("Column name: %s, position %s\n", actualColumnName, i);

            if (actualColumnName.equals(columnName)) {
                index = i + 1; // in the xpath index starts with 1 !!!!!!!!!!
                break;
            }
        }
        Assert.assertEquals(index, 3);
    }

    @Test
    public void getSpecificCell() {
        String expected = "http://www.jdoe.com";

        int row = 3;
        int column = 5;

        String xpath = "//table[1]//tbody//tr[" + row + "]//td[" + column + "]";

        WebElement cell = driver.findElement(By.xpath(xpath));

        Assert.assertEquals(cell.getText(), expected);
    }

    @AfterMethod
    public void tearDown() {
        BrowserUtils.wait(3);
        driver.quit();
    }

}