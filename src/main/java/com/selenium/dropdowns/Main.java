package com.selenium.dropdowns;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        String browser = "edge";
        String driverName = "msedgedriver";
        System.setProperty("webdriver. " + browser + ".driver", System.getProperty("user.dir") + File.separator + "src"
                + File.separator + "main" + File.separator + "resources"
                + File.separator + "drivers" + File.separator + driverName + ".exe");
        WebDriver driver = new EdgeDriver();
        driver.get("https://google.com");
        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());
        driver.quit();
    }
}