package com.example.sphere.service;

import com.example.sphere.model.Point;
import com.example.sphere.model.Sphere;

public class SphereService {
    public boolean containsPoint(Sphere sphere, Point point) {
        var position = sphere.getPosition();
        var radius = sphere.getRadius();

        return Math.abs(position.getX() - point.getX()) <= radius
            && Math.abs(position.getY() - point.getY()) <= radius
            && Math.abs(position.getZ() - point.getZ()) <= radius;
    }

    public double getArea(Sphere sphere) {
        return 4.0 * Math.PI * Math.pow(sphere.getRadius(), 2.0);
    }
}
