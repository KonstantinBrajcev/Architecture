package ru.geekbrains.lesson3;

import java.awt.*;

public class Program {


    public static void main(String[] args) {
        // 1. Создаем автомобили разных типов
        Car sportCar = SportCar.create("A", CarType.Sport, Color.BLACK, 3);
        Car harvester = new Harvester("B", CarType.Hatchback, Color.BLUE);
        Car threeWheeler = new ThreeWheelerCar("Reliant", CarType.Hatchback, Color.YELLOW);
        // 2. Создаем сервисы
        RefuelingStation refuelingStation = new RefuelingStation();
        PremiumCarWash carWash = new PremiumCarWash();
        FullServiceStation fullService = new FullServiceStation();
        // 3. Демонстрация работы с каждым автомобилем
        testCar(sportCar, refuelingStation, carWash, fullService);
        testCar(harvester, refuelingStation, carWash, fullService);
        testCar(threeWheeler, refuelingStation, carWash, fullService);
    }
    
    private static void testCar(Car car, RefuelingStation refueling, 
                              CarWash wash, EnhancedServiceStation service) {
        System.out.println("\n=== Тестируем " + car.getClass().getSimpleName() + " ===");
        System.out.println("Марка: " + car.getMake() + ", Цвет: " + car.getColor());

        // Базовые функции
        System.out.println("1. Движение:");
        car.movement();

        System.out.println("2. Обслуживание:");
        car.maintenance();

        // Проверка SRP
        System.out.println("3. Уборка улиц:");
        car.sweepStreet();

        // Проверка OCP
        System.out.println("4. Противотуманные фары:");
        car.switchFogLights(); // Включить
        car.switchFogLights(); // Выключить

        System.out.println("5. Перевозка груза:");
        car.transportCargo();

        // Работа с заправкой
        System.out.println("6. Заправка:");
        car.setRefuelingStation(refueling);
        car.fuel();

        // Работа с мойкой
        System.out.println("7. Автомойка:");
        car.setCarWash(wash);
        car.washCar();

        // Полное обслуживание
        System.out.println("8. Полное обслуживание:");
        car.setEnhancedServiceStation(service);
        car.fullService();

        // Проверка LSP
        System.out.println("9. Характеристики:");
        System.out.println("   - Количество колес: " + car.getWheelsCount());
        System.out.println("   - Стоимость обслуживания: " + calculateMaintenance(car));
    }

    public static double calculateMaintenance(Car car) {
        if (car.getWheelsCount() == 6) {
            return 1500 * 6;
        } else if (car.getWheelsCount() == 3) {
            return 800 * 3;
        } else {
            return 1000 * 4;
        }
    }
}