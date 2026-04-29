package org.example.tests;

import org.example.base.TestBase;
import org.example.data.AccountData;
import org.example.data.TaskData;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CreateTest extends TestBase {

    @Test
    public void create() {
        AccountData user = new AccountData("enzhe.ashrafullina@mail.ru", "A11r1ght!");
        TaskData task = new TaskData("test_create_" + System.currentTimeMillis());

        app.ensureLoggedIn(user);
        app.getTask().createTask(task);

        // ASSERT: задача с уникальным текстом действительно появилась в списке
        assertTrue("Задача '" + task.getTitle() + "' должна быть создана",
                app.getTask().taskExists(task.getTitle()));
    }
}