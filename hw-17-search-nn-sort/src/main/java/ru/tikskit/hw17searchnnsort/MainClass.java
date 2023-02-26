package ru.tikskit.hw17searchnnsort;

import java.util.List;

public class MainClass {
    public static void main(String[] args) {
        int[][] graph = {
              // A  B  C  D  E  F  G  H
                {0, 1, 0, 0, 0, 0, 0, 0}, // 0 A
                {0, 0, 0, 0, 1, 0, 0, 0}, // 1 B
                {0, 0, 0, 1, 0, 0, 0, 0}, // 2 C
                {1, 1, 0, 0, 1, 1, 0, 0}, // 3 D
                {0, 0, 0, 0, 0, 0, 1, 0}, // 4 E
                {0, 0, 0, 0, 1, 0, 0, 1}, // 5 F
                {0, 0, 0, 0, 0, 0, 0, 1}, // 6 G
                {0, 0, 0, 0, 0, 0, 0, 0}, // 7 H
        };

        DemukronSort sort = new DemukronSort();
        List<Integer> res = sort.sort(graph);
        System.out.println(res);
    }
}
