package ru.tikskit.hw10avltree;

import java.util.Random;

public class RandomDataProvider implements DataProvider {

    @Override
    public int[] getData(int size) {
        Random rnd = new Random();
        return rnd.ints(size, 0, size).toArray();
    }
}
