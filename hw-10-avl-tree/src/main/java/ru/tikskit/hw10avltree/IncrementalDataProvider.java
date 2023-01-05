package ru.tikskit.hw10avltree;

import java.util.stream.IntStream;

public class IncrementalDataProvider implements DataProvider {
    @Override
    public int[] getData(int size) {
        return IntStream.range(0, size).toArray();
    }
}
