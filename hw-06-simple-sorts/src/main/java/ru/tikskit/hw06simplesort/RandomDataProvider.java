package ru.tikskit.hw06simplesort;

import java.util.Random;

public class RandomDataProvider {
    public int[] getData(int size) {
        Random rnd = new Random();
        return rnd.ints(size, 0, 100).toArray();
    }
}
