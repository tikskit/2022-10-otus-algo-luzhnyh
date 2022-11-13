package ru.tikskit.hw03algalgorithms.fibonacci;

/**
 * Итеративная реализация поиска числа Фибоначчи
 */
public class FiboNumbersLoop implements FiboNumbers {

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

        long prePrev = 0;
        long prev = 1;
        long res;

        int i = 2;
        do {
            res = prePrev + prev;
            prePrev = prev;
            prev = res;
        } while(i++ < numNo);

        return res;
    }

}
