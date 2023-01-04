package ru.tikskit.hw09linearsort;

abstract class AbstractSort {
    protected int[] data;

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
