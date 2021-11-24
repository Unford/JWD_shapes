package by.epam.shape.repository.impl;

import by.epam.shape.entity.Cube;
import by.epam.shape.repository.CubeSpecification;
import by.epam.shape.service.CubeService;
import by.epam.shape.service.impl.CubeServiceImpl;

public class SurfaceAreaCubeSpecification implements CubeSpecification {
    private double fromSurfaceArea;
    private double toSurfaceArea;

    public SurfaceAreaCubeSpecification(double fromSurfaceArea, double toSurfaceArea){
        this.fromSurfaceArea = fromSurfaceArea;
        this.toSurfaceArea = toSurfaceArea;
    }

    @Override
    public boolean specify(Cube cube) {
        boolean match = false;
        if (cube != null){
            CubeService service = new CubeServiceImpl();
            double perimeter = service.calculateCubeSurfaceArea(cube).orElse(0.0);
            match = perimeter >= fromSurfaceArea && perimeter <= toSurfaceArea;
        }
        return match;
    }
}
