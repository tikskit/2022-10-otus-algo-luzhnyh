package ru.tikskit.hw32boyermoore;

import java.util.Arrays;

/**
 * Генератор тестовых данных, в которых маска совпадает, кроме последнего символа
 */
public class DataGeneratorMaskMatchButLastCharacter implements DataGenerator {
    private static final int TEXT_LENGTH = 1000000;
    private static final int MASK_LENGTH = 1000;
    @Override
    public Data gen() {

        char[] textArr = new char[TEXT_LENGTH];
        Arrays.fill(textArr, 'A');

        char[] maskArr = new char[MASK_LENGTH];
        Arrays.fill(maskArr, 'A');
        maskArr[MASK_LENGTH - 1] = 'B';

        return new Data(new String(textArr), new String(maskArr), "Mask match but last character");
    }
}
