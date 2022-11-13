package ru.tikskit.hw03algalgorithms.fibonacci;

public class FiboRunner {

    public static void main(String[] args) {
        FiboNumbers fiboNumbers = new FiboNumbersLoop();
        long start = System.currentTimeMillis();
        long num = fiboNumbers.getNum(100000L);

        long end = System.currentTimeMillis();
        System.out.printf("%s (%s ms)%n", num, end - start);
    }
}
