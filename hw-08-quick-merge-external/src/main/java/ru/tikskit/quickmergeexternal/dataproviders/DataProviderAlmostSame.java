package ru.tikskit.quickmergeexternal.dataproviders;

import java.util.Random;

/**
 * Провайдер данных, который производит одинаковые данные
 */
public class DataProviderAlmostSame implements DataProvider {
    @Override
    public int[] getData(int size) {

        Random rnd = new Random();

        int data[] = new int[size];
        for (int i = 0; i < size; i++) {
            data[i] = 100  + rnd.nextInt(2);
        }
        return data;
    }
}
