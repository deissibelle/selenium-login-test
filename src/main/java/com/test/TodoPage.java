package com.test;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TodoPage {

    private final WebDriver driver;

    // Sélecteurs génériques (fonctionnent sur ta page)
    private final By taskInput = By.cssSelector("input[type='text']");
    private final By addButton = By.xpath("//button[contains(text(),'Add')]");
    private final By taskItems = By.cssSelector("li");

    public TodoPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://tasks-app-likz.onrender.com/");
    }

    public void addTask(String task) {
        driver.findElement(taskInput).clear();
        driver.findElement(taskInput).sendKeys(task);
        driver.findElement(addButton).click();
    }

    public boolean isTaskDisplayed(String task) {
        List<WebElement> tasks = driver.findElements(taskItems);
        return tasks.stream().anyMatch(e -> e.getText().contains(task));
    }

    public int getTaskCount() {
        return driver.findElements(taskItems).size();
    }

    public void deleteFirstTask() {
        WebElement firstTask = driver.findElements(taskItems).get(0);
        firstTask.findElement(By.tagName("button")).click();
    }
}
