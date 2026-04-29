package org.example;

import org.example.data.AccountData;
import org.example.hepler.LoginHelper;
import org.example.hepler.NavigationHelper;
import org.example.hepler.TaskHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AppManager {

    private static ThreadLocal<AppManager> app = new ThreadLocal<>();

    private WebDriver driver;
    private NavigationHelper navigation;
    private LoginHelper auth;
    private TaskHelper task;
    private String baseURL;

    private AppManager() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        baseURL = "https://tweek.so";

        navigation = new NavigationHelper(this, baseURL);
        auth = new LoginHelper(this);
        task = new TaskHelper(this);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                driver.quit();
            } catch (Exception ignored) {}
        }));
    }

    public static AppManager getInstance() {
        if (app.get() == null) {
            AppManager newInstance = new AppManager();
            newInstance.getNavigation().openHomePage();
            app.set(newInstance);
        }
        return app.get();
    }

    public void ensureLoggedIn(AccountData user) {
        if (navigation.isLoggedIn()) {
            task.closeOverlayIfPresent();
            return;
        }
        navigation.openHomePage();
        if (navigation.isLoggedIn()) {
            task.closeOverlayIfPresent();
            return;
        }
        navigation.openLoginForm();
        auth.login(user);
        task.closeOverlayIfPresent();
    }

    public WebDriver getDriver() { return driver; }
    public NavigationHelper getNavigation() { return navigation; }
    public LoginHelper getAuth() { return auth; }
    public TaskHelper getTask() { return task; }
}
