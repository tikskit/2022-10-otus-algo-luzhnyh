package ru.tikskit.minhashsimhash;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Характеристическая матрица
 */
public class CMatrix {
    private final byte[][] values;
    private final Map<String, Integer> colIndexes;

    public CMatrix(Set<String> docIds, int setCount) {
        values = new byte[setCount][];
        for (int r = 0; r < setCount; r++) {
            values[r] = new byte[docIds.size()];
        }

        colIndexes = new HashMap<>(docIds.size());
        docIds.stream()
                .collect(Collectors.toMap());
    }
}
