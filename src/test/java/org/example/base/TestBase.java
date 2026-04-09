package org.example.base;

import org.example.data.AccountData;
import org.example.data.TaskData;
import org.junit.Before;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.*;

public class TestBase {
    protected WebDriver driver;
    protected Map<String, Object> vars;
    protected JavascriptExecutor js;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<>();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    public void openHomePage() {
        driver.get("https://tweek.so/ru");
        driver.manage().window().setSize(new Dimension(1054, 800));
    }

    public void openLoginForm() {
        driver.findElement(By.cssSelector("._variantOutlined_w81oo_35 > span")).click();
    }

    public void login(AccountData user) {
        driver.findElement(By.name("email")).sendKeys(user.getEmail());
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).sendKeys(user.getPassword());
        driver.findElement(By.cssSelector("._fullWidth_w81oo_20")).click();
    }

    public void closeOverlayIfPresent() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement overlay = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div.dialog-overlay")
            ));
            driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);
            wait.until(ExpectedConditions.invisibilityOf(overlay));
        } catch (Exception e) {
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
