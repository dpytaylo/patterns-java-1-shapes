package main;

import com.example.sphere.exception.InvalidSphereRadius;
import com.example.sphere.model.Sphere;
import com.example.sphere.model.Warehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class SphereApplication {
    private static final Logger logger = LogManager.getLogger(SphereApplication.class);
    private static final String SPHERE_FILE = "src/main/resources/spheres.txt";

    public static void main(String[] args) throws InvalidSphereRadius {
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(SPHERE_FILE));
        } catch (IOException e) {
            logger.error("Failed to read the sphere file", e);
            return;
        }

        var spheres = Sphere.parseSpheres(lines);
        for (var sphere : spheres) {
            logger.info(sphere);
        }

        var warehouse = Warehouse.getInstance();
        logger.info("1. " + warehouse.get(1));

        spheres.getFirst().setRadius(100.0);
        logger.info("2. " + warehouse.get(1));
    }
}
