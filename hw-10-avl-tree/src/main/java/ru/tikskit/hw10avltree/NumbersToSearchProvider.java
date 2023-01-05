package ru.tikskit.hw10avltree;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class NumbersToSearchProvider {

    private static final int R = 10;

    public int[] getNumbers(int[] data) {
        int count = data.length / R;
        Set<Integer> res = new HashSet<>(count);
        Random rnd = new Random();
        while (res.size() < count) {
            int ix = rnd.nextInt(data.length);
            res.add(data[ix]);
        }

        return res.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }
}
