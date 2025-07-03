package ru.geekbrains.lesson3;

public class FullServiceStation implements EnhancedServiceStation {
    @Override
    public void fuel(FuelType fuelType) {
        System.out.println("Заправка " + fuelType + " на полной станции");
    }

    @Override
    public void wipMirrors() {
        System.out.println("Чистка зеркал с полиролью");
    }

    @Override
    public void wipWindshield() {
        System.out.println("Мойка лобового стекла с антидождем");
    }

    @Override
    public void wipHeadlights() {
        System.out.println("Полировка фар");
    }

    @Override
    public void washBody() {
        System.out.println("Мойка кузова на полной станции");
    }

    @Override
    public void cleanInterior() {
        System.out.println("Чистка салона на полной станции");
    }

    @Override
    public void checkEngine() {
        System.out.println("Диагностика двигателя");
    }

    @Override
    public void checkSuspension() {
        System.out.println("Проверка подвески");
    }
}