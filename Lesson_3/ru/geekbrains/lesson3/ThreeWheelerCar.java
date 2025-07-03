package ru.geekbrains.lesson3;

import java.awt.*;

public class ThreeWheelerCar extends Car {
    public ThreeWheelerCar(String make, CarType model, Color color) {
        super(make, model, color);
        wheelsCount = 3;
    }

    private boolean fogLights = false;

    @Override
    public void movement() {
        System.out.println("Трехколесный автомобиль движется нестабильно");
    }

    @Override
    public void maintenance() {
        System.out.println("Обслуживание трехколесного автомобиля");
    }

        @Override
    public boolean gearShifting() {
        return false;
    }

    @Override
    public boolean switchHeadlights() {
        return false;
    }

    @Override
    public boolean switchWipers() {
        return false;
    }

    @Override
    public void sweepStreet() {
        System.out.println("Трехколесный автомобиль не предназначен для уборки улиц");
    }

    @Override
    public void switchFogLights() {
        System.out.println("Противотуманки трехколесного автомобиля " +
                (fogLights ? "выключены" : "включены"));
        fogLights = !fogLights;
    }

    @Override
    public void transportCargo() {
        System.out.println("Трехколесный автомобиль перевозит небольшой груз");
    }

}