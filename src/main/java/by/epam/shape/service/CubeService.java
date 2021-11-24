package by.epam.shape.service;

import by.epam.shape.entity.Cube;
import by.epam.shape.entity.Point;

import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

public interface CubeService {

    OptionalDouble calculateCubeSurfaceArea(Cube cube);

    OptionalDouble calculateCubeVolume(Cube cube);

    OptionalDouble calculateCubePerimeter(Cube cube);

    Optional<List<Point>> calculateCubeVertex(Cube cube);

    boolean isCube(List<Point> points);

    boolean isCubeTangentToOxy(Cube cube);

    boolean isCubeTangentToOxz(Cube cube);

    boolean isCubeTangentToOyz(Cube cube);

    OptionalDouble calculateRatioSectionOxy(Cube cube, double shiftZ);

    OptionalDouble calculateRatioSectionOxz(Cube cube, double shiftY);

    OptionalDouble calculateRatioSectionOyz(Cube cube, double shiftX);
}

