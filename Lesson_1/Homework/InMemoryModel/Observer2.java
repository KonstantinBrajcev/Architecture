package Lesson_1.Homework.InMemoryModel;

public class Observer2 implements ModelChangedObserver{
    @Override
    public void applyUpdateModel() {
        System.out.println("Observer2 реагирует на изменения ...");
    }
}