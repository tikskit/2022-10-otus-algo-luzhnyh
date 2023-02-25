package ru.tikskit.hw16graphsdefinitionsnnintro;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                {0, 0, 0, 0, 0, 1, 0, 1}, //6
                {0, 0, 0, 1, 0, 0, 0, 0}, //7
        };

        KosarajuSearch s = new KosarajuSearch();
        s.find(graph);

    }
}
