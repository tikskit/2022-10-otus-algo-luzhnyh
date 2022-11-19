package ru.tikskit.hw04datastructures;

public interface Array<T> {
    int size();

    void add(T item);

    T get(int index);

    void add(T item, int index);

    T remove(int index);
}
