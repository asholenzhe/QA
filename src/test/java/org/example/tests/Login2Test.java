package org.example.tests;

import org.example.base.TestBase;
import org.example.data.AccountData;
import org.junit.Test;

public class Login2Test extends TestBase {

    @Test
    public void login2() {
        AccountData user = new AccountData("enzhe.ashrafullina@mail.ru", "A11r1ght!");

        openHomePage();
        openLoginForm();
        login(user);
    }
}
