package org.example.tests;

import org.example.base.TestBase;
import org.example.data.AccountData;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class Login2Test extends TestBase {

    @Test
    public void login2() {
        AccountData user = new AccountData("enzhe.ashrafullina@mail.ru", "A11r1ght!");

        // ensureLoggedIn сам решает: логиниться или мы уже залогинены.
        // Прямой auth.login() использовать нельзя — после логина в другом тесте формы уже нет.
        app.ensureLoggedIn(user);

        // ASSERT: после ensureLoggedIn пользователь действительно в системе
        assertTrue("После ensureLoggedIn пользователь должен быть залогинен",
                app.getNavigation().isLoggedIn());
    }
}