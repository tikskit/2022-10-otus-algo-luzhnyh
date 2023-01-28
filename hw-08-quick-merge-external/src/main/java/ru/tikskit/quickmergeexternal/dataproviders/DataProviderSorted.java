package ru.tikskit.quickmergeexternal.dataproviders;

import ru.tikskit.quickmergeexternal.dataproviders.DataProvider;

import java.util.stream.IntStream;

/**
 * Провайдер данных, которые предоставляет отсортированные данные
 */
public class DataProviderSorted implements DataProvider {
    @Override
    public int[] getData(int size) {
        return IntStream.range(0, size).toArray();
    }
}
