package org.example.data;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

// Обёртка нужна, чтобы JAXB корректно работал со списком на верхнем уровне.
// Соответствует <ArrayOfTaskData> из примера в инструкции.
@XmlRootElement(name = "ArrayOfTaskData")
@XmlAccessorType(XmlAccessType.FIELD)
public class TaskDataList {

    @XmlElement(name = "TaskData")
    private List<TaskData> tasks = new ArrayList<>();

    public TaskDataList() {}

    public TaskDataList(List<TaskData> tasks) {
        this.tasks = tasks;
    }

    public List<TaskData> getTasks() { return tasks; }
    public void setTasks(List<TaskData> tasks) { this.tasks = tasks; }
}
