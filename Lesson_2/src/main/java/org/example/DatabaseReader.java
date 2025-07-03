package org.example;

import java.util.ArrayList;
import java.util.List;

public class DatabaseReader extends LogReader{
    private Object data;

    @Override
    public Object getDataSource() {
        return data;
    }

    @Override
    public void setDataSource(Object data) throws Exception {
        if (data instanceof String) {
            this.data = data;
        } else {
            throw new Exception("DataSource должен быть строкой с подключением к БД");
        }
    }

    @Override
    protected Iterable<String> readEntries(Integer position) {
        // Здесь должна быть реализация чтения из БД
        // Временная заглушка:
        List<String> entries = new ArrayList<>();
        entries.add("Database log entry 1");
        entries.add("Database log entry 2");
        return entries;
    }

    @Override
    protected LogEntry parseLogEntry(String stringEntry) {
        return new LogEntry("[DB] " + stringEntry);
    }
}