package by.epam.shape.observer.impl;

import by.epam.shape.entity.Cube;
import by.epam.shape.entity.CubeParameters;
import by.epam.shape.entity.CubeWarehouse;
import by.epam.shape.observer.CubeEvent;
import by.epam.shape.observer.Observer;
import by.epam.shape.service.CubeService;
import by.epam.shape.service.impl.CubeServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CubeObserver implements Observer {
    static Logger logger = LogManager.getLogger();

    @Override
    public void parameterChanged(CubeEvent event) {
        CubeWarehouse cubeWarehouse = CubeWarehouse.getInstance();
        Cube cube = event.getSource();
        CubeService operation = new CubeServiceImpl();

        double surfaceArea = operation.calculateCubeSurfaceArea(cube).orElse(0.0);
        double volume = operation.calculateCubeVolume(cube).orElse(0.0);
        double perimeter = operation.calculateCubePerimeter(cube).orElse(0.0);

        CubeParameters cubeParameters = new CubeParameters(surfaceArea, volume, perimeter);
        cubeWarehouse.putParameters(cube.getCubeId(), cubeParameters);
        logger.log(Level.INFO, "Parameters of {} were updated", cube);
    }
}
