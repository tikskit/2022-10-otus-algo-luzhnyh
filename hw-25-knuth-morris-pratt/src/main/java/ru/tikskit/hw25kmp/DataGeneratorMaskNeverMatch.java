package ru.tikskit.hw25kmp;

import java.util.Arrays;

/**
 * Генератор тестовых данных, в которых маска не совпадает с текстом
 */
public class DataGeneratorMaskNeverMatch implements DataGenerator {

    private static final int TEXT_LENGTH = 1000000;
    private static final int MASK_LENGTH = 100;
    @Override
    public Data gen() {

        char[] textArr = new char[TEXT_LENGTH];
        Arrays.fill(textArr, 'A');

        char[] maskArr = new char[MASK_LENGTH];
        Arrays.fill(textArr, 'B');

        return new Data(new String(textArr), new String(maskArr), "Mask never match");
    }
}
