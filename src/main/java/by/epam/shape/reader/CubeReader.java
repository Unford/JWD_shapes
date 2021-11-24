package by.epam.shape.reader;

import by.epam.shape.exception.CubeException;

import java.util.List;

public interface CubeReader {
    List<String> readAllLines(String filename) throws CubeException;
}
