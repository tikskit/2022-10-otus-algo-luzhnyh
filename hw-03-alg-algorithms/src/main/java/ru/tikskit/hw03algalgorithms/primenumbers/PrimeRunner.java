package ru.tikskit.hw03algalgorithms.primenumbers;

public class PrimeRunner {
    public static void main(String[] args) {
        PrimeNumbersCounter counter = new PrimeNumbersCounterInnerLoop();
        long count = counter.getCount(100000000);
        System.out.println(count);
    }
}
