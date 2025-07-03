package Lesson_1.Homework.InMemoryModel;

public class Observer1 implements ModelChangedObserver{
    @Override
    public void applyUpdateModel() {
        System.out.println("Observer1 реагирует на изменения ...");
    }
}