package by.epam.shape.reader;


import by.epam.shape.exception.CubeException;
import by.epam.shape.validation.CubeValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class CubeReader {
    static Logger logger = LogManager.getLogger();

    public List<String> readAllLines(String filename) throws CubeException{
        CubeValidator validator = new CubeValidator();
        if (!validator.isValidFilepath(filename)){
            throw new CubeException("File path is invalid: " + filename);
        }
        List<String> strings;
        Path path = Paths.get(filename);
        try (BufferedReader br = Files.newBufferedReader(path)){
            strings = br.lines()
                    .peek(line -> logger.log(Level.DEBUG, "read: {} ", line))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            logger.log(Level.ERROR,"Input error while reading file", e);
            throw new CubeException("Input error while reading file", e);
        }
        logger.log(Level.INFO, "Read file {} is successful", path.getFileName());
        return strings;
    }
}
