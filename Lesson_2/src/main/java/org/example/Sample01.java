package org.example;

public class Sample01 {

    public static String data = """
            У лукоморья дуб зеленый;
            Златая цепь на дубе том:
            И днем и ночью и кот ученый
            Все ходит по цепи кругом;""";

    public static void main(String[] args) {
        LogReader logReader = new PoemReader(data);
        logReader.setCurrentPosition(2);
        for (LogEntry log: logReader.readLogEntry()) {
            System.out.println(log.getText());
        }
    }
}