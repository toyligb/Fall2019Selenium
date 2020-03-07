package com.automation.tests.day04;

import com.automation.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class VerifyThatElementIsGone {

    /*
    * Interview question:
    *
    *   How to check if element doesn't exists any more?
    *
     */
    public static void main(String[] args) throws Exception {

        WebDriver driver = DriverFactory.createDriver("chrome");
        driver.get("http://practice.cybertekschool.com/multiple_buttons");
        driver.manage().window().maximize();
        Thread.sleep(2000);

        driver.findElement(By.id("disappearing_button")).click();
        Thread.sleep(2000);

        List<WebElement> lst = driver.findElements(By.id("disappearing_button"));

        if (lst.size() == 0) {
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED");
        }

        Thread.sleep(2000);
        // to find all buttons
        // make sure that you use findElements
        List<WebElement> buttons = driver.findElements(By.tagName("button"));

        for (WebElement button : buttons) {
            // click on every button
            button.click();
            Thread.sleep(2000);
        }

        driver.quit();

    }

}
