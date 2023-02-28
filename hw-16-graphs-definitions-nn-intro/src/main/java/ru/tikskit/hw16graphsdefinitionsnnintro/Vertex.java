package ru.tikskit.hw16graphsdefinitionsnnintro;

/**
 * Вершины
 */
public class Vertex {
    /**
     * Индекс вершины
     */
    private final int index;

    private VertexColor color;

    public Vertex(int index) {
        this.index = index;
        this.color = VertexColor.WHITE;
    }
}
