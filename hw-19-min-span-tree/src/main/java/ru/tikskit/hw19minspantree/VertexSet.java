package ru.tikskit.hw19minspantree;

import java.util.HashMap;
import java.util.Map;

/**
 * Структура Union-find для множества вершин
 */
public class VertexSet {
    /**
     * Ключом является вершина, значением - представитель
     */
    private final Map<Integer, Integer> delegates = new HashMap<>();

    public VertexSet(Edge[] edges) {
        for (Edge e : edges) {
            delegates.put(e.getV1(), e.getV1());
            delegates.put(e.getV2(), e.getV2());
        }
    }

    public Integer findDelegateFor(Integer vertex) {
        Integer parent = delegates.get(vertex);
        if (parent == null) {
            throw new IllegalStateException(String.format("Родитель для вершины %s не найден", vertex));
        }

        if (parent.equals(vertex)) {
            return parent;
        } else {
            Integer delegate = findDelegateFor(parent);
            delegates.put(vertex, delegate);
            return delegate;
        }
    }

    /**
     * Объединение двух множеств
     * @param vertex1 Вершина из первого объединяемого множества
     * @param vertex2 Вершина из второго объединяемого множества
     */
    public void unite(Integer vertex1, Integer vertex2) {
        if (vertex1.equals(vertex2)) {
            return;
        }
        Integer delegate1 = findDelegateFor(vertex1);
        Integer delegate2 = findDelegateFor(vertex2);

        if (!delegate1.equals(delegate2)) {
            delegates.put(vertex2, vertex1);
        }
    }
}
