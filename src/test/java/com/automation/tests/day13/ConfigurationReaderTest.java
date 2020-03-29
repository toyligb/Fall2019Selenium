package com.automation.tests.day13;

import com.automation.utilities.ConfigurationReader;
import org.testng.annotations.Test;

public class ConfigurationReaderTest {

    @Test
    public void readProperties() {
        String browser = ConfigurationReader.getProperty("browser");
        String url = ConfigurationReader.getProperty("qa1");
        String color = ConfigurationReader.getProperty("color");

        System.out.println("browser = " + browser);
        System.out.println("url = " + url);
        System.out.println("color = " + color);

        String storeManager = ConfigurationReader.getProperty("store_manager");
        String password = ConfigurationReader.getProperty("password");
        String driver = ConfigurationReader.getProperty("driver");

        System.out.println("storeManager = " + storeManager);
        System.out.println("password = " + password);
        System.out.println("driver = " + driver);
    }

}