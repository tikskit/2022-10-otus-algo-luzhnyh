package ru.tikskit.hw06simplesort;

/**
 * Проверка, отсортированы ли данные в массиве
 */
public class OrderChecker {

    public void check(int[] data) {
        for (int i = 0; i < data.length - 1; i++) {
            if (data[i] > data[i + 1]) {
                System.out.printf("Элемент [%s] = %s больше [%s] = %s%n", i, data[i], i + 1, data[i + 1]);
            }
        }
    }
}
