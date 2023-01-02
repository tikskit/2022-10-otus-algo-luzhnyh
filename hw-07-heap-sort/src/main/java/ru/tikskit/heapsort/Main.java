package ru.tikskit.heapsort;

public class Main {

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

    public static void main(String[] args) {
        RandomDataProvider rnd = new RandomDataProvider();
        int[] data = rnd.getData(1000000);

        sort(new SelectSort(cloneData(data)));
        sort(new HeapSort(cloneData(data)));
    }
}
