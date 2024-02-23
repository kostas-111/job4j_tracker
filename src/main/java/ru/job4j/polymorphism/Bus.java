package ru.job4j.polymorphism;

public class Bus implements Transport {
    @Override
    public void go() {
        System.out.println("Автобус начал движение.");
    }

    @Override
    public void passengers(int number) {
        System.out.println("Количество пассажиров в автобусе: " + number);

    }

    @Override
    public double refuel(double fuelAmount) {
        double fuelPrice = 60.35;
        return fuelAmount * fuelPrice;
    }
}