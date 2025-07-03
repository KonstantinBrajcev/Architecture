package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Sample03 {
    public static void main(String[] args) {
        try {
            // Создаем временный текстовый файл для демонстрации
            File tempFile = createTempTextFile();

            // Создаем фабрику
            BaseLogReaderCreator creator = new ConcreteReaderCreator();

            // 1. Тестируем PoemReader
            LogReader poemReader = creator.createLogReader(LogType.Poem, Sample01.data);
            System.out.println("=== Poem Reader ===");
            printLogEntries(poemReader);

            // 2. Тестируем TextFileReader с временным файлом
            LogReader textReader = creator.createLogReader(LogType.Text, tempFile.getAbsolutePath());
            System.out.println("\n=== Text Reader ===");
            printLogEntries(textReader);

            // 3. Тестируем DatabaseReader
            LogReader dbReader = creator.createLogReader(LogType.Database, "jdbc:mysql://localhost:3306/test");
            System.out.println("\n=== Database Reader ===");
            printLogEntries(dbReader);

            // 4. Тестируем SystemReader
            LogReader sysReader = creator.createLogReader(LogType.System, "system-log-identifier");
            System.out.println("\n=== System Reader ===");
            printLogEntries(sysReader);

            // Удаляем временный файл
            tempFile.delete();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printLogEntries(LogReader reader) {
        for (LogEntry log : reader.readLogEntry()) {
            System.out.println(log.getText());
        }
    }

    private static File createTempTextFile() throws IOException {
        File tempFile = File.createTempFile("log-example", ".txt");
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("Первая строка лога\n");
            writer.write("Вторая строка лога\n");
            writer.write("Третья строка лога\n");
        }
        return tempFile;
    }
}