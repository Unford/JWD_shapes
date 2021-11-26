package by.epam.shape.factory.impl;

import by.epam.shape.factory.CubeFactory;
import by.epam.shape.entity.Cube;
import by.epam.shape.entity.Point;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class CubeFactoryImpl implements CubeFactory {
    static Logger logger = LogManager.getLogger();
    private static final int MINIMUM_PARAMETERS_COUNT = 4;

    @Override
    public Optional<Cube> createCube(Double[] parameters) {
        Cube cube = null;
        if (parameters != null && parameters.length >= MINIMUM_PARAMETERS_COUNT && parameters[3] > 0){
            Point cubeCenter = new Point(parameters[0], parameters[1], parameters[2]);
            cube = new Cube(cubeCenter, parameters[3]);
            logger.log(Level.INFO, "Cube is successfully created: {}", cube);
        }
        return Optional.ofNullable(cube);
    }

    @Override
    public Optional<List<Cube>> createCubeList(List<Double[]> parameterList) {
        List<Cube> cubeList = null;
        if (parameterList != null && !parameterList.isEmpty()){
            cubeList = parameterList.stream()
                    .map(this::createCube)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .toList();
        }
        return Optional.ofNullable(cubeList);
    }
}
