package by.epam.shape.parser;

import by.epam.shape.exception.CubeException;
import by.epam.shape.validation.CubeValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CubeAttributeParser {
    static Logger logger = LogManager.getLogger();
    private static final String DELIMITER_REGEX = "\\s+";

    public List<Double[]> parseCubeList(List<String> listOfParameters) throws CubeException{//todo throw ex
        if (listOfParameters == null || listOfParameters.isEmpty()){
            throw new CubeException("Parameter list is null or empty");
        }

        List<Double[]> parsedData;
        CubeValidator validator = new CubeValidator();
        parsedData = listOfParameters.stream()
                .map(String::trim)
                .filter(validator::isValidCubeLine)
                .peek(line -> logger.log(Level.DEBUG, "Valid parameter string: " + line))
                .map(this::parseCubeString)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
        logger.log(Level.INFO, "Parsing is successful: " + parsedData.stream().map(Arrays::toString).collect(Collectors.toList()));//fixme ????
        return parsedData;
    }

    public Optional<Double[]> parseCubeString(String parameters){
        Double[] parsedData = null;

        if (parameters != null && !parameters.isBlank()){
            CubeValidator validator = new CubeValidator();
            parsedData = Arrays.stream(parameters.split(DELIMITER_REGEX))
                    .filter(validator::isValidDouble)
                    .map(Double::parseDouble)
                    .toArray(Double[]::new);
        }

        return Optional.ofNullable(parsedData);
    }

    /*todo parser old version
    * public List<List<Double>> parseCubes(List<String> listOfParameters){
        List<List<Double>> parsedData = new ArrayList<>();
        CubeValidator validator = new CubeValidator();
        for (String line : listOfParameters) {
            if (validator.isValidCubeLine(line.trim())){
                logger.log(Level.DEBUG, "Valid parameter string: " + line);
                List<Double> cubeArgs = parseCube(line);
                parsedData.add(cubeArgs);
            }
        }
        logger.log(Level.INFO, "Parsing is successful: " + parsedData);
        return parsedData;
    }

    public List<Double> parseCube(String parameters){
        List<Double> parsedData = new ArrayList<>();

        List<String> listOfParameters = Arrays.asList(parameters.split(DELIMITER_REGEX));
        DoubleValidator validator = new DoubleValidator();
        for (String parameter : listOfParameters) {
            if (validator.isValidDouble(parameter)){
                parsedData.add(Double.parseDouble(parameter));
            }
        }
        return parsedData;
    }*/
}
