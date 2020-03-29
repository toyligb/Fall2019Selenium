package com.automation.tests.day12;

import com.automation.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ExplicitWait {

    /**
     * //wait up to 10 seconds until title contains google
     * //ExpectedConditions is a class , we use it for explicit wait;
     * //ExpectedCondition is interface it's usage is different, we will use it in fluent wait
     * wait.until(ExpectedConditions.titleContains("Google"));
     * //when condition fails => we will get exception.
     * //by default it will check every 500 milliseconds => means that it checked 20 times until find the element
     * //Exception :
     * //org.openqa.selenium.TimeoutException: Expected condition failed:
     * //waiting for title to contain "Amazon". Current title: "Google" (tried for 10 second(s) with 500 milliseconds interval)
     */

    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = DriverFactory.createDriver("chrome");
        driver.manage().window().maximize();
    }

    @Test
    public void waitForTitle() {
        driver.get("http://google.com");

        WebDriverWait wait = new WebDriverWait(driver, 10);
        //wait up to 10 seconds until title contains Google
        wait.until(ExpectedConditions.titleIs("Google"));

        driver.navigate().to("https://amazon.com");
        wait.until(ExpectedConditions.titleContains("Amazon"));
    }

    @Test
    public void waitForVisibility() {
        driver.get("http://practice.cybertekschool.com/dynamic_loading/1");

        WebDriverWait wait = new WebDriverWait(driver, 10);

        driver.findElement(By.tagName("button")).click();
        //finding button with xpath
        //driver.findElement(By.xpath("//button[text()='Start']")).click();

        WebElement username = driver.findElement(By.name("username"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement submitBtn = driver.findElement(By.cssSelector("button[type='submit']"));

        wait.until(ExpectedConditions.visibilityOf(username)).sendKeys("tomsmith");
        wait.until(ExpectedConditions.visibilityOf(password)).sendKeys("SuperSecretPassword");

        wait.until(ExpectedConditions.visibilityOf(submitBtn));
        wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();

        String expected = "Welcome to the Secure Area. When you are done click logout below.";
        String actual = driver.findElement(By.className("subheader")).getText();

        Assert.assertEquals(actual, expected);
    }

    /**
     * overlay screen ==> div element goes on top of page we have this page this screen, it will show up but not immediately there is a gap
     * selenium starts manipulating once load done, but there is a gap=> between loading complete - overlay screen pop up
     * submit button condition not helpful cause it becomes true even before overlay appears
     * so we put condition:
     * wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loadingoverlay")));
     */

    @Test
    public void elementToBeClickableTest() {
        driver.get("http://practice.cybertekschool.com/dynamic_loading/5");

        WebDriverWait wait = new WebDriverWait(driver, 10);

        WebElement username = driver.findElement(By.name("username"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement submitBtn = driver.findElement(By.cssSelector("button[type='submit']"));

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loadingoverlay")));
        wait.until(ExpectedConditions.elementToBeClickable(submitBtn));

        username.sendKeys("tomsmith");
        password.sendKeys("SuperSecretPassword");
        submitBtn.click();

        String expected = "Welcome to the Secure Area. When you are done click logout below.";
        String actual = driver.findElement(By.className("subheader")).getText();

        Assert.assertEquals(actual, expected);

    }

    @AfterMethod
    public void killDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

}
