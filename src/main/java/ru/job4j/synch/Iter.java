package ru.job4j.synch;

import java.util.Iterator;
import java.util.List;

public interface Iter<T> {

    List<T> copy(List<T> list);

    void add(T value);

    T get(int index);

    Iterator<T> iterator();

}
