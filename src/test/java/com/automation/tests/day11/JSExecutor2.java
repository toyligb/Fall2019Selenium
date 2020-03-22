package com.automation.tests.day11;

import com.automation.utilities.BrowserUtils;
import com.automation.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class JSExecutor2 {

    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = DriverFactory.createDriver("chrome");
        driver.get("http://practice.cybertekschool.com/");
        driver.manage().window().maximize();
    }

    @Test(description = "Verifying page title")
    public void verifyTitle() {
        String expected = "Practice";
        //we create JavascriptExecutor object by casting webdriver object
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //executeScript - this method executes javascript code
        //we provide js code as a string
        //String actual = (String) js.executeScript("return document.title");
        //.toString() - to avoid additional casting from Object to String
        String actual = js.executeScript("return document.title").toString();

        //Assert.assertEquals(actual, expected);
        Assert.assertTrue(actual.equals(expected));
    }

    @Test
    public void clickTest() {
        WebElement link = driver.findElement(By.linkText("Multiple Buttons"));
        //disable this click action to perform it with js executor
//        link.click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //after "" you can list webelements that will be used
        //as part of your javascript code
        //it's varargs, so you can list 0+
        //arguments - listed after
        //use index to get specific webelements
        //WebElement arguments = {element, link, link2}
        //from left to right
        js.executeScript("arguments[0].click()", link);
        BrowserUtils.wait(2);

        WebElement button6 = driver.findElement(By.id("disappearing_button"));

        js.executeScript("arguments[0].click()", button6);
        BrowserUtils.wait(2);

        WebElement result = driver.findElement(By.id("result"));
        Assert.assertEquals(result.getText(), "Now it's gone!");

    }

    @Test
    public void textInputTest() {
        driver.findElement(By.linkText("Form Authentication")).click();
        BrowserUtils.wait(3);

        WebElement username = driver.findElement(By.name("username"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement loginbtn = driver.findElement(By.id("wooden_spoon"));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        //to get text from input box - read attribute "value"
        //to enter text - set attribute "value"

        js.executeScript("arguments[0].setAttribute('value', 'tomsmith')", username);
        js.executeScript("arguments[0].setAttribute('value', 'SuperSecretPassword')", password);
        js.executeScript("arguments[0].click()", loginbtn);

        BrowserUtils.wait(4);
        String subheader = js.executeScript("return document.getElementsByClassName('subheader')[0].textContent").toString();
        String expected = "Welcome to the Secure Area. When you are done click logout below.";

        Assert.assertEquals(subheader, expected);
    }

    @Test
    public void clickAllButtons() {
        driver.findElement(By.linkText("Multiple Buttons")).click();
        BrowserUtils.wait(3);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        List<WebElement> lstOfBtns = driver.findElements(By.xpath("//button[@class='btn btn-primary']"));
        for (WebElement eachBtn : lstOfBtns) {
            js.executeScript("arguments[0].click()", eachBtn);
            BrowserUtils.wait(2);
        }

    }

    @Test
    public void scrollToElement() {
        //href = link, URL
        WebElement link = driver.findElement(By.linkText("Cybertek School"));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true)", link);
        BrowserUtils.wait(5);
    }

    @Test
    public void scrollTest() {
        //navigate().to() method calls get method, =>  within same window to go another url
        driver.navigate().to("http://practice.cybertekschool.com/infinite_scroll");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for (int i = 0; i < 15; i++) {
            js.executeScript("window.scrollBy(0, 1000)");
            BrowserUtils.wait(1);
        }
    }

    @AfterMethod
    public void tearDown() {
        BrowserUtils.wait(3);
        driver.quit();
    }

}
