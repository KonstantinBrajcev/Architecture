package ru.geekbrains.lesson3;

import java.awt.*;

public class SportCar extends Car{


    private SportCar(String make, CarType model, Color color, int wheelsCount) {
        super(make, model, color);
        super.wheelsCount = wheelsCount;
    }

    public static SportCar create(String make, CarType model, Color color){

        return create(make, CarType.Sport, color, 4);
    }

    public static SportCar create(String make, CarType model, Color color, int wheelsCount){
        if (wheelsCount < 3 || wheelsCount > 10){
            throw new RuntimeException("Недопустимое кол-во колес.");
        }
        return new SportCar(make, CarType.Sport, color, wheelsCount);
    }

    private boolean fogLights = false;

    @Override
    public void movement() {
        System.out.println("The sport car moves fast");

    }

    @Override
    public void maintenance() {
        System.out.println("The sport car is maintained");

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
        System.out.println("Спорткар не может подметать улицы - функция недоступна");
    }

    @Override
    public void switchFogLights() {
        System.out.println("Противотуманные фары спортивного автомобиля " +
                (fogLights ? "выключены" : "включены"));
        fogLights = !fogLights;
    }

    @Override
    public void transportCargo() {
        System.out.println("Спорткар может перевозить только небольшие грузы");
    }
}