package com.example.sphere.model;

import com.example.sphere.exceptions.InvalidSphereRadius;
import com.example.sphere.observer.Observable;
import com.example.sphere.observer.SphereObserver;
import com.example.sphere.utils.AutoIncrement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Sphere implements Observable {
    private static final Logger logger = LogManager.getLogger(Sphere.class);
    private final long id = AutoIncrement.nextId();
    private Point position;
    private double radius;
    private SphereObserver observer = new SphereObserver();

    public Sphere(Point position, double radius) throws InvalidSphereRadius {
        this.position = position;
        setRadius(radius);
    }

    public static ArrayList<Sphere> parseSpheres(List<String> lines) {
        var spheres = new ArrayList<Sphere>();

        for (String line : lines) {
            try {
                var parts = line.split(";");

                var x = Double.parseDouble(parts[0]);
                var y = Double.parseDouble(parts[1]);
                var z = Double.parseDouble(parts[2]);

                var radius = Double.parseDouble(parts[3]);

                var position = new Point(x, y, z);
                var sphere = new Sphere(position, radius);

                spheres.add(sphere);
            } catch (NumberFormatException | InvalidSphereRadius e) {
                logger.error("Failed to parse a sphere", e);
            }
        }

        return spheres;
    }

    public long getId() {
        return id;
    }

    public void setRadius(double radius) throws InvalidSphereRadius {
        if (radius < 0.0) {
            throw new InvalidSphereRadius();
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
