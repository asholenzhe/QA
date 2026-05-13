package org.example.tests;

import org.example.base.AuthBase;
import org.example.data.TaskData;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Тест удаления задачи.
 * Использует авторизацию как ПРЕДУСЛОВИЕ — поэтому AuthBase.
 */
public class DeleteTest extends AuthBase {

    @Test
    public void delete() {
        TaskData task = new TaskData("test_delete_" + System.currentTimeMillis());

        app.getTask().createTask(task);
        assertTrue("Задача должна быть создана перед удалением",
                app.getTask().taskExists(task.getTitle()));

        app.getTask().deleteTaskByText(task.getTitle());

        assertFalse("Задача '" + task.getTitle() + "' должна быть удалена",
                app.getTask().taskExists(task.getTitle()));
    }
}