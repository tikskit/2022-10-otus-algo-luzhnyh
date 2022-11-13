package ru.tikskit.hw03algalgorithms.fibonacci;

/**
 * Рекурсивная реализация поиска числа Фибоначчи
 */
public class FiboNumbersRecursion implements FiboNumbers {

    /**
     * Возвращает число Фибоначчи по его номеру
     * @param numNo номер числа начиная с 0
     */
    @Override
    public long getNum(long numNo) {
        if (numNo < 0) {
            throw new IllegalArgumentException("Номер числа должен начинаться с 0");
        }

        if (numNo == 0) {
            return 0;
        }
        if (numNo == 1) {
            return 1;
        }


        long prePrev = getNum(numNo - 2);
        long prev = getNum(numNo - 1);
        return prePrev + prev;
    }
}
