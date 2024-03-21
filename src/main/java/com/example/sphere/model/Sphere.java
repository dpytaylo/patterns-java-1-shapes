package com.example.sphere.model;

import com.example.sphere.exception.InvalidSphereException;
import com.example.sphere.observer.Observable;
import com.example.sphere.observer.SphereObserver;
import com.example.sphere.util.AutoIncrement;

public class Sphere implements Observable {
    private final long id = AutoIncrement.nextId();
    private Point position;
    private double radius;
    private SphereObserver observer = new SphereObserver();

    public Sphere(Point position, double radius) throws InvalidSphereException {
        this.position = position;
        setRadius(radius);
    }

    public long getId() {
        return id;
    }

    public void setRadius(double radius) throws InvalidSphereException {
        if (radius < 0.0) {
            throw new InvalidSphereException("Invalid radius: " + radius + " (expected: >= 0.0)");
        }

        this.radius = radius;
        notifyObservers();
    }

    public double getRadius() {
        return radius;
    }

    public void setPosition(Point position) {
        this.position = position;
        notifyObservers();
    }

    public Point getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "Sphere{" +
            "id=" + id +
            ", position=" + position +
            ", radius=" + radius +
            '}';
    }

    @Override
    public void attach() {
        observer = new SphereObserver();
    }

    @Override
    public void detach() {
        observer = null;
    }

    @Override
    public void notifyObservers() {
        if (observer != null) {
            observer.update(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sphere sphere = (Sphere) o;

        if (id != sphere.id) return false;
        if (Double.compare(radius, sphere.radius) != 0) return false;
        return position.equals(sphere.position);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + position.hashCode();
        temp = Double.doubleToLongBits(radius);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
