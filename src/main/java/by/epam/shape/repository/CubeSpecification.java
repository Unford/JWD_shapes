package by.epam.shape.repository;

import by.epam.shape.entity.Cube;

@FunctionalInterface
public interface CubeSpecification {
    boolean specify(Cube cube);
}
