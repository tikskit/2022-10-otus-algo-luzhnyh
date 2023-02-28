package ru.tikskit.hw16graphsdefinitionsnnintro;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MainClass {
    public static void main(String[] args) {
        int[][] graph = {
              //{0, 1, 2, 3, 4, 5, 6, 7}
                {0, 1, 0, 0, 0, 0, 0, 0}, //0
                {0, 0, 1, 0, 1, 1, 0, 0}, //1
                {0, 0, 0, 1, 0, 0, 1, 0}, //2
                {0, 0, 1, 0, 0, 0, 0, 1}, //3
                {1, 0, 0, 0, 0, 1, 0, 0}, //4
                {0, 0, 0, 0, 0, 0, 1, 0}, //5
                {0, 0, 0, 0, 0, 1, 0, 0}, //6
                {0, 0, 0, 1, 0, 0, 1, 0}, //7
        };

        KosarajuSearch s = new KosarajuSearch();
        List<Set<Integer>> sccs = s.find(graph);
        System.out.println("Были найдены следующие компоненты сильной связности:");
        for (Set<Integer> scc : sccs) {
            System.out.println(scc);
        }

    }
}
