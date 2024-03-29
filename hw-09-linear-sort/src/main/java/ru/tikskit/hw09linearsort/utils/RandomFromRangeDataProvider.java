package ru.tikskit.hw09linearsort.utils;

import ru.tikskit.hw09linearsort.Constants;

import java.util.Random;

public class RandomFromRangeDataProvider {
    public int[] getData(int size) {
        Random rnd = new Random();
        return rnd.ints(size, 0, Constants.MAX_VALUE).toArray();
    }
}
