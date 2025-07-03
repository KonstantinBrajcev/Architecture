package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TextFileReader extends LogReader {
    private String filePath;

    @Override
    public Object getDataSource() {
        return filePath;
    }

    @Override
    public void setDataSource(Object data) throws Exception {
        if (data instanceof String) {
            this.filePath = (String) data;
        } else {
            throw new Exception("DataSource должен быть строкой с путем к файлу");
        }
    }

    @Override
    protected Iterable<String> readEntries(Integer position) {
        List<String> entries = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int currentLine = 0;
            while ((line = reader.readLine()) != null) {
                if (currentLine++ >= position) {
                    entries.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entries;
    }

    @Override
    protected LogEntry parseLogEntry(String stringEntry) {
        return new LogEntry("[TXT] " + stringEntry);
    }
}