package org.example;

public class ConcreteReaderCreator extends  BaseLogReaderCreator{

    /**
     * Фабричный метод
     *
     * @param logType
     * @return
     */
    @Override
    protected LogReader createLogReaderInstance(LogType logType) {
        return switch (logType) {
            case Text -> new TextFileReader();
            case Poem -> new PoemReader();
            case Database -> new DatabaseReader();
            case System -> new OperationSystemLogEventsReader();
        };
    }
}