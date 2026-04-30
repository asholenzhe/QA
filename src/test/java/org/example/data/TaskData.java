package org.example.data;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "TaskData")
@XmlAccessorType(XmlAccessType.FIELD)
public class TaskData {

    @XmlElement
    private String title;

    // Пустой конструктор обязателен для JAXB
    public TaskData() {}

    public TaskData(String title) {
        this.title = title;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
}
