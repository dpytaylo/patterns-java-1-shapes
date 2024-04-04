package com.example.sphere.main;

import com.example.sphere.exception.InvalidSphereException;
import com.example.sphere.exception.SphereFactoryException;
import com.example.sphere.factory.SphereFactory;
import com.example.sphere.model.Warehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SphereApplication {
    private static final Logger logger = LogManager.getLogger(SphereApplication.class);
    private static final String SPHERE_FILENAME = "src/main/resources/spheres.txt";

    public static void main(String[] args) throws SphereFactoryException, InvalidSphereException {
        var sphereFactory = new SphereFactory();
        var spheres = sphereFactory.parseSpheresFromFile(SPHERE_FILENAME);

        var warehouse = Warehouse.getInstance();
        logger.info("1. " + warehouse.get(1));

        spheres.getFirst().setRadius(100.0);
        logger.info("2. " + warehouse.get(1));
    }
}
