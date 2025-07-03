package ru.geekbrains.lesson3;

public interface EnhancedServiceStation extends Refueling, Wiping, CarWash {
    void checkEngine();
    void checkSuspension();
}