package ru.tikskit.hw20minpath;

public class MainClass {

    public static char numToLetter(int num) {
        char a = 'A';
        return (char) (a + num);
    }

    public static void main(String[] args) {
        /*
         * A 0
         * B 1
         * C 2
         * D 3
         * E 4
         * F 5
         * G 6
         * H 7
         * I 8
         */

        int[][] graph = {
                //      A, B, C, D, E, F, G
                /*A*/ { 0, 2, 3, 6, 0, 0, 0},
                /*B*/ { 2, 0, 4, 0, 9, 0, 0},
                /*C*/ { 3, 4, 0, 1, 7, 6, 0},
                /*D*/ { 6, 0, 1, 0, 0, 4, 0},
                /*E*/ { 0, 9, 7, 0, 0, 1, 5},
                /*F*/ { 0, 0, 6, 4, 1, 0, 8},
                /*G*/ { 0, 0, 0, 0, 5, 8, 0},
        };

        DejkstraSearch s = new DejkstraSearch();
        int[] ints = s.find(graph, 6, 1);
        for (int v : ints) {
            System.out.println(numToLetter(v));
        }


    }
}
