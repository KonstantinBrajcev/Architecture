package ru.geekbrains.lesson3;

public class ServiceStation implements Refueling, Wiping{

    @Override
    public void fuel(FuelType fuelType) {

    }

    @Override
    public void wipMirrors() {
        System.out.println("Моем зеркала");
    }

    @Override
    public void wipWindshield() {
        System.out.println("Моем что-то там еще");
    }

    @Override
    public void wipHeadlights() {

    }
}