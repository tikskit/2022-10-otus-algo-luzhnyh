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

        int[] numberIndexes = new int[Constants.MAX_VALUE + 1];

        // подсчитываем числа
        for (int i = 0; i < data.length; i++) {
            int value = data[i];
            numberIndexes[value] += 1;
        }

        // определяем индексы
        int sum = 0;
        for (int i = 0; i < numberIndexes.length - 1; i++) {
            sum += numberIndexes[i];
            numberIndexes[i] = sum;
        }

        int[] sorted = new int[data.length];
        // сортируем
        for (int i = data.length - 1; i >= 0; i--) {
            int value = data[i];
            numberIndexes[value] -= 1;
            int ix = numberIndexes[value];
            sorted[ix] = data[i];
        }
        data = sorted;
    }
}
