package com.example.sphere.factory;

import com.example.sphere.exception.InvalidSphereException;
import com.example.sphere.exception.SphereFactoryException;
import com.example.sphere.model.Point;
import com.example.sphere.model.Sphere;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SphereFactory {
    private static final Logger logger = LogManager.getLogger(Sphere.class);
    private static final String PARTS_SEPERATOR = ";";

    public ArrayList<Sphere> parseSpheres(List<String> lines) {
        var spheres = new ArrayList<Sphere>();

        for (String line : lines) {
            try {
                var parts = line.split(PARTS_SEPERATOR);

                var x = Double.parseDouble(parts[0]);
                var y = Double.parseDouble(parts[1]);
                var z = Double.parseDouble(parts[2]);

                var radius = Double.parseDouble(parts[3]);

                var position = new Point(x, y, z);
                var sphere = new Sphere(position, radius);

                spheres.add(sphere);
            } catch (NumberFormatException | InvalidSphereException e) {
                logger.error("Failed to parse a sphere", e);
            }
        }

        return spheres;
    }

    public ArrayList<Sphere> parseSpheresFromFile(String filename) throws SphereFactoryException {
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(filename));
        } catch (IOException e) {
            throw new SphereFactoryException(e);
        }

        return parseSpheres(lines);
    }
}
