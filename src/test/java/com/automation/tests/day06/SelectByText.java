package com.automation.tests.day06;

import com.automation.utilities.BrowserUtils;
import com.automation.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class SelectByText {

    public static void main(String[] args) {

        WebDriver driver = DriverFactory.createDriver("chrome");
        driver.manage().window().maximize();
        BrowserUtils.wait(3);
        driver.get("http://practice.cybertekschool.com/dropdown");
        BrowserUtils.wait(3);
        //create a webelement object for drop-down list
        WebElement simpleDropdown = driver.findElement(By.id("dropdown"));
        //provide webelement object into constructor
        Select selectSimpleDropdown = new Select(simpleDropdown);
        //select by visible text
        selectSimpleDropdown.selectByVisibleText("Option 2");
        BrowserUtils.wait(3);
        //and select option 1
        selectSimpleDropdown.selectByVisibleText("Option 1");
        BrowserUtils.wait(3);

        Select selectYear = new Select(driver.findElement(By.id("year")));
        Select selectMonth = new Select(driver.findElement(By.id("month")));
        Select selectDay = new Select(driver.findElement(By.id("day")));

        selectYear.selectByVisibleText("1982");
        selectMonth.selectByVisibleText("February");
        selectDay.selectByVisibleText("27");
        BrowserUtils.wait(3);

        //select all months one by one
        //.getOptions(); -->> returns all options from dropdown as List<WebElement>
        List<WebElement> months = selectMonth.getOptions();
        for (WebElement eachMonth : months) {
            selectMonth.selectByVisibleText(eachMonth.getText());
            BrowserUtils.wait(1);
        }

        Select stateSelect = new Select(driver.findElement(By.id("state")));
        stateSelect.selectByVisibleText("California");
        BrowserUtils.wait(3);
        //option that is currently selected
        //getFirstSelectedOption() -->> returns a webelement, that's why we need to call getText
        //getText() retrieves visible text from the webelement
        String selected = stateSelect.getFirstSelectedOption().getText();

        System.out.println((selected.equals("California"))? "TEST PASSED!" : "TEST FAILED!");

        List<WebElement> states = stateSelect.getOptions();
        for (WebElement eachState : states) {
            System.out.println(eachState.getText());
        }

        driver.quit();

    }

}
