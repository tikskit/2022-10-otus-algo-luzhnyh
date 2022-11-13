package ru.tikskit.hw03algalgorithms.primenumbers;

/**
 * Интерфейс для счетчика простых чисел
 */
public interface PrimeNumbersCounter {
    /**
     * Возвращает количество целых чисел до числа N
     */
    long getCount(long N);
}
