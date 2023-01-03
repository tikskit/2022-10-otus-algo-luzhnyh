package ru.tikskit.quickmergeexternal.dataproviders;

import java.util.Random;

/**
 * Провайдер данных, который возвращает случайные данные
 */
public class DataProviderRandom implements DataProvider {

    @Override
    public int[] getData(int size) {
        Random rnd = new Random();
        return rnd.ints(size, 0, 100).toArray();
    }
}
