package ru.tikskit.hw03algalgorithms.fibonacci;

/**
 * Интерфейс для поиска чисел Фибоначчи
 */
public interface FiboNumbers {

    /**
     * Возвращает число Фибоначчи по его номеру
     * @param numNo номер числа начиная с 0
     */
    long getNum(long numNo);
}
