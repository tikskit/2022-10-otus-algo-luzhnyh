package ru.tikskit.quickmergeexternal;

public class SortShell extends AbstractSort implements Sort {

    public SortShell(int[] data) {
        super(data);
    }

    @Override
    public void sort() {
        for (int gap = data.length / 2; gap > 0; gap /= 2) {
            for (int j = gap; j < data.length; j++) {
                for (int i = j; i >= gap; i -= gap) {
                    if (data[i] < data[i - gap]) {
                        swap(i, i - gap);
                    } else {
                        break;
                    }
                }
            }
        }
    }
}
