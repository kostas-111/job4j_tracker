package ru.job4j.tracker;

import java.util.ArrayList;

public final class SingleTracker {

    private final Tracker tracker = new Tracker();

    private static SingleTracker instance = null;

    private SingleTracker() {
    }

    public static SingleTracker getInstance() {
        if (instance == null) {
            instance = new SingleTracker();
        }
        return instance;
    }

    public Item add(Item item) {
        return tracker.add(item);
    }

    public Item findById(int id) {
        return tracker.findById(id);
    }

    public ArrayList<Item> findAll() {
        return tracker.findAll();
    }

    public  ArrayList<Item> findByName(String key) {
        return tracker.findByName(key);
    }
}