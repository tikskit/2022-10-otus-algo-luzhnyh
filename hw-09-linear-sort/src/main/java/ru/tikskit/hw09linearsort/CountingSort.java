package ru.tikskit.hw09linearsort;

/**
 * Сортировка посчетом
 */
public class CountingSort extends AbstractSort implements Sort {

    public CountingSort(int[] data) {
        super(data);
    }

    @Override
    public void sort() {
        if (data.length <= 1) {
            return;
        }

        int[] numberCount = new int[Constants.MAX_VALUE - Constants.MIN_VALUE + 1];

        for (int i = 0; i < data.length; i++) {
            int value = data[i];
            numberCount[value] += 1;
        }

        int sum = 0;
        for (int i = 0; i < numberCount.length - 1; i++) {
            sum += numberCount[i];
            numberCount[i] = sum;
        }

        int[] sorted = new int[data.length];
        for (int i = data.length - 1; i >= 0; i--) {
            int value = data[i];
            numberCount[value] -= 1;
            int ix = numberCount[value];
            sorted[ix] = data[i];
        }
        data = sorted;
    }
}
