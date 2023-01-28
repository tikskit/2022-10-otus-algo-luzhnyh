package ru.tikskit.heapsort;

/**
 * Выборочная сортировка
 */
public class SelectSort extends AbstractSort implements Sort {

    public SelectSort(int[] data) {
        super(data);
    }

    @Override
    public void sort() {
        for (int j = data.length - 1; j > 0; j--) {
            swap(findMax(j), j);
        }
    }

    private int findMax(int j) {
        int maxInd = j;
        for (int i = 0; i < j; i++) {
            if (data[i] > data[maxInd]) {
                maxInd = i;
            }
        }
        return maxInd;
    }

}
