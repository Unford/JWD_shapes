package by.epam.shape.repository.impl;

import by.epam.shape.entity.Cube;
import by.epam.shape.repository.CubeSpecification;
import by.epam.shape.repository.Repository;


import java.util.*;
import java.util.function.Predicate;


public class CubeRepository implements Repository {
    private final List<Cube> cubes = new ArrayList<>();

    @Override
    public int size() {
        return cubes.size();
    }

    @Override
    public boolean contains(Cube cube) {
        return cubes.contains(cube);
    }

    @Override
    public boolean add(Cube cube) {
        return cubes.add(cube);
    }

    @Override
    public boolean remove(Cube cube) {
        return cubes.remove(cube);
    }

    @Override
    public boolean addAll(Collection<? extends Cube> c) {
        return cubes.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends Cube> c) {
        return cubes.addAll(index, c);
    }

    @Override
    public List<Cube> query(CubeSpecification specification){
        return cubes.stream().filter(specification::specify).toList();
    }

    @Override
    public List<Cube> query(Predicate<Cube> predicate) {
        return cubes.stream().filter(predicate).toList();
    }

    @Override
    public List<Cube> sort(Comparator<? super Cube> comparator) {
        return cubes.stream().sorted(comparator).toList();
    }

    @Override
    public void clear() {
        cubes.clear();
    }

    @Override
    public Cube get(int index) {
        return cubes.get(index);
    }

    @Override
    public Cube set(int index, Cube element) {
        return cubes.set(index, element);
    }

    @Override
    public void add(int index, Cube element) {
        cubes.add(index, element);
    }

    @Override
    public Cube remove(int index) {
        return cubes.remove(index);
    }

    @Override
    public int indexOf(Cube cube) {
        return cubes.indexOf(cube);
    }

    @Override
    public int lastIndexOf(Cube cube) {
        return cubes.lastIndexOf(cube);
    }

}
