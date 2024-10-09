package com.example.demo;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import static org.springframework.test.util.AssertionErrors.assertTrue;

public class SeleniumTests {

    @Test
    public void test1() {
        WebDriver driver = new EdgeDriver();

        try {
            driver.manage().window().maximize();
            driver.get("http://"+System.getenv("MYSQL_HOST")+"/pi-zero-z/Template-CV.php");
            Thread.sleep(10000);
            driver.quit();
        } catch(Exception e) {
            driver.quit();
            assertTrue(e.getMessage(), false);
        }
    }
}
