package com.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GitHubLoginTest {

    @Test
    void testGitHubLogin() {

        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://github.com/login");

        driver.findElement(By.id("login_field")).sendKeys("deissibelle");
        driver.findElement(By.id("password")).sendKeys("#lovemyself#8");
        driver.findElement(By.name("commit")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        wait.until(ExpectedConditions.not(
                ExpectedConditions.urlContains("login")
        ));

        assertTrue(driver.getCurrentUrl().contains("github.com"));

        driver.quit();
    }
}
