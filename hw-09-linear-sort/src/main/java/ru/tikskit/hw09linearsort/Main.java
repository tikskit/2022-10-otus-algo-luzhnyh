package ru.tikskit.hw09linearsort;

import ru.tikskit.hw09linearsort.utils.OrderChecker;
import ru.tikskit.hw09linearsort.utils.RandomFromRangeDataProvider;
import ru.tikskit.hw09linearsort.utils.Timer;

public class Main {

    public static void main(String[] args) {
        RandomFromRangeDataProvider rnd = new RandomFromRangeDataProvider();
        int[] data = rnd.getData(1000_000_000);

        // Сравнивать все алгоритмы будем на одних и тех же рандомных данных, для этого клонируем их сперва

//        sort(new BucketSort(cloneData(data)));
//        sort(new CountingSort(cloneData(data)));
//        sort(new RadixSort(cloneData(data)));
        sort(new SortShell(cloneData(data)));
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
