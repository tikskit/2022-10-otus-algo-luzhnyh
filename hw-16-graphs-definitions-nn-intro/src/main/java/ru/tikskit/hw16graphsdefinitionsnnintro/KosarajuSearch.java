package ru.tikskit.hw16graphsdefinitionsnnintro;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Поиск сильно связных компонентов в орграфе
 */
public class KosarajuSearch {
    /**
     * Чтобы поменять направление всех дуг, мы просто транспонируем матрицу смежности
     * @param graph матрица смежности графа
     * @return транспонированная матрица смежности
     */
    private int[][] transpose(int[][] graph) {
        int[][] res = new int[graph.length][];
        for (int r = 0; r < graph.length; r++) {
            res[r] = new int[graph.length];
            for (int c = 0; c < graph.length; c++) {
                res[r][c] = graph[c][r];
            }
        }
        return res;
    }

    /**
     * Получить список смежных непосещенных
     */
    private Set<Integer> getAdjNotVisited(int vertex, int[][] graph, List<Integer> visited) {
        Set<Integer> res = new HashSet<>();
        int[] adj = graph[vertex];
        for (int a = 0; a < adj.length; a++) {
            if (adj[a] == 1 // Это смежная вершина?
                    && !visited.contains(a)) {
                res.add(a);
            }
        }

        return res;
    }

    /**
     * Рекурсивный обход вершин
     * @param graph Матрица смежности графа
     * @param vertex Вершина от которой выполняется обход
     * @param visited Список посещенных вершин
     * @return Список вершин в том порядке (но до их инвертирования), в котором мы будем их использовать при обходе
     * инвертироавнного графа
     */
    private List<Integer> traverseFrom(int[][] graph, int vertex, List<Integer> visited) {
        visited.add(vertex);
        Set<Integer> adjNotVisited = getAdjNotVisited(vertex, graph, visited);
        List<Integer> res = new ArrayList<>();
        if (adjNotVisited.isEmpty()) {
            res.add(vertex);
            return res;
        } else {
            for (Integer a : adjNotVisited) {
                if (!visited.contains(a)) {
                    List<Integer> adj = traverseFrom(graph, a, visited);
                    res.addAll(adj);
                }
            }
            res.add(vertex);
        }
        return res;
    }

    /**
     * Выбрать очередную вершину из графа для обхода
     * @param graph Матрица смежности графа
     * @param visited Посещенные вершины (из них не выбираем)
     * @return Индекс вершины, либо empty если не осталось не посещенных вершних
     */
    private Optional<Integer> pickVertex(int[][] graph, List<Integer> visited) {
        for (int i = 0; i < graph.length; i++) {
            if (!visited.contains(i)) {
                return Optional.of(i);
            }
        }
        return Optional.empty();
    }

    /**
     * Обход вершин и составление списка посещенных вершин
     * @param graph Матрица смежности
     * @return выписанные посещенные вершины
     */
    private List<Integer> traverse(int[][] graph) {
        List<Integer> visited = new ArrayList<>(graph.length);
        Optional<Integer> vertex = pickVertex(graph, visited);
        List<Integer> res = new ArrayList<>();
        while (vertex.isPresent()) {
            List<Integer> writtenDown = traverseFrom(graph, vertex.get(), visited);
            res.addAll(writtenDown);
            vertex = pickVertex(graph, visited);
        }
        return res;
    }

    /**
     * Поиск сильно связных компонентов графа
     * @param graph Матрица смежности;
     * @return Список сильно связных компонентов
     */
    public List<Set<Integer>> find(int[][] graph) {
        if (graph.length == 0) {
            return Collections.emptyList();
        }

        // Выполним обход графа и выпишем посещенные вершины
        List<Integer> writtenDown = traverse(graph);
        // Запишем посещенные вершины в обратном порядке
        Collections.reverse(writtenDown);
        // Инвертируем дуги орграфа
        int[][] transposedGraph = transpose(graph);

        return null;
    }
}
