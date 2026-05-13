package org.example.base;

import org.example.data.AccountData;
import org.example.settings.Settings;
import org.junit.Before;

/**
 * Базовый класс для тестов, которые НЕ проверяют авторизацию,
 * но используют её как предусловие (создание задач, удаление и т.п.).
 *
 * Важно про порядок:
 * JUnit 4 сначала вызывает @Before родителя (TestBase.setUp), а потом @Before наследника.
 * Поэтому к моменту authenticate() поле app уже инициализировано.
 */
public class AuthBase extends TestBase {

    @Before
    public void authenticate() {
        AccountData user = new AccountData(Settings.getLogin(), Settings.getPassword());
        app.getAuth().login(user);
        app.getTask().closeOverlayIfPresent();
    }
}