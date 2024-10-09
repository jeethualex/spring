package com.example.demo;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class SeleniumTests {

    private WebDriver driver;

    @Test
    public void test1()
    {

        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.google.com");
        driver.quit();
    }
}
