package com.automation.tests.day04;

import com.automation.utilities.DriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class FindElementsTest {

    public static void main(String[] args) throws Exception {

        WebDriverManager.chromedriver().version("79").setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://practice.cybertekschool.com/");
        driver.manage().window().maximize();

        Thread.sleep(3000);

        // How to collect all links from the page?
        List<WebElement> links = driver.findElements(By.tagName("a"));

        for (WebElement link : links) {
            System.out.println("link.getText() = " + link.getText());
            System.out.println("link.getAttribute(\"href\") = " + link.getAttribute("href"));
            System.out.println();
        }

        for (int i = 1; i < links.size(); i++) {
            driver.findElements(By.tagName("a")).get(i).click();
            //Thread.sleep(3000);

            driver.navigate().back(); // go back
            //Thread.sleep(3000);
            // refresh list (assign again)
            //links = driver.findElements(By.tagName("a"));
        }

        driver.quit();

    }

}
