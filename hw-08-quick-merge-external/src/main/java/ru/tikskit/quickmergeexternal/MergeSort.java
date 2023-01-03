package ru.tikskit.quickmergeexternal;

/**
 * Сортировка слиянием
 */
public class MergeSort extends AbstractSort implements Sort {

    public MergeSort(int[] data) {
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
        int m = (l + r) / 2;
        sort(l, m);
        sort(m + 1, r);
        merge(l, m, r);
    }

    private void merge(int l, int m, int r) {
        int leftCur = l;
        int rightCur = m + 1;
        int mergedPos = 0;
        int merged[] = new int[r - l + 1];

        while (leftCur <= m && rightCur <= r) {
            if (data[leftCur] < data[rightCur]) {
                merged[mergedPos++] = data[leftCur++];
            } else {
                merged[mergedPos++] = data[rightCur++];
            }
        }

        while (leftCur <= m) {
            merged[mergedPos++] = data[leftCur++];
        }

        while (rightCur <= r) {
            merged[mergedPos++] = data[rightCur++];
        }

        for (int i = l; i <= r; i++) {
            data[i] = merged[i - l];
        }
    }
}
