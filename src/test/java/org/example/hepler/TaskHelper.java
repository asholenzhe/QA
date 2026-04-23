package org.example.hepler;

import org.example.AppManager;
import org.example.data.TaskData;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TaskHelper extends HelperBase { //создание задачи, закрытие оверлея и похожие действия.

    public TaskHelper(AppManager manager) {
        super(manager);
    }

    public void closeOverlayIfPresent() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement overlay = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("div.dialog-overlay")
            ));
            driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);
            wait.until(ExpectedConditions.invisibilityOf(overlay));
        } catch (Exception ignored) {
        }
    }

    public void createTask(TaskData task) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement taskInput = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("input._input_15t7a_19")
        ));
        taskInput.click();
        taskInput.sendKeys(task.getTitle());
        taskInput.sendKeys(Keys.ENTER);
    }
}