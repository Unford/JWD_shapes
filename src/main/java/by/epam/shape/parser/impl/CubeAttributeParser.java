package by.epam.shape.parser.impl;

import by.epam.shape.exception.CubeException;
import by.epam.shape.parser.CubeParser;
import by.epam.shape.validator.impl.CubeValidatorImpl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CubeAttributeParser implements CubeParser {
    static Logger logger = LogManager.getLogger();
    private static final String DELIMITER_REGEX = "\\s+";

    @Override
    public List<Double[]> parseCubeList(List<String> listOfParameters) throws CubeException{
        if (listOfParameters == null || listOfParameters.isEmpty()){
            throw new CubeException("Parameter list is null or empty");
        }
        List<Double[]> parsedData;
        CubeValidatorImpl validator = new CubeValidatorImpl();
        parsedData = listOfParameters.stream()
                .map(String::trim)
                .filter(validator::isValidCubeLine)
                .peek(line -> logger.log(Level.DEBUG, "Valid parameter string: " + line))
                .map(this::parseCubeString)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
        logger.log(Level.INFO, "Parsing is successful: " + parsedData.stream().map(Arrays::toString).toList());
        return parsedData;
    }

    @Override
    public Optional<Double[]> parseCubeString(String parameters){
        Double[] parsedData = null;

        if (parameters != null && !parameters.isBlank()){
            CubeValidatorImpl validator = new CubeValidatorImpl();
            parsedData = Arrays.stream(parameters.split(DELIMITER_REGEX))
                    .filter(validator::isValidDouble)
                    .map(Double::parseDouble)
                    .toArray(Double[]::new);
        }

        return Optional.ofNullable(parsedData);
    }

}
