package org.example.hepler;

import org.example.AppManager;
import org.openqa.selenium.By;

public class NavigationHelper extends HelperBase { //отвечает за переходы по страницам и открытие нужных экранов.
    private String baseURL;

    public NavigationHelper(AppManager manager, String baseURL) {
        super(manager);
        this.baseURL = baseURL;
    }

    public void openHomePage() {
        driver.get(baseURL + "/ru");
    }

    public void openLoginForm() {
        driver.findElement(By.cssSelector("._variantOutlined_w81oo_35 > span")).click();
    }
}