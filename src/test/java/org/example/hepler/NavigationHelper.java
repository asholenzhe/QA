package org.example.hepler;

import org.example.AppManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class NavigationHelper extends HelperBase {
    private String baseURL;

    public NavigationHelper(AppManager manager, String baseURL) {
        super(manager);
        this.baseURL = baseURL;
    }

    public void openHomePage() {
        driver.get(baseURL + "/ru");
    }

    // Проверяет, залогинены ли мы (по наличию поля ввода задач).
    public boolean isLoggedIn() {
        try {
            return !driver.findElements(
                    By.xpath("//input[@type='text' and not(@name='email') and not(@name='password')]")
            ).isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public void openLoginForm() {
        // Если уже залогинены — ничего не нужно делать
        if (isLoggedIn()) {
            System.out.println("Уже залогинены, форма логина не нужна");
            return;
        }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Ищем кнопку "Войти" по тексту (стабильнее, чем по хеш-классу)
        By[] selectors = {
                By.xpath("//button[normalize-space(text())='Войти']"),
                By.xpath("//button[.//span[normalize-space(text())='Войти']]"),
                By.xpath("//a[normalize-space(text())='Войти']"),
                By.xpath("//*[normalize-space(text())='Войти'][self::button or self::a or self::span]"),
                By.xpath("//button[normalize-space(text())='Log in']"),
                By.xpath("//button[normalize-space(text())='Sign in']"),
        };

        for (By sel : selectors) {
            try {
                WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(sel));
                btn.click();
                System.out.println("Открыли форму логина селектором: " + sel);
                return;
            } catch (Exception ignored) {}
        }

        throw new RuntimeException(
                "Не удалось найти кнопку 'Войти'. URL: " + driver.getCurrentUrl()
        );
    }
}
