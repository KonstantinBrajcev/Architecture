package org.example;

import java.util.ArrayList;
import java.util.List;

public class OperationSystemLogEventsReader extends LogReader {
    private Object data;

    @Override
    public Object getDataSource() {
        return data;
    }

    @Override
    public void setDataSource(Object data) throws Exception {
        this.data = data; // Может быть путем к файлу логов или другим идентификатором
    }

    @Override
    protected Iterable<String> readEntries(Integer position) {
        // Здесь должна быть реализация чтения системных логов
        // Временная заглушка:
        List<String> entries = new ArrayList<>();
        entries.add("System log entry 1");
        entries.add("System log entry 2");
        return entries.subList(position, entries.size());
    }

    @Override
    protected LogEntry parseLogEntry(String stringEntry) {
        return new LogEntry("[SYS] " + stringEntry);
    }
}