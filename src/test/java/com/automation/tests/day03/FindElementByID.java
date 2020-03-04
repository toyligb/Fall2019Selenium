package com.automation.tests.day03;

import com.automation.utilities.DriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FindElementByID {

    public static void main(String[] args) throws Exception {

//        WebDriverManager.chromedriver().setup();
//        WebDriver driver = new ChromeDriver();
        WebDriver driver = DriverFactory.createDriver("chrome");
        driver.manage().window().maximize();
        driver.get("http://practice.cybertekschool.com/login");
        driver.findElement(By.name("username")).sendKeys("tomsmith");
        Thread.sleep(2000);

        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("SuperSecretPassword");
        Thread.sleep(2000);

        driver.findElement(By.id("wooden_spoon")).click();
        Thread.sleep(4000);

        String expected = "Welcome to the Secure Area. When you are done click logout below.";
        String actual = driver.findElement(By.tagName("h4")).getText();

        System.out.println(expected.equals(actual)? "TEST PASSED!" : "TEST FAILED!");

        // Let's click on Logout button. It looks like a button, but it's actually a link
        // every element with <a> tag is a link
        // if you have couple spaces in the text, just use partialLinkText instead of linkText
        // linkText -->> equals()
        // partialLinkText -->> contains() - complete match doesn't required
        WebElement logout = driver.findElement(By.linkText("Logout"));

        String href = logout.getAttribute("href");
        String className = logout.getAttribute("class");

        System.out.println("href = " + href);
        System.out.println("className = " + className);

        logout.click();

        Thread.sleep(3000);

        driver.findElement(By.name("username")).sendKeys("toyligb");
        Thread.sleep(2000);
        driver.findElement(By.name("password")).sendKeys("asdfv");
        Thread.sleep(2000);
        driver.findElement(By.id("wooden_spoon")).click();
        Thread.sleep(3000);

        WebElement errorMessage = driver.findElement(By.id("flash-messages"));
        System.out.println(errorMessage.getText());
        Thread.sleep(3000);

        driver.quit();

    }

}
