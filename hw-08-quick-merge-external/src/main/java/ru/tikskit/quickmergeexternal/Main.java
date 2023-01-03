package ru.tikskit.quickmergeexternal;

import ru.tikskit.quickmergeexternal.dataproviders.DataProvider;
import ru.tikskit.quickmergeexternal.dataproviders.DataProviderAlmostSame;
import ru.tikskit.quickmergeexternal.dataproviders.DataProviderAlmostSorted;
import ru.tikskit.quickmergeexternal.dataproviders.DataProviderRandom;
import ru.tikskit.quickmergeexternal.utils.OrderChecker;
import ru.tikskit.quickmergeexternal.utils.Timer;

public class Main {
    public static void main(String[] args) {
        DataProvider dataProvider = new DataProviderRandom();
        int[] data = dataProvider.getData(1000_000_000);

        // Сравнивать все алгоритмы будем на одних и тех же рандомных данных, для этого клонируем их сперва

//        sort(new QuickSort(cloneData(data)));
//        sort(new MergeSort(cloneData(data)));
        sort(new SortShell(cloneData(data)));
//        sort(new SortInsert(cloneData(data)));
    }

    private static int[] cloneData(int[] src) {
        int[] res = new int[src.length];
        System.arraycopy(src, 0, res, 0, src.length);
        return res;
    }

    private static void sort(Sort sort) {
        Timer timer = new Timer();
        timer.start();
        sort.sort();
        System.out.println(timer.stop() + " ms");

        OrderChecker orderChecker = new OrderChecker();
        orderChecker.check(sort.getData());
    }
}
