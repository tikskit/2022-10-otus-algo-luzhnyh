package ru.tikskit.hw03algalgorithms.power;

/**
 * Реализация интерфейса, в которой возведение выполняется в цикле (итеративный подход)
 */
public class PowerLoop implements Power {


    public double power(double base, int exponent) {

        if (exponent < 0) {
            throw new IllegalArgumentException("Показатель степени должен быть неотрицательным");
        }

        if (exponent == 0) {
            return 1.0;
        }

        double res = base;
        for (int i = 2; i <= exponent; i++) {
            res *= base;
        }

        return res;
    }
}
