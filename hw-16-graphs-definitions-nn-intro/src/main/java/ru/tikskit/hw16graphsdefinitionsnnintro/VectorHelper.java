package ru.tikskit.hw16graphsdefinitionsnnintro;

import java.util.EmptyStackException;

import static ru.tikskit.hw16graphsdefinitionsnnintro.Constants.EMPTY;

public final class VectorHelper {

    public static int[][] initVector(int vertexCount) {
        int rowCount = vertexCount;
        int colCount = vertexCount + 1;
        int[][] res = new int[rowCount][];
        for (int r = 0; r < rowCount; r++) {
            res[r] = new int[colCount];
            res[r][0] = r;
            for (int c = 1; c < colCount; c++) {
                res[r][c] = EMPTY;
            }
        }
        return res;
    }
}
