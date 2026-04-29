package org.example.hepler;

import org.example.AppManager;
import org.example.data.TaskData;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class TaskHelper extends HelperBase {

    private static final By TASK_INPUT = By.xpath(
            "//input[@type='text' and not(@name='email') and not(@name='password')]"
    );

    private static final By TASK_ITEMS = By.xpath(
            "//*[contains(@class,'_text_') or @data-task-id]" +
                    "[not(ancestor::*[@role='dialog'])]"
    );

    private static final By DELETE_BTN = By.xpath(
            "//div[contains(@class,'tooltip')][.//span[contains(@class,'tooltip__title') and text()='Удалить']]"
    );

    public TaskHelper(AppManager manager) {
        super(manager);
    }

    private WebDriverWait wait(int sec) {
        return new WebDriverWait(driver, Duration.ofSeconds(sec));
    }

    public void closeOverlayIfPresent() {
        try {
            WebElement overlay = wait(3).until(ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector("div.dialog-overlay")));
            driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);
            wait(2).until(ExpectedConditions.invisibilityOf(overlay));
        } catch (Exception ignored) {}
    }

    public void createTask(TaskData task) {
        closeOverlayIfPresent();
        WebElement input = wait(15).until(ExpectedConditions.elementToBeClickable(TASK_INPUT));
        input.click();
        input.sendKeys(task.getTitle());
        input.sendKeys(Keys.ENTER);

        wait(10).until(d -> findTaskByText(task.getTitle()) != null);
    }

    public WebElement findTaskByText(String title) {
        List<WebElement> items = driver.findElements(TASK_ITEMS);
        for (WebElement item : items) {
            try {
                if (!item.isDisplayed()) continue;
                if (title.equals(item.getText().trim())) return item;
            } catch (Exception ignored) {}
        }
        return null;
    }

    public boolean taskExists(String title) {
        return findTaskByText(title) != null;
    }

    public int getTaskCount() {
        return driver.findElements(TASK_ITEMS).size();
    }

    /**
     * Удаление задачи.
     */
    public void deleteTaskByText(String title) {
        WebElement task = findTaskByText(title);
        if (task == null) throw new RuntimeException("Задача не найдена: " + title);

        try {
            tryDeleteViaPanel(task);
            wait(5).until(d -> !taskExists(title));
            return;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

    private void tryDeleteViaPanel(WebElement task) {
        task.click();
        try { Thread.sleep(500); } catch (InterruptedException ignored) {}

        WebElement deleteBtn = wait(5).until(ExpectedConditions.presenceOfElementLocated(DELETE_BTN));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", deleteBtn);
        new Actions(driver).moveToElement(deleteBtn).pause(Duration.ofMillis(200)).click().perform();

        try {
            WebElement confirm = wait(2).until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[normalize-space(text())='Удалить' or .//span[normalize-space(text())='Удалить']]")));
            confirm.click();
        } catch (Exception ignored) {}
    }
}