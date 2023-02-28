package ru.tikskit.hw17searchnnsort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Реализация алгоритма Демукрона для топологической сортировки орграфа
 */
public class DemukronSort {

    /**
     * Выбрать из уровня все вершины с нулевой полустепенью
     */
    private List<Integer> getZeroVertexes(Integer[] level) {
        List<Integer> res = new ArrayList<>(level.length);
        for (int v = 0; v < level.length; v++) {
            if (level[v] != null && level[v].equals(0)) {
                res.add(v);
            }
        }

        return res;
    }

    /**
     * Вычитаем из текущего уровня все вершины, у которых текущая полустепень равна нулю
     * @param level текущий уровень
     * @param zeroVertexes список веришн с текущей поулсуммой равной 0
     * @param graph матрица смежности графа
     * @return уровень за вычетом вершин
     */
    private Integer[] subtract(Integer[] level, List<Integer> zeroVertexes, int[][] graph) {
        Integer[] res = Arrays.copyOf(level, level.length);

        for (int c = 0; c < level.length; c++) {
            for (Integer zv : zeroVertexes) {
                if (!zv.equals(c) && level[c] != null && res[c] != null) {
                    res[c] -= graph[zv][c];
                } else {
                    res[c] = null;
                }
            }

        }

        return res;
    }

    /**
     * Получаем начальный уровень - каждый элемент это сумма всех полустепеней выхода для вершины (индекс массива = индекс вершины)
     */
    private Integer[] getInitLevel(int[][] graph) {
        Integer[] res = new Integer[graph.length];
        Arrays.fill(res, 0);
        for (int r = 0; r < graph.length; r++) {
            for (int c = 0; c < res.length; c++) {
                res[c] += graph[r][c];
            }
        }

        return res;
    }

    /**
     * Топологическая сортировка орграфа
     *
     * @param graph Матрица смежности графа
     * @return списк отсортированных вершин
     */
    public List<Integer> sort(int[][] graph) {
        Integer[] level = getInitLevel(graph);
        List<Integer> res = new ArrayList<>(graph.length);
        List<Integer> zeroVertexes = getZeroVertexes(level);
        if (zeroVertexes.isEmpty()) {
            throw new DemukronSearchException("Не обнаружено вершин с нулевой полустепенью входа");
        }

        while (!zeroVertexes.isEmpty()) {
            res.addAll(zeroVertexes);
            level = subtract(level, zeroVertexes, graph);
            zeroVertexes = getZeroVertexes(level);
        }
        return res;
    }
}
