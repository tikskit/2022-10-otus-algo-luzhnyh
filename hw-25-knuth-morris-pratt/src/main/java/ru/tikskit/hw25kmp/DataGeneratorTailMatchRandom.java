package ru.tikskit.hw25kmp;

import java.util.Random;

public class DataGeneratorTailMatchRandom implements DataGenerator {

    private static final int TEXT_LENGTH = 1000000;
    private static final int MASK_LENGTH = 1000;
    private final Random random = new Random();

    private String getRndString(int len) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append((char) random.nextInt((int) 'A', (int) 'Z'));
        }
        return sb.toString();
    }
    @Override
    public Data gen() {
        StringBuilder sb = new StringBuilder();
        sb.append(getRndString(TEXT_LENGTH));
        String mask = getRndString(MASK_LENGTH);
        sb.append(mask);

        return new Data(sb.toString(), mask, "Random text mask match at end");
    }
}
