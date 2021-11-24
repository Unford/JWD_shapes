package by.epam.shape.comparator;

import by.epam.shape.entity.Cube;
import by.epam.shape.entity.CubeParameters;
import by.epam.shape.entity.CubeWarehouse;


import java.util.Comparator;


public enum CubeComparator {
    ID (Comparator.comparingLong(Cube::getCubeId)),

    CENTER_X (Comparator.comparing(cube -> cube.getCenter().x())),
    CENTER_Y (Comparator.comparing(cube -> cube.getCenter().y())),
    CENTER_Z (Comparator.comparing(cube -> cube.getCenter().z())),

    EDGE (Comparator.comparingDouble(Cube::getEdge)),

    SURFACE_AREA (Comparator.comparingDouble(cube ->
                     CubeWarehouse.getInstance()
                     .getParameters(cube.getCubeId())
                     .orElseThrow(() -> new RuntimeException("Warehouse hasn't this cube id: " + cube.getCubeId()))
                     .surfaceArea())),

    PERIMETER (Comparator.comparingDouble(cube ->
                     CubeWarehouse.getInstance()
                    .getParameters(cube.getCubeId())
                    .orElseThrow(() -> new RuntimeException("Warehouse hasn't this cube id: " + cube.getCubeId()))
                    .perimeter())),

    VOLUME (Comparator.comparingDouble(cube ->
            CubeWarehouse.getInstance()
                    .getParameters(cube.getCubeId())
                    .orElseThrow(() -> new RuntimeException("Warehouse hasn't this cube id: " + cube.getCubeId()))
                    .volume()));

    //private static final CubeParameters EMPTY_PARAMETERS = new CubeParameters(0.0, 0.0, 0.0);//todo change to exception?
    CubeComparator(Comparator<Cube> comparator){
        this.comparator = comparator;
    }

    private Comparator<Cube> comparator;

    public Comparator<Cube> getComparator(){
        return comparator;
    }
}
