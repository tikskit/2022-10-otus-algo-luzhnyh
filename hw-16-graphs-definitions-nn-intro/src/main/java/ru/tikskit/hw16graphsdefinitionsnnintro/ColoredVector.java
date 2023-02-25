package ru.tikskit.hw16graphsdefinitionsnnintro;

import lombok.Data;

import static ru.tikskit.hw16graphsdefinitionsnnintro.Constants.EMPTY;

/**
 * Раскрашенный вектор смежностей
 */
public class ColoredVector {
    private final Vertex[] data;

    private int getRowLength(int[] row) {
        if (row.length == 0) {
            // Должен быть хотя бы 1 элемент, с индексом
            throw new IllegalArgumentException(String.format("Размер строки %s в матрице равен нулю", row));
        }

        int res = 0;
        for (int c = 1; c < row.length; c++) {
            if (row[c] != EMPTY) {
                res++;
            }
        }
        return res;
    }

    public ColoredVector(int[][] vector) {
        data = new Vertex[vector.length];
        for (int row = 0; row < vector.length; row++) {
            int rowLength = getRowLength(vector[row]);
            int[] adjacent = new int[rowLength];
            System.arraycopy(vector[row], 1, adjacent, 0, rowLength - 1);
            data[row] = new Vertex(row, adjacent);
        }
    }

    public Vertex getVertex(int index) {
        return data[index];
    }

    public int getVertexCount() {
        return data.length;
    }
}
