package ru.tikskit.hw19minspantree;

public class SortShell {
    private final WeightedEdge[] data;

    public SortShell(WeightedEdge[] data) {
        this.data = data;
    }

    private void swap(int x, int y) {
        WeightedEdge t = data[x];
        data[x] = data[y];
        data[y] = t;
    }

    public void sort() {
        for (int gap = data.length / 2; gap > 0; gap /= 2) {
            for (int j = gap; j < data.length; j++) {
                for (int i = j; i >= gap; i -= gap) {
                    if (data[i].getWeight() < data[i - gap].getWeight()) {
                        swap(i, i - gap);
                    } else {
                        break;
                    }
                }
            }
        }
    }
}
