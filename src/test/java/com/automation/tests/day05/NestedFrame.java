package com.automation.tests.day05;

import com.automation.utilities.BrowserUtils;
import com.automation.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NestedFrame {

    public static void main(String[] args) {

        WebDriver driver = DriverFactory.createDriver("chrome");
        driver.manage().window().maximize();
        driver.get("http://practice.cybertekschool.com/nested_frames");
        BrowserUtils.wait(4);

        driver.switchTo().frame("frame-top").switchTo().frame("frame-middle");

        WebElement content = driver.findElement(By.id("content"));
        System.out.println("content.getText() = " + content.getText());

        driver.switchTo().parentFrame().switchTo().frame("frame-right");
        WebElement body = driver.findElement(By.tagName("body"));
        System.out.println("body.getText() = " + body.getText());

        // to go to the bottom frame, you need to switch to the default content
        // because, top frame is a sibling for bottom, but not a parent
        driver.switchTo().defaultContent().switchTo().frame("frame-bottom");
        WebElement bottom = driver.findElement(By.tagName("body"));
        System.out.println("bottom.getText() = " + bottom.getText());

        driver.quit();

    }

}
