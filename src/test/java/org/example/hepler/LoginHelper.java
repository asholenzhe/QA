package org.example.hepler;

import org.example.AppManager;
import org.example.data.AccountData;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginHelper extends HelperBase {

    private String currentUserEmail;

    public LoginHelper(AppManager manager) {
        super(manager);
    }

    public boolean isLoggedIn() {
        try {
            if (!driver.findElements(By.name("email")).isEmpty()) return false;
            if (!driver.findElements(By.name("password")).isEmpty()) return false;

            By[] loginButtons = {
                    By.xpath("//button[normalize-space(text())='Войти']"),
                    By.xpath("//a[normalize-space(text())='Войти']"),
                    By.xpath("//button[.//span[normalize-space(text())='Войти']]"),
                    By.xpath("//button[normalize-space(text())='Log in']"),
                    By.xpath("//button[normalize-space(text())='Sign in']"),
            };
            for (By sel : loginButtons) {
                if (!driver.findElements(sel).isEmpty()) return false;
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLoggedIn(String email) {
        return isLoggedIn() && email != null && email.equals(currentUserEmail);
    }


    public void login(AccountData user) {
        if (isLoggedIn()) {
            if (isLoggedIn(user.getEmail())) {
                return;
            }
            logout();
        }
        manager.getNavigation().openLoginForm();
        boolean success = performLogin(user);
        if (success) {
            currentUserEmail = user.getEmail();
        } else {
            currentUserEmail = null;
        }
    }


    private boolean performLogin(AccountData user) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement emailInput = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.name("email")));
        emailInput.sendKeys(user.getEmail());

        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.click();
        passwordInput.sendKeys(user.getPassword());

        driver.findElement(By.cssSelector("._fullWidth_w81oo_20")).click();

        try {
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(d -> isLoggedIn());
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public void logout() {
        try {
            driver.manage().deleteAllCookies();
        } catch (Exception ignored) {}
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("try{window.localStorage.clear();}catch(e){}");
            js.executeScript("try{window.sessionStorage.clear();}catch(e){}");
        } catch (Exception ignored) {}

        manager.getNavigation().openHomePage();
        currentUserEmail = null;

        // Ждём, пока страница реально перерендерится в logged-out state
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(d -> !isLoggedIn());
        } catch (Exception ignored) {}
    }
}