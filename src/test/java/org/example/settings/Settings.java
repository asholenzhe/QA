package org.example.settings;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public final class Settings {

    private static final String FILE = "Settings.xml";

    private static String baseUrl;
    private static String login;
    private static String password;

    private static final Document document;

    static {
        File file = new File(FILE);
        if (!file.exists()) {
            throw new RuntimeException("Problem: settings file not found: " + FILE);
        }
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(file);
            document.getDocumentElement().normalize();
        } catch (Exception e) {
            throw new RuntimeException("Не удалось прочитать " + FILE, e);
        }
    }

    private Settings() {
        // запрещаем создание экземпляров
    }

    public static String getBaseUrl() {
        if (baseUrl == null) {
            baseUrl = readNode("BaseUrl");
        }
        return baseUrl;
    }

    public static String getLogin() {
        if (login == null) {
            login = readNode("Login");
        }
        return login;
    }

    public static String getPassword() {
        if (password == null) {
            password = readNode("Password");
        }
        return password;
    }

    private static String readNode(String tag) {
        Node node = document.getDocumentElement().getElementsByTagName(tag).item(0);
        if (node == null) {
            throw new RuntimeException("В Settings.xml нет узла <" + tag + ">");
        }
        return node.getTextContent().trim();
    }
}