package org.example.generator;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import org.example.data.TaskData;
import org.example.data.TaskDataList;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class TestDataGenerator {

    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.out.println("Использование: TestDataGenerator <count> <file.xml>");
            return;
        }

        int count = Integer.parseInt(args[0]);
        String filename = args[1];

        // 1. Генерируем случайные задачи
        List<TaskData> tasks = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            String title = generateRandomTitle(random);
            tasks.add(new TaskData(title));
        }

        // 2. Сериализуем в XML через JAXB
        TaskDataList list = new TaskDataList(tasks);
        JAXBContext context = JAXBContext.newInstance(TaskDataList.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        File outFile = new File(filename);
        marshaller.marshal(list, outFile);

        System.out.println("Сгенерировано " + count + " задач в файл " + outFile.getAbsolutePath());
    }

    private static String generateRandomTitle(Random random) {
        // случайная строка из букв, цифр и спецсимволов длиной 5–15 символов
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int length = 5 + random.nextInt(11);
        StringBuilder sb = new StringBuilder("task_");
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }
}
