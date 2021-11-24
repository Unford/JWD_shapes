package by.epam.shape.service.impl;

import by.epam.shape.entity.Cube;
import by.epam.shape.entity.Point;
import by.epam.shape.service.CubeService;
import by.epam.shape.validation.CubeValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

public class CubeServiceImpl implements CubeService {
    static Logger logger = LogManager.getLogger();
    private static final String CUBE_ERROR_MESSAGE = "Cube is null or invalid: ";

   @Override
   public OptionalDouble calculateCubeSurfaceArea(Cube cube){
       if (CubeValidator.isValidCube(cube)){
           double surfaceArea = 6 * Math.pow(cube.getEdge(), 2.0);
           logger.log(Level.INFO, "Calculated surface area for {} = " + surfaceArea, cube);
           return OptionalDouble.of(surfaceArea);
       }
       logger.log(Level.ERROR, CUBE_ERROR_MESSAGE + cube);
       return OptionalDouble.empty();
   }

   @Override
   public OptionalDouble calculateCubeVolume(Cube cube){
       if (CubeValidator.isValidCube(cube)){
           double volume = Math.pow(cube.getEdge(), 3.0);
           logger.log(Level.INFO, "Calculated volume for {} = " + volume, cube);
           return OptionalDouble.of(volume);
       }
       logger.log(Level.ERROR, CUBE_ERROR_MESSAGE + cube);
       return OptionalDouble.empty();
   }

   @Override
   public OptionalDouble calculateCubePerimeter(Cube cube){
       if (CubeValidator.isValidCube(cube)){
           double perimeter = 12 * cube.getEdge();
           logger.log(Level.INFO, "Calculated perimeter for {} = " + perimeter, cube);
           return OptionalDouble.of(perimeter);
       }
       logger.log(Level.ERROR, CUBE_ERROR_MESSAGE + cube);
       return OptionalDouble.empty();
   }
   //todo
    /*
                 .3------7
               .' |    .'|
              1---+--5'  |
              |   |  |   |
              |  ,2--+---6
              |.'    | .'
              0------4'
       */
   @Override
   public Optional<List<Point>> calculateCubeVertex(Cube cube){
       List<Point> points = null;
       if (CubeValidator.isValidCube(cube)){
           points = new ArrayList<>(CubeValidator.MINIMUM_VERTEX_COUNT);
           double edge = cube.getEdge();
           double shiftX = cube.getCenter().x() - edge / 2;
           double shiftY = cube.getCenter().y() - edge / 2;
           double shiftZ = cube.getCenter().z() - edge / 2;
           for (int x = 0; x < 2; x++) {
               for (int y = 0; y < 2; y++) {
                   for (int z = 0; z < 2; z++) {
                       Point point = new Point(x * edge + shiftX, y * edge + shiftY, z * edge + shiftZ);
                       points.add(point);
                   }
               }
           }
       }
       return Optional.ofNullable(points);
   }

   @Override
   public boolean isCube(List<Point> points){
       if (points == null || points.size() < CubeValidator.MINIMUM_VERTEX_COUNT){
           logger.log(Level.WARN, "points are null or their number is less than " + CubeValidator.MINIMUM_VERTEX_COUNT);
           return false;
       }
       boolean result = true;
       for (int x = 0, i = 0; x < 2 && result; x++) {
           for (int y = 0; y < 2 && result; y++) {
               for (int z = 0; z < 2 && result; z++, i++) {
                   Point pointOne = points.get(i);
                   Point pointX = points.get(i ^ 0b100);
                   Point pointY = points.get(i ^ 0b010);
                   Point pointZ = points.get(i ^ 0b001);
                 result = isPerpendicular(pointOne, pointX, pointZ) &&
                          isPerpendicular(pointOne, pointX, pointY) &&
                          isPerpendicular(pointOne, pointY, pointZ);
               }
           }
       }
       return result;
   }


   private boolean isPerpendicular(Point middlePoint, Point pointOne, Point pointTwo){
       if (middlePoint == null || pointOne == null || pointTwo == null){
           logger.log(Level.WARN, "points are null");
           return false;
       }
       double vectorDotProduct = (pointOne.x() - middlePoint.x()) * (pointTwo.x() - middlePoint.x());
       vectorDotProduct += (pointOne.y() - middlePoint.y()) * (pointTwo.y() - middlePoint.y());
       vectorDotProduct += (pointOne.z() - middlePoint.z()) * (pointTwo.z() - middlePoint.z());
       return vectorDotProduct == 0;
   }

   @Override
   public boolean isCubeTangentToOxy(Cube cube){
       boolean result = CubeValidator.isValidCube(cube) &&
               cube.getCenter().z() == cube.getEdge() / 2;
       return result;
   }

   @Override
   public boolean isCubeTangentToOxz(Cube cube){
       boolean result = CubeValidator.isValidCube(cube) &&
               cube.getCenter().y() == cube.getEdge() / 2;
       return result;
   }

   @Override
   public boolean isCubeTangentToOyz(Cube cube){
       boolean result = CubeValidator.isValidCube(cube) &&
               cube.getCenter().x() == cube.getEdge() / 2;
       return result;
   }

   @Override
   public OptionalDouble calculateRatioSectionOxy(Cube cube, double shiftZ){
       if (CubeValidator.isValidCube(cube)){
           double heightUp = cube.getCenter().z() + cube.getEdge() / 2 - shiftZ;
           double heightDown = cube.getEdge() - heightUp;
           double ratio = heightUp / heightDown;
           return OptionalDouble.of(ratio);
       }
       logger.log(Level.ERROR, CUBE_ERROR_MESSAGE + cube);
       return OptionalDouble.empty();
   }

   @Override
   public OptionalDouble calculateRatioSectionOxz(Cube cube, double shiftY){
       if (CubeValidator.isValidCube(cube)){
           double heightUp = cube.getCenter().y() + cube.getEdge() / 2 - shiftY;
           double heightDown = cube.getEdge() - heightUp;
           double ratio = heightUp / heightDown;
           return OptionalDouble.of(ratio);
       }
       logger.log(Level.ERROR, CUBE_ERROR_MESSAGE + cube);
       return OptionalDouble.empty();
    }

    @Override
    public OptionalDouble calculateRatioSectionOyz(Cube cube, double shiftX){
        if (CubeValidator.isValidCube(cube)){
            double heightUp = cube.getCenter().x() + cube.getEdge() / 2 - shiftX;
            double heightDown = cube.getEdge() - heightUp;
            double ratio = heightUp / heightDown;
            return OptionalDouble.of(ratio);
        }
        logger.log(Level.ERROR, CUBE_ERROR_MESSAGE + cube);
        return OptionalDouble.empty();
    }

}
