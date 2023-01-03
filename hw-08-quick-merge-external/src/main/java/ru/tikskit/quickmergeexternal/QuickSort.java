package ru.tikskit.quickmergeexternal;

/**
 * Быстрая сортировка
 */
public class QuickSort extends AbstractSort implements Sort {

    public QuickSort(int[] data) {
        super(data);
    }

    @Override
    public void sort() {
        sort(0, data.length - 1);
    }

    private void sort(int l, int r) {
        if (l >= r) {
            return;
        }
        int x = split(l, r);
        sort(l, x - 1);
        sort(x + 1, r);
    }

    private int split(int l, int r) {
        int p = data[r]; // опорный элемент
        int m = l - 1; // последний элемент в отсортированной части

        for (int j = l; j <= r; j++) { // j - первый элемент неотсортированной части
            if (data[j] <= p) {
                swap(++m, j);
            }
        }

        return m;
    }
}
