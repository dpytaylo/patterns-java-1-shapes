package com.example.sphere.observer;

import com.example.sphere.model.Sphere;
import com.example.sphere.model.Warehouse;
import com.example.sphere.service.SphereService;

public class SphereObserver {
    public void update(Sphere sphere) {
        var sphereService = new SphereService();
        var area = sphereService.calculateArea(sphere);

        var warehouse = Warehouse.getInstance();
        warehouse.put(sphere.getId(), area);
    }
}
