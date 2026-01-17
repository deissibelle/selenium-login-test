package com.test.todo;

import io.cucumber.java.After;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;

public class TodoSteps {
    WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    @Given("que je suis sur la page {string}")
    public void setup(String url) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
        driver.manage().window().maximize();
        driver.get(url);
    }

    @When("je saisis la tâche {string} et je clique sur ajouter")
    public void create(String texte) {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("task-input"))).sendKeys(texte);
        driver.findElement(By.id("add-btn")).click();
    }

    @Then("la tâche {string} doit être visible")
    public void verify(String texte) {
        WebElement task = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[contains(text(), '" + texte + "')]")));
        assertTrue(task.isDisplayed());
    }

    @And("j'actualise la page")
    public void refresh() {
        driver.navigate().refresh();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("task-list")));
    }

    @When("je double-clique sur {string} pour changer en {string}")
    public void update(String oldT, String newT) {
        WebElement span = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'"+oldT+"')]")));
        actions.doubleClick(span).perform();
        WebElement editInput = driver.switchTo().activeElement();
        editInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
        editInput.sendKeys(newT + Keys.ENTER);
    }

    @When("je supprime la tâche {string}")
    public void delete(String texte) {

        String xpath = "//li[contains(., '" + texte + "')]//button[descendant::*[contains(@data-lucide, 'trash')] or contains(@class, 'red')]";

        try {
            WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btn);

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        } catch (Exception e) {
            WebElement fallbackBtn = driver.findElement(By.xpath("//li[contains(., '" + texte + "')]//button"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", fallbackBtn);
        }
    }


    @Then("la tâche {string} ne doit plus exister")
    public void verifyDeleted(String texte) {
        boolean vanished = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//span[contains(text(),'"+texte+"')]")));
        assertTrue(vanished);
    }

    @After
    public void tearDown() {
    }
}
