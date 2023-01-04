package ru.tikskit.hw09linearsort;

/**
 * Поразрядная сортировка
 */
public class RadixSort extends AbstractSort implements Sort {

    public RadixSort(int[] data) {
        super(data);
    }

    public static class NumDigits {
        private final int[] hundreds;
        private final int[] tens;
        private final int[] ones;

        public NumDigits(int length) {
            hundreds = new int[length];
            tens = new int[length];
            ones = new int[length];
        }
    }

    @Override
    public void sort() {
        if (data.length <= 1) {
            return;
        }

        NumDigits notSorted = new NumDigits(data.length);

        for (int i = 0; i < data.length; i++) {
            notSorted.hundreds[i] = data[i] / 100;
            notSorted.tens[i] = data[i] % 100 / 10;
            notSorted.ones[i] = data[i] % 10;
        }

        NumDigits onesSorted = countingSort(notSorted.ones, notSorted);
        NumDigits tensSorted = countingSort(onesSorted.tens, onesSorted);
        NumDigits hundredsSorted = countingSort(tensSorted.hundreds, tensSorted);

        int[] sorted = new int[data.length];
        for (int i = 0; i < data.length; i++) {
            sorted[i] = hundredsSorted.hundreds[i] * 100 + hundredsSorted.tens[i] * 10 + hundredsSorted.ones[i];
        }
        data = sorted;
    }

    private static NumDigits countingSort(int[] sortingArray, NumDigits wholeData) {
        int[] numberIndexes = new int[Constants.MAX_VALUE + 1];

        // подсчитываем числа
        for (int i = 0; i < sortingArray.length; i++) {
            int value = sortingArray[i];
            numberIndexes[value] += 1;
        }

        // определяем индексы
        int sum = 0;
        for (int i = 0; i < numberIndexes.length - 1; i++) {
            sum += numberIndexes[i];
            numberIndexes[i] = sum;
        }

        NumDigits res = new NumDigits(sortingArray.length);
        // сортируем
        for (int i = sortingArray.length - 1; i >= 0; i--) {
            int value = sortingArray[i];
            numberIndexes[value] -= 1;
            int ix = numberIndexes[value];
            res.hundreds[ix] = wholeData.hundreds[i];
            res.tens[ix] = wholeData.tens[i];
            res.ones[ix] = wholeData.ones[i];
        }
        return res;
    }
}
