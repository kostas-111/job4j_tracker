package ru.job4j.polymorphism;

public interface Transport {
    void go();

    void passengers(int number);

    double refuel(double fuelAmount);
}