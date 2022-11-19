package ru.tikskit.hw04datastructures;

public class FactorArray<T> implements Array<T> {
    private static final int CAPACITY_DEFAULT = 20;
    private final int factor;
    private Object[] data;
    private int size;

    public FactorArray(int factor) {
        this.factor = factor;
        data = new Object[CAPACITY_DEFAULT];
    }

    @Override
    public int size() {
        return size;
    }

    private void resize() {
        Object[] newData = new Object[data.length * factor];
        /*
         * Я знаю,что есть готовые более быстрые методы копирования массива, но я не могу посчитать их сложность.
         * Подозреваю, что сложность нативного System.arraycopy O(1), потому что он скорей всего копирует за одну операцию
         * сразу большой кусок байтов в массив и далее весь его вставляет также за одну операцию.
         */
        for (int i = 0; i < data.length; i++) {
            newData[i] = data[i];
        }

        data = newData;
    }

    @Override
    public void add(T item) {
        if (data.length == size) {
            resize();
        }
        data[size++] = item;
    }

    @Override
    public T get(int index) {
        return (T) data[index];
    }

    @Override
    public void add(T item, int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс вызходит за пределы списка");
        }
        if (data.length == size) {
            resize();
        }

        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }
        data[index] = item;
        size++;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс вызходит за пределы списка");
        }

        T item = (T) data[index];
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
        return item;
    }
}
