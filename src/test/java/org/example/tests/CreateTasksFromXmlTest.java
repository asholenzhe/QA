package org.example.tests;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import org.example.AppManager;
import org.example.data.AccountData;
import org.example.data.TaskData;
import org.example.data.TaskDataList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Параметризованный тест: запускается отдельно для каждой задачи из XML.
 */
@RunWith(Parameterized.class)
public class CreateTasksFromXmlTest {

    protected AppManager app;
    private final TaskData task; // данные текущего прогона

    public CreateTasksFromXmlTest(TaskData task) {
        this.task = task;
    }

    @Before
    public void setUp() {
        app = AppManager.getInstance();
    }


    @Parameterized.Parameters(name = "{index}: task={0}")
    public static Collection<Object[]> dataFromXml() throws Exception {
        File xmlFile = new File("tasks.xml");
        JAXBContext context = JAXBContext.newInstance(TaskDataList.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        TaskDataList list = (TaskDataList) unmarshaller.unmarshal(xmlFile);

        // упаковываем каждую задачу в Object[] — это формат, ожидаемый JUnit
        List<Object[]> params = new ArrayList<>();
        for (TaskData t : list.getTasks()) {
            params.add(new Object[]{t});
        }
        return params;
    }

    @Test
    public void createTaskFromXml() {
        AccountData user = new AccountData("enzhe.ashrafullina@mail.ru", "A11r1ght!");
        app.ensureLoggedIn(user);

        TaskData uniqueTask = new TaskData(task.getTitle() + "_" + System.currentTimeMillis());
        app.getTask().createTask(uniqueTask);

        assertTrue("Задача '" + uniqueTask.getTitle() + "' должна быть создана",
                app.getTask().taskExists(uniqueTask.getTitle()));
    }
}
