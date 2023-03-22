package ru.tikskit.hw32boyermoore;

import java.util.Arrays;

public class DataGeneratorTailMatch implements DataGenerator {

    private static final int TEXT_LENGTH = 1000000;
    private static final int MASK_LENGTH = 1000;
    @Override
    public Data gen() {
        StringBuilder sb = new StringBuilder();
        char[] textArr = new char[TEXT_LENGTH];
        Arrays.fill(textArr, 'A');

        sb.append(new String(textArr));

        char[] maskArr = new char[MASK_LENGTH];
        Arrays.fill(maskArr, 'B');
        String mask = new String(maskArr);

        sb.append(mask);

        return new Data(sb.toString(), mask, "Mask match at end");
    }
}
