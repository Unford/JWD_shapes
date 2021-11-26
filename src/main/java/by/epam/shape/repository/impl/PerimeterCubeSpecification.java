package by.epam.shape.repository.impl;

import by.epam.shape.entity.Cube;
import by.epam.shape.repository.CubeSpecification;
import by.epam.shape.service.CubeService;
import by.epam.shape.service.impl.CubeServiceImpl;

public final class PerimeterCubeSpecification implements CubeSpecification {
    private final double fromPerimeter;
    private final double toPerimeter;

    public PerimeterCubeSpecification(double fromPerimeter, double toPerimeter){
        this.fromPerimeter = fromPerimeter;
        this.toPerimeter = toPerimeter;
    }

    @Override
    public boolean specify(Cube cube) {
        boolean match = false;
        if (cube != null){
            CubeService service = new CubeServiceImpl();
            double perimeter = service.calculateCubePerimeter(cube).orElse(0.0);
            match = perimeter >= fromPerimeter && perimeter <= toPerimeter;
        }
        return match;
    }

}
