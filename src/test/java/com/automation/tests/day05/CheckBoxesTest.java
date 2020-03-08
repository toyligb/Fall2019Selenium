package com.automation.tests.day05;

import com.automation.utilities.BrowserUtils;
import com.automation.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CheckBoxesTest {

    public static void main(String[] args) {

        WebDriver driver = DriverFactory.createDriver("chrome");
        driver.manage().window().maximize();

        // TASK:
        // verify that 1st checkbox is not selected and 2nd is selected
        driver.get("http://practice.cybertekschool.com/checkboxes");
        BrowserUtils.wait(2);

        List<WebElement> chkBoxes = driver.findElements(By.tagName("input"));
        for (WebElement eachBox : chkBoxes) {
            if (eachBox.isSelected()) {
                System.out.println("1st checkbox is checked");
            } else {
                System.out.println("2nd checkbox is not checked");
            }
        }

        if (!chkBoxes.get(0).isSelected() && chkBoxes.get(1).isSelected()) {
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED");
        }

        BrowserUtils.wait(2);
        WebElement chkBox1 = chkBoxes.get(0);
        chkBox1.click();

        BrowserUtils.wait(2);

        if (chkBox1.isSelected()) {
            System.out.println("TEST PASSED");
            System.out.println("CHECKBOX #1 IS SELECTED");
        } else {
            System.out.println("TEST FAILED");
            System.out.println("CHECKBOX #1 IS NOT SELECTED");
        }

        driver.quit();

    }

}
