package ru.tikskit.hw04datastructures;

public class Program {
    public static void main(String[] args) {
        testAdd(new VectorArray<>(), 10000000);
        testAdd(new FactorArray<>(2), 10000000);
        testAdd(new MatrixArray<>(), 10000000);
    }

    private static void testAdd(Array<Integer> array, int count) {
        long start = System.currentTimeMillis();
        for (int i = 0; i <= count; i++) {
            array.add(i);
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start + "ms");

    }
}
