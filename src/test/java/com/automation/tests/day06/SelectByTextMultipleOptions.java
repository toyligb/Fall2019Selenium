package com.automation.tests.day06;

import com.automation.utilities.BrowserUtils;
import com.automation.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class SelectByTextMultipleOptions {

    public static void main(String[] args) {

        WebDriver driver = DriverFactory.createDriver("chrome");
        driver.manage().window().maximize();
        BrowserUtils.wait(1);
        driver.get("http://practice.cybertekschool.com/dropdown");
        BrowserUtils.wait(3);

        Select languagesSelect = new Select(driver.findElement(By.name("Languages")));
        boolean isMultiple = languagesSelect.isMultiple();
        System.out.println("isMultiple = " + isMultiple);
        languagesSelect.selectByVisibleText("Java");
        languagesSelect.selectByVisibleText("JavaScript");
        languagesSelect.selectByVisibleText("Python");

        //let's get all selected options
        List<WebElement> selectedLanguages = languagesSelect.getAllSelectedOptions();

        for (WebElement eachSelectedLanguage : selectedLanguages) {
            System.out.println("eachSelectedLanguage.getText() = " + eachSelectedLanguage.getText());
        }

        languagesSelect.deselectByVisibleText("Java"); //to unselect something
        BrowserUtils.wait(2);
        languagesSelect.deselectAll();

        BrowserUtils.wait(3);

        driver.quit();

    }

}
