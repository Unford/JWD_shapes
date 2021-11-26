package by.epam.shape.reader.impl;

import by.epam.shape.exception.CubeException;
import by.epam.shape.reader.CubeReader;
import by.epam.shape.validator.impl.CubeValidatorImpl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public final class CubeReaderImpl implements CubeReader {
    static Logger logger = LogManager.getLogger();

    @Override
    public List<String> readAllLines(String filename) throws CubeException{
        CubeValidatorImpl validator = new CubeValidatorImpl();
        if (!validator.isValidFilepath(filename)){
            throw new CubeException("File path is invalid: " + filename);
        }
        List<String> strings;
        Path path = Paths.get(filename);
        try (BufferedReader br = Files.newBufferedReader(path)){
            strings = br.lines()
                    .peek(line -> logger.log(Level.DEBUG, "read: {} ", line))
                    .toList();
        } catch (IOException e) {
            logger.log(Level.ERROR,"Input error while reading file", e);
            throw new CubeException("Input error while reading file", e);
        }
        logger.log(Level.INFO, "Read file {} is successful", path.getFileName());
        return strings;
    }
}
