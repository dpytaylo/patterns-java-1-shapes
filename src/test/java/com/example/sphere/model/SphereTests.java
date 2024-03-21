package com.example.sphere.model;

import com.example.sphere.exception.InvalidSphereException;
import com.example.sphere.service.SphereService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SphereTests {
    @Test
    void testIsValidSphere() throws InvalidSphereException {
        new Sphere(new Point(), 12.0);

        assertThrowsExactly(InvalidSphereException.class, () -> new Sphere(new Point(), -12.0));
    }

    @Test
    void testContainsPoint() throws InvalidSphereException {
        var sphereService = new SphereService();

        var sphere = new Sphere(new Point(), 12.0);
        var point = new Point(10.0, 10.0, 10.0);
        assertTrue(sphereService.containsPoint(sphere, point));

        point = new Point(20.0, 20.0, 20.0);
        assertFalse(sphereService.containsPoint(sphere, point));
    }

    @Test
    void testArea() throws InvalidSphereException {
        var sphereService = new SphereService();

        var sphere = new Sphere(new Point(), 12.0);
        assertEquals(4.0 * Math.PI * Math.pow(12.0, 2.0), sphereService.calculateArea(sphere));
    }
}
