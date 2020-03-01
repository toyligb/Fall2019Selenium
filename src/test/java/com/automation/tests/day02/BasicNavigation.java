package com.automation.tests.day02;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;

public class BasicNavigation {

    public static void main(String[] args) throws Exception {

        // To start selenium script we need:
        // setup WebDriver (browser driver) and create WebDriver object
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        // In selenium, everything starts from WebDriver interface
        // ChromeDriver extends RemoteWebDriver -->> implements WebDriver
        driver.get("http://google.com"); // to open a website
        driver.manage().window().maximize(); // to maximize browser
        //driver.manage().window().fullscreen();

        driver.navigate().to("https://amazon.com");
        Thread.sleep(3000);
        if (driver.getTitle().toLowerCase().contains("amazon")) {
            System.out.println("TEST PASSED!");
        } else {
            System.out.println("TEST FAILED!");
        }

        driver.navigate().back();
        Thread.sleep(3000);
        driver.navigate().forward();
        Thread.sleep(3000);

        driver.navigate().refresh();

        Thread.sleep(3000); // for demo, wait 3 seconds

        verifyEquals(driver.getTitle(), "Google");

        String title = driver.getTitle(); // returns <title> Some title </title> text
        System.out.println("Title is " + title);

        driver.close();

    }

    public static void verifyEquals (String arg1, String arg2) {
        System.out.println(arg1.equals(arg2)? "TEST PASSED!" : "TEST FAILED!");
    }

}
