package ru.tikskit.hw06simplesort;

abstract class AbstractSort {
    protected final int[] data;

    public AbstractSort(int[] data) {
        this.data = data;
    }

    protected void swap(int x, int y) {
        int t = data[x];
        data[x] = data[y];
        data[y] = t;
    }

    public int[] getData() {
        return data;
    }
}
