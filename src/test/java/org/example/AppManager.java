package org.example;

import org.example.hepler.LoginHelper;
import org.example.hepler.NavigationHelper;
import org.example.hepler.TaskHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AppManager {  //главный управляющий класс, который создаёт driver и все helper-классы
    private WebDriver driver;

    private NavigationHelper navigation;
    private LoginHelper auth;
    private TaskHelper task;

    private String baseURL;

    public AppManager() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        baseURL = "https://tweek.so";

        navigation = new NavigationHelper(this, baseURL);
        auth = new LoginHelper(this);
        task = new TaskHelper(this);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public NavigationHelper getNavigation() {
        return navigation;
    }

    public LoginHelper getAuth() {
        return auth;
    }

    public TaskHelper getTask() {
        return task;
    }

    public void stop() {
        driver.quit();
    }
}