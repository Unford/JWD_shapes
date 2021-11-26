package by.epam.shape.main;

import by.epam.shape.service.CubeService;
import by.epam.shape.service.impl.CubeServiceImpl;
import by.epam.shape.factory.CubeFactory;
import by.epam.shape.factory.impl.CubeFactoryImpl;
import by.epam.shape.entity.Cube;
import by.epam.shape.entity.Point;
import by.epam.shape.exception.CubeException;
import by.epam.shape.parser.impl.CubeAttributeParser;
import by.epam.shape.reader.impl.CubeReaderImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Main {
    static Logger logger = LogManager.getLogger();


    public static void main(String[] args) throws CubeException {
        try {
            CubeReaderImpl reader = new CubeReaderImpl();
            List<String> strings = reader.readAllLines("src/main/resources/input/input.txt");
          //  List<String> empty = reader.readAllLines("src/main/resources/input/empty.txt");

            //List<String> strings2 = reader.readAll(null);
     //       List<String> strings2 = reader.readAllLines("dsrc/main/resources/input/&//\7фв/input.txt");

            CubeAttributeParser parser = new CubeAttributeParser();
       //     var emptyParams = parser.parseCubeList(empty);
            var params = parser.parseCubeList(strings);
            CubeFactory cubeFactory = new CubeFactoryImpl();
             var s = cubeFactory.createCubeList(params);

            Cube cube = new Cube(new Point(1.23241, 6.243214, 4.54252), 4.334121124);

            CubeService operation = new CubeServiceImpl();
            var points = operation.calculateCubeVertex(cube);
            operation.isCubeTangentToOxy(null);
            operation.calculateCubePerimeter(cube);
            operation.calculateCubeSurfaceArea(cube);
            operation.calculateCubeVolume(cube);
            System.out.println(operation.isCube(points.get()));
        }catch (CubeException e){
            logger.error(e);
        }

    }
}
