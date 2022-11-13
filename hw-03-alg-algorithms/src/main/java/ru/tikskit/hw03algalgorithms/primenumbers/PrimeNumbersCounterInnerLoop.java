package ru.tikskit.hw03algalgorithms.primenumbers;

/**
 * Реализация счетчика простых чисел на основе перебора во внутреннем цикле
 */
public class PrimeNumbersCounterInnerLoop implements PrimeNumbersCounter {
    @Override
    public long getCount(long n) {
        long[] primes = new long [(int)(n / 2)];
        int count = 0;
        primes[count++] = 2;
        for (int i = 3; i <= n; i++) {
            if (isPrime(i, primes)) {
                primes[count++] = i;
            }
        }
        return count;
    }

    private boolean isPrime(long num, long[] primes) {
        if (num == 1) {
            return false;
        }
        if (num == 2) {
            return true;
        }
        double sqrt = Math.sqrt(num);
        for (int i = 0; primes[i] <= sqrt; i++) {
            if (num % primes[i] == 0) {
                return false;
            }
        }

        return true;
    }
}
