package ru.tikskit.hw16graphsdefinitionsnnintro;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.Stack;

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
     * Есть ли у вершины не посещенные смежные
     * @param vertex вершина для проверки
     * @param graph мартица смежности
     * @param visited список посещенных вершин
     */
    private boolean hasNotVisitedAdj(int vertex, int[][] graph, List<Integer> visited) {
        int[] adj = graph[vertex]; // Получим смежные вершины
        for (int a = 0; a < adj.length; a++) {
            if (adj[a] == 1 // Это смежная вершина?
                    && !visited.contains(a)) { // Это не посещенная вершина?
                return true;
            }
        }

        return false;
    }

    private List<Integer> traverseFrom(int[][] graph, int start) {
        List<Integer> visited = new ArrayList<>(graph.length);
        Stack<Integer> stack = new Stack<>();
        // Выписанные вершины в требуемом порядке
        List<Integer> writtenDown = new ArrayList<>(graph.length);

        stack.push(start);
        List<Integer> path = new ArrayList<>();
        while (!stack.empty()) {
            Integer vertex = stack.pop();
            if (!visited.contains(vertex)) {
                visited.add(vertex);
            }

            if (!hasNotVisitedAdj(vertex, graph, visited)) {
                writtenDown.add(vertex);
            }

            int[] adj = graph[vertex]; // Получим смежные вершины
            boolean noPath = true;
            for (int a = 0; a < adj.length; a++) {
                if (adj[a] == 1 // Это смежная вершина?
                        && !visited.contains(a)) { // Это не посещенная вершина?
                    stack.push(a); // если сюда попали, значит из vertex есть куда идти
                    noPath = false;
                }
            }
        }

        return visited;
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
     * @return
     */
    private List<Integer> traverse(int[][] graph) {
        List<Integer> visited = new ArrayList<>(graph.length);
        Optional<Integer> vertex = pickVertex(graph, visited);
        while (vertex.isPresent()) {
            List<Integer> visitedFrom = traverseFrom(graph, vertex.get());
            visited.addAll(visitedFrom);
            vertex = pickVertex(graph, visited);
        }
        return visited;
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
        List<Integer> vertexVisited = traverse(graph);
        // Запишем посещенные вершины в обратном порядке
        Collections.reverse(vertexVisited);
        // Инвертируем дуги орграфа
        int[][] transposedGraph = transpose(graph);

        return null;
    }
}
