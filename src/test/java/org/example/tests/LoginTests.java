package org.example.tests;

import org.example.base.TestBase;
import org.example.data.AccountData;
import org.example.settings.Settings;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class LoginTests extends TestBase {

    @Test
    public void loginWithValidData() {
        app.getAuth().logout();

        AccountData user = new AccountData(Settings.getLogin(), Settings.getPassword());
        app.getAuth().login(user);

        assertTrue("После login с валидными данными пользователь должен быть залогинен",
                app.getAuth().isLoggedIn());
    }

    @Test
    public void loginWithInvalidData() {
        app.getAuth().logout();

        AccountData user = new AccountData("definitely_wrong_user@example.com", "wrong_password_123");
        try {
            app.getAuth().login(user);
        } catch (Exception ignored) {

        }

        assertFalse("После login с невалидными данными пользователь не должен быть залогинен",
                app.getAuth().isLoggedIn());
    }
}