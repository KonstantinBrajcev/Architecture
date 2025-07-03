package org.example;

/**
 * Запись лога
 */
public class LogEntry {
    private final String text;

    public String getText() {
        return text;
    }

    public LogEntry(String text) {
        this.text = text;
    }
}