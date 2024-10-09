package com.example.demo;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class SeleniumTests {

    private WebDriver driver;

    @Test
    public void test1() throws InterruptedException {

        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:8080/swagger-ui/index.html");
        Thread.sleep(10000);
        driver.quit();
    }
}
