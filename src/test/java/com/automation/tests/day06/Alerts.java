package com.automation.tests.day06;

import com.automation.utilities.BrowserUtils;
import com.automation.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Alerts {

    public static void main(String[] args) {
        WebDriver driver = DriverFactory.createDriver("chrome");
        driver.get("http://practice.cybertekschool.com/javascript_alerts");
        BrowserUtils.wait(3);

        List<WebElement> buttons = driver.findElements(By.tagName("button"));
        buttons.get(0).click();//to click on the first button
        BrowserUtils.wait(3);
        //to get the text from popup message
        String popupText = driver.switchTo().alert().getText();
        System.out.println(popupText);
        driver.switchTo().alert().accept();//to click OK
        String expected = "You successfully clicked an alert";
        String actual = driver.findElement(By.id("result")).getText();
        //if will fail, because there is a typo ##BUG##
        if (expected.equals(actual)) {
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED");
            System.out.println("Expected: " + expected);
            System.out.println("Actual:   " + actual);
        }
        BrowserUtils.wait(3);

        buttons.get(1).click(); //to click on the 2nd button
        BrowserUtils.wait(3);
        //to click cancel
        driver.switchTo().alert().dismiss();

        System.out.println((driver.findElement(By.id("result")).getText().equals("You clicked: Cancel")) ? "TEST PASSED" : "TEST FAILED");
        BrowserUtils.wait(2);

        // TASK: click on button #3
        // Enter some text: Hello, World!
        // Verify that result text ends with Hello, World!
        buttons.get(2).click();
        driver.switchTo().alert().sendKeys("Hello, World!");
        BrowserUtils.wait(3);

        driver.switchTo().alert().accept();
        System.out.println((driver.findElement(By.id("result")).getText().endsWith("Hello, World!")) ? "TEST PASSED" : "TEST FAILED");

        BrowserUtils.wait(3);
        driver.quit();
    }

}
