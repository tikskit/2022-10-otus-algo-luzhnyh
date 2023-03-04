package ru.tikskit.hw19minspantree;

public class MainClass {
    public static void main(String[] args) {
        int[][] graph = {
              //  A  B  C  D  E  F  G  H  I
                { 0, 9, 0,10, 0, 0, 0, 0, 3},  // A
                { 9, 0, 4, 0, 8, 0, 0, 0,16},  // B
                { 0, 4, 0, 0,14, 1, 0, 0, 0},  // C
                {10, 0, 0, 0, 7, 0,13, 5,11},  // D
                { 0, 8,14, 7, 0,12,15, 0, 0},  // E
                { 0, 0, 1, 0,12, 0, 2, 0, 0},  // F
                { 0, 0, 0,13,15, 2, 0, 6, 0},  // G
                { 0, 0, 0, 5, 0, 0, 6, 0, 0},  // H
                { 3,16, 0,11, 0, 0, 0, 0, 0}   // I
        };

        KruskalSearch s = new KruskalSearch();
        Edge[] minSpinTree = s.getMinSpinTree(graph);
        System.out.println(minSpinTree);
    }
}
