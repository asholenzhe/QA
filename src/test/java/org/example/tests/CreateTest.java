package org.example.tests;

import org.example.base.TestBase;
import org.example.data.AccountData;
import org.example.data.TaskData;
import org.junit.Test;

public class CreateTest extends TestBase {

    @Test
    public void create() {
        AccountData user = new AccountData("enzhe.ashrafullina@mail.ru", "A11r1ght!");
        TaskData task = new TaskData("sleep all day");

        app.getNavigation().openHomePage();
        app.getNavigation().openLoginForm();
        app.getAuth().login(user);
        app.getTask().closeOverlayIfPresent();
        app.getTask().createTask(task);
    }
}