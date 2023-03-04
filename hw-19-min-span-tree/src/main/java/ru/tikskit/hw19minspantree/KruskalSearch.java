package ru.tikskit.hw19minspantree;

import java.util.ArrayList;
import java.util.List;

/**
 * Реализация алгоритма Краскала
 *
 * Граф должен быть задан матрицей смежности, поскольку только в этом случае возможно
 * 1. Задавать веса ребер
 * 2. Задавать не направленный граф
 */
public class KruskalSearch {
    /**
     * Возвращает все ребра в графе
     * Поскольку матрица смежности ненаправленного гроафа симметрична относительно главной диагонали, мы не будем обходить
     * всю матрицу, а только ту ее часть, которая выше диагонали. Это также позволит избежать дублирование одних и тех же
     * ребер
     * @param graph матрица смежности графа
     * @return Список ребер графа с весами
     */
    private WeightedEdge[] getAllEdges(int[][] graph) {
        List<WeightedEdge> list = new ArrayList<>();
        for (int r = 0; r < graph.length; r++) {
            for (int c = r; c < graph[r].length; c++) {
                if (graph[r][c] != 0) {
                    list.add(new WeightedEdge(r, c, graph[r][c]));
                }
            }
        }
        WeightedEdge[] res = new WeightedEdge[list.size()];
        list.toArray(res);
        return res;
    }

    /**
     * Возвращает отсортированные по весу в порядке неубывания все ребра графа
     * @param graph Матрица смежности графа
     */
    private Edge[] getSortedEdges(int[][] graph) {
        WeightedEdge[] edges = getAllEdges(graph);
        if (edges.length == 0) {
            return new Edge[0];
        }
        // Используем сортировку Шелла для сортировки ребер по их весу
        SortShell shellSort = new SortShell(edges);
        shellSort.sort();

        return edges;
    }

    /**
     * Получить минимальный скелет графа
     * @param graph матрица смежности графа
     * @return список ребер
     */
    public Edge[] getMinSpinTree(int[][] graph) {
        /* Нам нужно из графа перебрать все ребра в порядке неубывания. Для этого сперва получим отсортированный по весу
        * массив ребер  */
        Edge[] sortedEdges = getSortedEdges(graph);
        if (sortedEdges.length == 0) {
            return new Edge[0];
        }

        VertexSet sets = new VertexSet(sortedEdges);
        List<Edge> edges = new ArrayList<>();

        for (Edge e : sortedEdges) {
            Integer delegateV1 = sets.findDelegateFor(e.getV1());
            Integer delegateV2 = sets.findDelegateFor(e.getV2());
            if (!delegateV1.equals(delegateV2)) {
                sets.unite(e.getV1(), e.getV2());
                edges.add(e);
            }
        }

        Edge[] res = new Edge[edges.size()];
        edges.toArray(res);
        return res;
    }
}
