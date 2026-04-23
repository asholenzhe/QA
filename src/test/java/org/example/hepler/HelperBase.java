package org.example.hepler;

import org.example.AppManager;
import org.openqa.selenium.WebDriver;

public class HelperBase { //общая база для всех helper-классов
    protected AppManager manager;
    protected WebDriver driver;

    public HelperBase(AppManager manager) {
        this.manager = manager;
        this.driver = manager.getDriver();
    }
}