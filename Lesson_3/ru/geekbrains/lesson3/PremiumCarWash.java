// PremiumCarWash.java
package ru.geekbrains.lesson3;

public class PremiumCarWash implements CarWash {
    @Override
    public void washBody() {
        System.out.println("Премиум мойка кузова");
    }

    @Override
    public void cleanInterior() {
        System.out.println("Химчистка салона");
    }
}
