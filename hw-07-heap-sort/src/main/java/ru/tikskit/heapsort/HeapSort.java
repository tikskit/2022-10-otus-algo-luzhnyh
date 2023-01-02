package ru.tikskit.heapsort;

/**
 * Пирамидальная сортировка
 */
public class HeapSort extends AbstractSort implements Sort {

    public HeapSort(int[] data) {
        super(data);
    }

    @Override
    public void sort() {
        for (int h = data.length / 2 - 1; h >= 0; h--) {
            heapify(h, data.length);
        }

        for (int j = data.length - 1; j > 0; j--) {
            swap(0, j);
            heapify(0, j);
        }
    }

    private void heapify(int root, int size) {
        int x = root;
        int l = 2 * root + 1;
        int r = l + 1;

        if (l < size && data[l] > data[x]) {
            x = l;
        }

        if (r < size && data[r] > data[x]) {
            x = r;
        }

        if (x != root) {
            swap(x, root);
            heapify(x, size);
        }
    }
}
