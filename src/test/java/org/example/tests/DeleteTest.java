package org.example.tests;

import org.example.base.TestBase;
import org.example.data.AccountData;
import org.example.data.TaskData;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DeleteTest extends TestBase {

    @Test
    public void delete() {
        AccountData user = new AccountData("enzhe.ashrafullina@mail.ru", "A11r1ght!");
        TaskData task = new TaskData("test_delete_" + System.currentTimeMillis());

        app.ensureLoggedIn(user);
        app.getTask().createTask(task);

        // убедимся, что создание прошло
        assertTrue("Задача должна быть создана перед удалением",
                app.getTask().taskExists(task.getTitle()));

        app.getTask().deleteTaskByText(task.getTitle());

        // ASSERT: задачи с этим текстом больше нет
        assertFalse("Задача '" + task.getTitle() + "' должна быть удалена",
                app.getTask().taskExists(task.getTitle()));
    }
}
