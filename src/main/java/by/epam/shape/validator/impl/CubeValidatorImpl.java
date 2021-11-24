package by.epam.shape.validator.impl;

import by.epam.shape.entity.Cube;
import by.epam.shape.validator.CubeValidator;

import java.io.File;

public final class CubeValidatorImpl implements CubeValidator {
    private static final String POSITIVE_DOUBLE_REGEX = "[+]?\\d*\\.?\\d+([eE][-+]?\\d)?";
    private static final String DOUBLE_REGEX = "[-+]?\\d*\\.?\\d+([eE][-+]?\\d)?";
    private static final String CUBE_LINE_REGEX = "^(" + DOUBLE_REGEX + "\\s+){3}" + POSITIVE_DOUBLE_REGEX;

    public static final int MINIMUM_VERTEX_COUNT = 8;

    @Override
    public boolean isValidFilepath(String filepath){
        if (filepath == null || filepath.isBlank()){
            return false;
        }
        File file = new File(filepath);
        return file.exists();
    }

    @Override
    public boolean isValidDouble(String num){
        return num.matches(DOUBLE_REGEX);
    }

    @Override
    public boolean isValidCubeLine(String line){
        return line.matches(CUBE_LINE_REGEX);
    }

    @Override
    public boolean isValidCube(Cube cube){
        boolean result = cube != null && cube.getEdge() > 0 && cube.getCenter() != null;
        return result;
    }
}
