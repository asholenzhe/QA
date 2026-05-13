package org.example.tests;

import org.example.base.AuthBase;
import org.example.data.TaskData;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Тест создания задачи.
 * Использует авторизацию как ПРЕДУСЛОВИЕ — поэтому AuthBase.
 * Сам объявлять AccountData и логиниться больше не нужно.
 */
public class CreateTest extends AuthBase {

    @Test
    public void create() {
        TaskData task = new TaskData("test_create_" + System.currentTimeMillis());

        app.getTask().createTask(task);

        assertTrue("Задача '" + task.getTitle() + "' должна быть создана",
                app.getTask().taskExists(task.getTitle()));
    }
}