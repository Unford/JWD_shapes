package by.epam.shape.repository.impl;

import by.epam.shape.entity.Cube;
import by.epam.shape.repository.CubeSpecification;
import by.epam.shape.service.CubeService;
import by.epam.shape.service.impl.CubeServiceImpl;

public class VolumeCubeSpecification implements CubeSpecification {
    private double fromVolume;
    private double toVolume;

    public VolumeCubeSpecification(double fromVolume, double toVolume){
        this.fromVolume = fromVolume;
        this.toVolume = toVolume;
    }

    @Override
    public boolean specify(Cube cube) {
        boolean result = false;
        if (cube != null){
            CubeService service = new CubeServiceImpl();
            double perimeter = service.calculateCubeVolume(cube).orElse(0.0);
            result = perimeter >= fromVolume && perimeter <= toVolume;
        }
        return result;
    }
}
