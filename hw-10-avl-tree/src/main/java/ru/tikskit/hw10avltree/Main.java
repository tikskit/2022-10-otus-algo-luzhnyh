package ru.tikskit.hw10avltree;

import java.sql.Time;

public class Main {
    private static final int DATA_SIZE = 100_000;

    public static void main(String[] args) {
        DataProvider randomDataProvider = new RandomDataProvider();
        DataProvider incrementalDataProvider = new IncrementalDataProvider();
        Timer timer = new Timer();
        NumbersToSearchProvider numbersToSearchProvider = new NumbersToSearchProvider();

        int[] randomData = randomDataProvider.getData(DATA_SIZE);
        BSTree randomDataTree = new BSTree(randomData);
        int[] randomDataNumbers = numbersToSearchProvider.getNumbers(randomData);
        timer.start();
        for (int n : randomDataNumbers) {
//            randomDataTree.search(n);
            randomDataTree.remove(n);
        }
        System.out.println("Поиск по дереву со случайными данными: " + timer.stop());
        BstTreeChecker.check(randomDataTree.getRoot());

        int[] incData = incrementalDataProvider.getData(DATA_SIZE);
        BSTree incDataTree = new BSTree(incData);
        int[] incDataNumbers = numbersToSearchProvider.getNumbers(incData);
        timer.start();
        for (int n : incDataNumbers) {
//            incDataTree.search(n);
            incDataTree.remove(n);
        }
        System.out.println("Поиск по дереву с возрастающими данными: " + timer.stop());
        BstTreeChecker.check(incDataTree.getRoot());

//        System.out.println(randomDataTree);
    }
}
