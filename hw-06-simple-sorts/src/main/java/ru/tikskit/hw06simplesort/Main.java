package ru.tikskit.hw06simplesort;

public class Main {
    public static void main(String[] args) {
        RandomDataProvider rnd = new RandomDataProvider();
        int[] data = rnd.getData(100000);

        // Сравнивать все алгоритмы будем на одних и тех же рандомных данных, для этого клонируем их сперва

        sort(new SortBubble(cloneData(data)));
        sort(new SortInsert(cloneData(data)));
        sort(new SortInsertBatchMover(cloneData(data)));
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
