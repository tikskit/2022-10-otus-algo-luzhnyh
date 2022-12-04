package ru.tikskit.hw06simplesort;

/**
 * Вариант Insertion sort, но мы будем не сдвигать каждый раз отсортированные элементы вправо, а сперва найдем
 * место для вставки, а потом сдвинем все вправо нативной операцией arrayCopy
 */
public class SortInsertBatchMover extends AbstractSort implements Sort {

    public SortInsertBatchMover(int[] data) {
        super(data);
    }

    @Override
    public void sort() {
        for (int j = 1; j < data.length; j++) {
            int i = j - 1;
            int valJ = data[j];
            if (data[i] > valJ) {
                while (i >= 0 && data[i] > valJ) {
                    i--;
                }
                i++;

                System.arraycopy(data, i, data, i + 1, j - i);
                data[i] = valJ;
            }
        }

    }
}
