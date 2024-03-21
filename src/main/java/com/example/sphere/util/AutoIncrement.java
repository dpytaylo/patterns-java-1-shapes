package com.example.sphere.util;

public class AutoIncrement {
    private static long ID = 0;

    public static long nextId() {
        return ID++;
    }
}
