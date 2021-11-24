package by.epam.shape.repository.impl;

import by.epam.shape.entity.Cube;
import by.epam.shape.repository.CubeSpecification;
import by.epam.shape.service.CubeService;
import by.epam.shape.service.impl.CubeServiceImpl;

public class TangentToOxyCubeSpecification implements CubeSpecification {
    @Override
    public boolean specify(Cube cube) {
        boolean result = false;
        if (cube != null){
            CubeService service = new CubeServiceImpl();
            result = service.isCubeTangentToOxy(cube);
        }
        return result;
    }
}
