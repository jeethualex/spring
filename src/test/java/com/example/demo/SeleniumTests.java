package com.example.demo;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import static org.springframework.test.util.AssertionErrors.assertTrue;

public class SeleniumTests {

    private WebDriver driver;

    @Test
    public void test1() {
        WebDriver driver = new EdgeDriver();

        try {
            driver.manage().window().maximize();
            driver.get("http://localhost:8080/swagger-ui/index.html");
            Thread.sleep(10000);
            driver.quit();
        } catch(Exception e) {
            driver.quit();
            assertTrue(e.getMessage(), false);
        }
    }
}
