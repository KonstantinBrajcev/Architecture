package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class PoemReader extends LogReader {

    private String data;
    @Override
    public Object getDataSource() {
        return data;
    }

    @Override
    public void setDataSource(Object data) throws Exception {
        if (data instanceof String) {
            this.data = data.toString();
        } else throw new Exception("Неверный тип данных");
    }

    @Override
    protected Iterable<String> readEntries(Integer position) {
        Scanner scanner = new Scanner(data);
        ArrayList<String> logList = new ArrayList<>();
        int counter = 0;
        while (scanner.hasNextLine()) {
            counter++;
            if (counter >= position) {
                position = counter;
                logList.add(scanner.nextLine());
            } else {
                scanner.nextLine();
            }
        }
        return logList;
    }

    public PoemReader() {

    }

    public PoemReader(String data) {
        this.data = data;

    }



    @Override
    protected LogEntry parseLogEntry(String stringEntry) {
        return new LogEntry(stringEntry);
    }
}