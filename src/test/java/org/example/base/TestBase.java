package org.example.base;

import org.example.AppManager;
import org.junit.Before;

/**
 * Базовый класс для всех тестов.
 * Здесь — только общее: получаем экземпляр менеджера и открываем домашнюю страницу.
 * Авторизации тут НЕТ — её делает AuthBase для тестов, которым она нужна как предусловие.
 */
public class TestBase {

    protected AppManager app;

    @Before
    public void setUp() {
        app = AppManager.getInstance();
        app.getNavigation().openHomePage();
    }
}