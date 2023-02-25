package ru.tikskit.hw16graphsdefinitionsnnintro;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Поиск сильносвяных компонентов в орграфе
 */
public class KosarajuSearch {

    /**
     * Выбрать вершину их вектора смежности для поиска сильно связного компонента
     * @param vector Вектор смежности, в котором ищем вершину.
     * @return Возвращает первую попавшуюся белую вершину, если есть. Если нет, возвращает null
     */
    private Integer pickVertex(ColoredVector vector) {
        for (int row = 0; row < vector.getVertexCount(); row++) {
            if (vector.getVertex(row).getColor().equals(VertexColor.WHITE)) {
                return row;
            }
        }
        return null;
    }

    private ColoredVector invert(ColoredVector vector) {
        int[][] inverted = VectorHelper.initVector(vector.getVertexCount());
        for (int v = 0; v < vector.getVertexCount(); v++) {
            Vertex vertex = vector.getVertex(v);
        }
    }

    /**
     * Поиск сильно связных компонентов графа
     * @param graph Вектор смежности. Индекс каждой строки совпадает с индексом вершины (нумерация от 0). Поскольку
     *              нумерация от 0, то пустые значения должны быть -1;
     * @return Список сильно связных компонентов
     */
    public List<Set<Integer>> find(int[][] graph) {
        if (graph.length == 0) {
            return Collections.emptyList();
        }
        ColoredVector vector = new ColoredVector(graph);

        /**
         * Выберем вершину, с которой начнем поиск
         */
        int firstVertexIndex = pickVertex(vector);
    }
}
