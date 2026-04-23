package org.example.hepler;

import org.example.AppManager;
import org.example.data.AccountData;
import org.openqa.selenium.By;

public class LoginHelper extends HelperBase {

    public LoginHelper(AppManager manager) {
        super(manager);
    }

    public void login(AccountData user) {
        driver.findElement(By.name("email")).sendKeys(user.getEmail());
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).sendKeys(user.getPassword());
        driver.findElement(By.cssSelector("._fullWidth_w81oo_20")).click();
    }
}