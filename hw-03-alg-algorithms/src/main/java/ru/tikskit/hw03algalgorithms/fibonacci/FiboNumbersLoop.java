package ru.tikskit.hw03algalgorithms.fibonacci;

/**
 * Итеративная реализация поиска числа Фибоначчи
 */
public class FiboNumbersLoop implements FiboNumbers {

    /**
     * Возвращаем массив со всеми числами Ф до указанного номера
     * @param numNo номер числа, н
     * @return массив, содержащий все числа до указанного
     */
    private int[] getUntil(int numNo) {
        if (numNo < 0) {
            throw new IllegalArgumentException("Номер числа должен начинаться с 0");
        }

        int[] res = new int[numNo + 1];

        res[0] = 0;
        if (numNo == 0) {
            return res;
        }
        res[1] = 1;
        if (numNo == 1) {
            return res;
        }

        for (int i = 2; i < numNo; i++) {
            res[i] = res[i - 2] + res[i - 1];
        }

        return res;
    }

    @Override
    public int getNum(int numNo) {
        if (numNo < 0) {
            throw new IllegalArgumentException("Номер числа должен начинаться с 0");
        }

        int[] res = new int[numNo + 1];

        res[0] = 0;
        if (numNo == 0) {
            return 0;
        }
        res[1] = 1;
        if (numNo == 1) {
            return 1;
        }

        for (int i = 2; i <= numNo; i++) {
            res[i] = res[i - 2] + res[i - 1];
        }

        return res[numNo];
    }

}
