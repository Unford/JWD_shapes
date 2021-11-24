package by.epam.shape.repository.impl;

import by.epam.shape.entity.Cube;
import by.epam.shape.repository.CubeSpecification;
import by.epam.shape.service.CubeService;
import by.epam.shape.service.impl.CubeServiceImpl;

public class TangentToOxzCubeSpecification implements CubeSpecification {
    @Override
    public boolean specify(Cube cube) {
        boolean match = false;
        if (cube != null){
            CubeService service = new CubeServiceImpl();
            match = service.isCubeTangentToOxz(cube);
        }
        return match;
    }
}
