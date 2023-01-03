package ru.tikskit.quickmergeexternal.dataproviders;

import java.util.Random;

/**
 * Провайдер данных, которые предоставляет отсортированные данные
 */
public class DataProviderAlmostSorted implements DataProvider {
    @Override
    public int[] getData(int size) {
        Random rnd = new Random();

        int data[] = new int[size];
        for (int i = 0; i < size; i++) {

            data[i] = i  + rnd.nextInt(-8, 17);
        }
        return data;
    }
}
