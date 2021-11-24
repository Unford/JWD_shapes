package by.epam.shape.factory;

import by.epam.shape.entity.Cube;

import java.util.List;
import java.util.Optional;

public interface CubeFactory {

    Optional<Cube> createCube(Double[] parameters);

    Optional<List<Cube>> createCubeList(List<Double[]> parameters);


}
