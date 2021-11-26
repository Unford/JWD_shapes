package by.epam.shape.comparator;

import by.epam.shape.entity.Cube;
import by.epam.shape.entity.CubeWarehouse;


import java.util.Comparator;


public enum CubeComparator implements Comparator<Cube> {

    ID {
        @Override
        public int compare(Cube leftCube, Cube rightCube){
            return Long.compare(leftCube.getCubeId(), rightCube.getCubeId());
        }
    },
    CENTER_X {
        @Override
        public int compare(Cube leftCube, Cube rightCube){
            return Double.compare(leftCube.getCenter().x(), rightCube.getCenter().x());
        }
    },
    CENTER_Y {
        @Override
        public int compare(Cube leftCube, Cube rightCube){
            return Double.compare(leftCube.getCenter().y(), rightCube.getCenter().y());
        }
    },
    CENTER_Z {
        @Override
        public int compare(Cube leftCube, Cube rightCube){
            return Double.compare(leftCube.getCenter().z(), rightCube.getCenter().z());
        }
    },
    EDGE {
        @Override
        public int compare(Cube leftCube, Cube rightCube){
            return Double.compare(leftCube.getEdge(), rightCube.getEdge());
        }
    },
    SURFACE_AREA {
        @Override
        public int compare(Cube leftCube, Cube rightCube){
            CubeWarehouse warehouse = CubeWarehouse.getInstance();
            double leftCubeArea = warehouse.getParameters(leftCube.getCubeId()).get().surfaceArea();
            double rightCubeArea = warehouse.getParameters(rightCube.getCubeId()).get().surfaceArea();
            return Double.compare(leftCubeArea, rightCubeArea);
        }
    },
    PERIMETER {
        @Override
        public int compare(Cube leftCube, Cube rightCube){
            CubeWarehouse warehouse = CubeWarehouse.getInstance();
            double leftCubePerimeter = warehouse.getParameters(leftCube.getCubeId()).get().perimeter();
            double rightCubePerimeter = warehouse.getParameters(rightCube.getCubeId()).get().perimeter();
            return Double.compare(leftCubePerimeter, rightCubePerimeter);
        }
    },
    VOLUME {
        @Override
        public int compare(Cube leftCube, Cube rightCube){
            CubeWarehouse warehouse = CubeWarehouse.getInstance();
            double leftCubeVolume = warehouse.getParameters(leftCube.getCubeId()).get().volume();
            double rightCubeVolume = warehouse.getParameters(rightCube.getCubeId()).get().volume();
            return Double.compare(leftCubeVolume, rightCubeVolume);
        }
    }
}
