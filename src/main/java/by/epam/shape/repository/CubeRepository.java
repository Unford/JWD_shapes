package by.epam.shape.repository;

import by.epam.shape.entity.Cube;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public interface CubeRepository {
    int size();

    boolean contains(Cube cube);

    boolean add(Cube cube);

    boolean remove(Cube cube);

    boolean addAll(Collection<? extends Cube> c);

    boolean addAll(int index, Collection<? extends Cube> c);

    List<Cube> query(CubeSpecification specification);

    List<Cube> query(Predicate<Cube> predicate);

    List<Cube> sort(Comparator<? super Cube> comparator);

    void clear();

    Cube get(int index);

    Cube set(int index, Cube element);

    void add(int index, Cube element);

    Cube remove(int index);

    int indexOf(Cube cube);

    int lastIndexOf(Cube cube);
}
