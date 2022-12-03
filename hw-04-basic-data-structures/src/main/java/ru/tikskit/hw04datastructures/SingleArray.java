package ru.tikskit.hw04datastructures;

public class SingleArray<T> implements Array<T> {
    private Object[] data = new Object[0];

    @Override
    public int size() {
        return data.length;
    }

    private void incSize() {
        Object[] newData = new Object[data.length + 1];
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
        incSize();

        data[data.length - 1] = item;
    }

    @Override
    public T get(int index) {
        return (T) data[index];
    }

    @Override
    public void add(T item, int index) {
        if (index < 0 || index >= data.length) {
            throw new IndexOutOfBoundsException("Индекс вызходит за пределы списка");
        }
        incSize();

        for (int i = data.length - 1; i > index; i--) {
            data[i] = data[i - 1];
        }
        data[index] = item;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= data.length) {
            throw new IndexOutOfBoundsException("Индекс вызходит за пределы списка");
        }

        T item = (T) data[index];
        for (int i = index; i < data.length - 1; i++) {
            data[i] = data[i + 1];
        }
        decSize();
        return item;
    }

    private void decSize() {
        Object[] newData = new Object[data.length - 1];
        /*
         * Я знаю,что есть готовые более быстрые методы копирования массива, но я не могу посчитать их сложность.
         * Подозреваю, что сложность нативного System.arraycopy O(1), потому что он скорей всего копирует за одну операцию
         * сразу большой кусок байтов в массив и далее весь его вставляет также за одну операцию.
         */
        for (int i = 0; i < data.length - 1; i++) {
            newData[i] = data[i];
        }

        data = newData;
    }
}
