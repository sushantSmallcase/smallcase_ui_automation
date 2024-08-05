package com.smallcase;
import org.openqa.selenium.WebDriver;
public class webpage {
    public static void getWebPage(String url, WebDriver driver) {
        driver.get(url);
        driver.manage().window().maximize();
    }
}