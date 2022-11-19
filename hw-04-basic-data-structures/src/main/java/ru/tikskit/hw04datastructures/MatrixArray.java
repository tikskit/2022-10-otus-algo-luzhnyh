package ru.tikskit.hw04datastructures;

public class MatrixArray<T> implements Array<T> {
    private static final int ROW_SIZE = 500;
    private final Array<Array<T>> rows = new FactorArray<>(2);
    private int size;
    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(T item) {
        Array<T> lastRow;
        if (rows.size() == 0) {
            lastRow = new VectorArray<>(ROW_SIZE);
            rows.add(lastRow);
        } else {
            lastRow = rows.get(rows.size() - 1);
            if (lastRow.size() == ROW_SIZE) {
                lastRow = new VectorArray<>(ROW_SIZE);
                rows.add(lastRow);
            }
        }

        lastRow.add(item);
        size++;
    }

    @Override
    public T get(int index) {
        if (index <0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс выходит за пределы списка");
        }
        int rowIndex = index / ROW_SIZE;
        int indexInRow = index % ROW_SIZE;
        return rows.get(rowIndex).get(indexInRow);
    }

    @Override
    public void add(T item, int index) {
        throw new UnsupportedOperationException("Метод add(T, int) не поддерживается");
    }

    @Override
    public T remove(int index) {
        throw new UnsupportedOperationException("Метод add(T, int) не поддерживается");
    }
}
