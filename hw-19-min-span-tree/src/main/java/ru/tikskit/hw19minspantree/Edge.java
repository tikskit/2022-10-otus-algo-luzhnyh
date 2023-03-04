package ru.tikskit.hw19minspantree;

/**
 * Класс, содержащий информацию о ребре, множество которых составляет минимальный скелет
 */

public class Edge {
    private final int v1;
    private final int v2;

    public Edge(int v1, int v2) {
        if (v1 == v2) {
            // В этом графе петель быть не должно
            throw new IllegalArgumentException(String.format("Вернишны %s не могут совпадать", v1));
        }
        this.v1 = v1;
        this.v2 = v2;
    }

    public int getV1() {
        return v1;
    }

    public int getV2() {
        return v2;
    }

    @Override
    public String toString() {
        return String.format("%s-%s", Utils.numToLetter(v1), Utils.numToLetter(v2));
    }
}
