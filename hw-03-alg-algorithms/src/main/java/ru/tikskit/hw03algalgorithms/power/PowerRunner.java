package ru.tikskit.hw03algalgorithms.power;


/**
 * Запуск алгоритмов возведения числа в степень
 */
public class PowerRunner {

    public static void main(String[] args) {
        Power powerLoop = new PowerLoop();
        long start = System.currentTimeMillis();
        double power = powerLoop.power(2, 4);
        long end = System.currentTimeMillis();
        System.out.printf("%s (%s ms)%n", power, end - start);
    }
}
