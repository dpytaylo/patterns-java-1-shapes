package com.example.sphere.model;

import java.util.HashMap;

public class Warehouse {
    private static final Warehouse instance = new Warehouse();
    private final HashMap<Long, Double> map = new HashMap<>();

    private Warehouse() {}

    public static Warehouse getInstance() {
        return instance;
    }

    public void put(long id, double area) {
        map.put(id, area);
    }

    public Double get(long id) {
        return map.get(id);
    }
}
