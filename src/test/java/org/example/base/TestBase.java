package org.example.base;

import org.example.AppManager;
import org.junit.Before;

public class TestBase { // базовый класс для тестов

    protected AppManager app;

    @Before
    public void setUp() {
        app = AppManager.getInstance();
    }
}
