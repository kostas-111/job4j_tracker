package ru.job4j.cast;

public class VehicleMain {
    public static void main(String[] args) {
        Vehicle airplaneBoeing = new Airplane();
        Vehicle airplaneSJ = new Airplane();
        Vehicle train1 = new Train();
        Vehicle train2 = new Train();
        Vehicle busScania = new Bus();
        Vehicle busVolvo = new Bus();
        Vehicle[] vehicles = {airplaneBoeing, airplaneSJ, train1, train2, busScania, busVolvo};
        for (Vehicle object : vehicles) {
            object.move();
        }
    }
}