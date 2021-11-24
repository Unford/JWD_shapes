package by.epam.shape.parser;

import by.epam.shape.exception.CubeException;

import java.util.List;
import java.util.Optional;

public interface CubeParser {

    List<Double[]> parseCubeList(List<String> listOfParameters) throws CubeException;

    Optional<Double[]> parseCubeString(String parameters);

}
