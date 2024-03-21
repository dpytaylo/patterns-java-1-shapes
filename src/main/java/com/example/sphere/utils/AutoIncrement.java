package com.example.sphere.utils;

public class AutoIncrement {
    private static long ID = 0;

    public static long nextId() {
        return ID++;
    }
}
