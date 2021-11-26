package by.epam.shape.comparator.impl;

import by.epam.shape.comparator.CubeComparator;
import by.epam.shape.entity.Cube;
import by.epam.shape.entity.CubeWarehouse;


import java.util.Comparator;


public enum CubeComparators implements CubeComparator {

    ID {
        @Override
        public Comparator<Cube> get(){
            return Comparator.comparingLong(Cube::getCubeId);
        }
    },
    CENTER_X {
        @Override
        public Comparator<Cube> get(){
            return Comparator.comparingDouble(cube -> cube.getCenter().x());
        }
    },
    CENTER_Y {
        @Override
        public Comparator<Cube> get(){
            return Comparator.comparingDouble(cube -> cube.getCenter().y());
        }
    },
    CENTER_Z {
        @Override
        public Comparator<Cube> get(){
            return Comparator.comparingDouble(cube -> cube.getCenter().z());
        }
    },
    EDGE {
        @Override
        public Comparator<Cube> get(){
            return Comparator.comparingDouble(Cube::getEdge);
        }
    },
    SURFACE_AREA {
        @Override
        public Comparator<Cube> get(){
            return Comparator.comparingDouble(cube ->
                    CubeWarehouse.getInstance()
                            .getParameters(cube.getCubeId())
                            .get()
                            .surfaceArea());
        }
    },
    PERIMETER {
        @Override
        public Comparator<Cube> get(){
            return Comparator.comparingDouble(cube ->
                    CubeWarehouse.getInstance()
                            .getParameters(cube.getCubeId())
                            .get()
                            .perimeter());
        }
    },
    VOLUME {
        @Override
        public Comparator<Cube> get(){
            return Comparator.comparingDouble(cube ->
                    CubeWarehouse.getInstance()
                            .getParameters(cube.getCubeId())
                            .get()
                            .volume());
        }
    }
}
