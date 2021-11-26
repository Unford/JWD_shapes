package by.epam.shape.validator;

import by.epam.shape.entity.Cube;

public interface CubeValidator {

    boolean isValidFilepath(String filepath);

    boolean isValidDouble(String num);

    boolean isValidCubeLine(String line);

    boolean isValidCube(Cube cube);
}
