package ru.tikskit.quickmergeexternal;

public class SortInsert extends AbstractSort implements Sort {

    public SortInsert(int[] data) {
        super(data);
    }

    @Override
    public void sort() {
        for (int j = 1; j < data.length; j++) {
            for (int i = j - 1; i >= 0; i--) {
                if (data[i] > data[i + 1]) {
                    swap(i, i + 1);
                } else {
                    break;
                }
            }
        }
    }
}
