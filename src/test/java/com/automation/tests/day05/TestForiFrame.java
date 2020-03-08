package com.automation.tests.day05;

import com.automation.utilities.BrowserUtils;
import com.automation.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TestForiFrame {

    public static void main(String[] args) {

        WebDriver driver = DriverFactory.createDriver("chrome");
        driver.manage().window().maximize();
        driver.get("http://practice.cybertekschool.com/iframe");
        BrowserUtils.wait(4);

        // before looking for that element, we need to jump to that frame
        driver.switchTo().frame("mce_0_ifr");
        // now, this content will be visible

        WebElement textInput = driver.findElement(By.id("tinymce"));

        System.out.println("textInput = " + textInput.getText());
        textInput.clear();
        BrowserUtils.wait(2);
        textInput.sendKeys("Toyly Gurdov");
        BrowserUtils.wait(4);

        // exit from the frame
        driver.switchTo().defaultContent();

        WebElement heading = driver.findElement(By.tagName("h3"));
        System.out.println(heading.getText());

        driver.quit();

    }

}