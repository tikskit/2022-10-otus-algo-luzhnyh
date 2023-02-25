package ru.tikskit.hw16graphsdefinitionsnnintro;

import lombok.Data;

/**
 * Вершины
 */
@Data
public class Vertex {
    /**
     * Индекс вершины
     */
    private final int index;

    private VertexColor color;

    /**
     * Массив смежных вершин
     */
    private int[] adjacents;

    public Vertex(int index, int... adjacents) {
        this.index = index;
        this.color = VertexColor.WHITE;
        this.adjacents = adjacents;
    }
}
