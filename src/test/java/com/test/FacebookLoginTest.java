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

public class FacebookLoginTest {

    @Test
    void testFacebookLogin() {

        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        driver.get("https://www.facebook.com/login");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")))
                .sendKeys("djumeghesibelle@gmail.com");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pass")))
                .sendKeys("lovemyself");

        wait.until(ExpectedConditions.elementToBeClickable(By.name("login")))
                .click();

        wait.until(ExpectedConditions.urlContains("facebook"));

        assertTrue(driver.getCurrentUrl().contains("facebook"));

        driver.quit();
    }
}
