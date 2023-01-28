package ru.tikskit.heapsort;

import java.util.Random;

public class RandomDataProvider {
    public int[] getData(int size) {
        Random rnd = new Random();
        return rnd.ints(size, 0, 100).toArray();
    }
}
