package ru.tikskit.hw10avltree;

import java.util.stream.IntStream;

public class AscDataProvider implements DataProvider {
    @Override
    public int[] getData(int size) {
        return IntStream.range(0, size).toArray();
    }
}
