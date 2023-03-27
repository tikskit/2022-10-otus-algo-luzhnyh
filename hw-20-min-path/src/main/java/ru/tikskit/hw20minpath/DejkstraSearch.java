package ru.tikskit.hw20minpath;

import java.util.ArrayList;
import java.util.List;

/**
 * Реализация алгоритма поиска кратчайшего пути Дейкстры
 */
public class DejkstraSearch {


    /**
     * Поиск кратчайшего пути в графе из заданной вершины
     *
     * @param graph матрица смежности графа
     * @param from  вершина, от которой начинаем поиск
     * @param to    вершина, до которой ищем путь
     * @return вершины от from до to в порядке обхода
     */
    public int[] find(int[][] graph, int from, int to) {
        int totalCost = 0;
        int v = from;
        List<Integer> path = new ArrayList<>();
        path.add(from);
        while (v != to) {
            int[] adjs = graph[v];
            // Найдем самую дешевую из смежных вершин
            int cheapestVertex = -1;
            for (int a = 0; a < adjs.length; a++) {
                if (adjs[a] > 0) {
                    if (cheapestVertex == -1 || adjs[cheapestVertex] > adjs[a]) {
                        cheapestVertex = a;
                    }
                }
            }
            if (cheapestVertex < 0) {
                throw new IllegalStateException(String.format("Вершина %s не достижима из вершины %s", to, from));
            }

            totalCost += adjs[cheapestVertex];
            graph[v][cheapestVertex] = 0;
            graph[cheapestVertex][v] = 0;
            path.add(cheapestVertex);
            v = cheapestVertex;
        }

        int[] res = new int[path.size()];
        for (int i = 0; i < path.size(); i++) {
            res[i] = path.get(i);
        }
        return res;
    }
}
