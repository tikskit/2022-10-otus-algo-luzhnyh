package ru.tikskit.hw25kmp;

import java.util.Random;

public class DataGeneratorRandomBlocks implements DataGenerator {
    private static final int TEXT_LENGTH = 100000000;
    private static final int MASK_LENGTH = 10;
    private static final int MAX_BLOCK_LENGTH = 4;
    private static final char[] LETTERS = {'A', 'B', 'C', 'D'};
    private static final char BLOCK_SEPARATOR = '-';
    private final Random random = new Random();

    @Override
    public Data gen() {
        return new Data(genText(TEXT_LENGTH), genText(MASK_LENGTH), "Repeating char blocks");
    }

    private String genText(int length) {
        StringBuilder sb = new StringBuilder(length);
        while (sb.length() < length) {
            int blockLength = getBlockLength();
            blockLength = length - (sb.length() + blockLength + 1) > 0 ? blockLength : length - (sb.length() + 1);
            if (blockLength <= 0) {
                return sb.toString();
            }

            StringBuilder block = new StringBuilder(blockLength);
            if (!sb.isEmpty()) {
                sb.append(BLOCK_SEPARATOR);
            }
            for (int i = 0; i < blockLength; i++) {
                block.append(getLetter());
            }
            sb.append(block);
        }

        return sb.toString();
    }

    private char getLetter() {
        int i = random.nextInt(LETTERS.length);
        return LETTERS[i];
    }

    private int getBlockLength() {
        return random.nextInt(1, MAX_BLOCK_LENGTH + 1);
    }
}
