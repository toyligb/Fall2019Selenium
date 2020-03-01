package com.automation.tests.day02;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;

public class JumpToTheNewWindow {

    public static void main(String[] args) throws Exception {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://practice.cybertekschool.com/open_new_tab");
        driver.manage().window().maximize();

        Thread.sleep(5000);

        // every windows has some id, this id calls window handle
        // based on window handle, we can switch in between windows

        String windowsHandle = driver.getWindowHandle();

        System.out.println("windowsHandle = " + windowsHandle);
        // getWindowHandles() - returns id's of currently opened windows
        // Set - doesn't allow duplicates
        Set<String> windowhandles = driver.getWindowHandles();
        System.out.println("windowhandles = " + windowhandles);

        System.out.println("Before switch " + driver.getCurrentUrl());

        // since we have all windows
        // and we know id of original window
        // we can say switch to something that is not equals to old window id

        for (String windowId : windowhandles) {
            if (!windowId.equals(windowsHandle)) {
                driver.switchTo().window(windowId);
            }
        }
        System.out.println("After switch " + driver.getCurrentUrl());

        Thread.sleep(3000);

        driver.close();
//        Thread.sleep(3000);
////        driver.navigate().refresh();
////
////        Thread.sleep(3000);
////
////        driver.quit();

    }

    /**
     * This method helps to switch in between windows based on page title
     *
     * @param pageTitle
     * @param driver
     */
    public static void switchToWindowBasedOnTitle(String pageTitle, WebDriver driver) {
        Set<String> windows = driver.getWindowHandles();
        for (String window : windows) {
            driver.switchTo().window(window);
            if (driver.getTitle().equals(pageTitle)) {
                break;
            }
        }
    }

}
