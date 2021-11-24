package by.epam.shape.repository.impl;

import by.epam.shape.entity.Cube;
import by.epam.shape.repository.CubeSpecification;

public class IdCubeSpecification implements CubeSpecification {
    private long fromId;
    private long toId;

    public IdCubeSpecification(long fromId, long toId){
        this.fromId = fromId;
        this.toId = toId;
    }

    @Override
    public boolean specify(Cube cube) {
        boolean match = false;
        if (cube != null){
            long id = cube.getCubeId();
            match = id >= fromId && id <= toId;
        }
        return match;
    }
}
