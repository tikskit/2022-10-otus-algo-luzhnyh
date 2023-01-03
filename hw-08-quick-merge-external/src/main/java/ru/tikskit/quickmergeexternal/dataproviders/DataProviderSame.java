package ru.tikskit.quickmergeexternal.dataproviders;

/**
 * Провайдер данных, который производит одинаковые данные
 */
public class DataProviderSame implements DataProvider {
    @Override
    public int[] getData(int size) {
        int data[] = new int[size];

        return data;
    }
}
