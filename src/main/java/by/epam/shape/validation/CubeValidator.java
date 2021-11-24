package by.epam.shape.validation;

import by.epam.shape.entity.Cube;

import java.io.File;

public class CubeValidator {
    private static final String POSITIVE_DOUBLE_REGEX = "[+]?\\d*\\.?\\d+([eE][-+]?\\d)?";
    private static final String DOUBLE_REGEX = "[-+]?\\d*\\.?\\d+([eE][-+]?\\d)?";
    private static final String CUBE_LINE_REGEX = "^(" + DOUBLE_REGEX + "\\s+){3}" + POSITIVE_DOUBLE_REGEX;

    public static final int MINIMUM_VERTEX_COUNT = 8;

    public boolean isValidFilepath(String filepath){
        if (filepath == null || filepath.isBlank()){
            return false;
        }
        File file = new File(filepath);
        return file.exists();
    }
    public boolean isValidDouble(String num){
        return num.matches(DOUBLE_REGEX);
    }
    public boolean isValidCubeLine(String line){
        return line.matches(CUBE_LINE_REGEX);
    }

    public static boolean isValidCube(Cube cube){//todo static?????
        boolean result = cube != null && cube.getEdge() > 0 && cube.getCenter() != null;
        return result;
    }
}
