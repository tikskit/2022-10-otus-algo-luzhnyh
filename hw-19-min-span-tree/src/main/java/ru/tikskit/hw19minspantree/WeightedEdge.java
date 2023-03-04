package ru.tikskit.hw19minspantree;

public class WeightedEdge extends Edge {
    private final int weight;

    public WeightedEdge(int v1, int v2, int weight) {
        super(v1, v2);
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }
}
