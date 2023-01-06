package ru.tikskit.hw10avltree;

import java.sql.Time;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {
    private static final int DATA_SIZE = 10;

    public static void main(String[] args) {
        avlTreePopulate();
/*        return;
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
        BstTreeChecker.check(incDataTree.getRoot());*/

    }

    private static void avlTreePopulate() {
        DataProvider randomDataProvider = new RandomDataProvider();
        DataProvider incrementalDataProvider = new IncrementalDataProvider();
        Timer timer = new Timer();

        int[] randomData = randomDataProvider.getData(DATA_SIZE);
        timer.start();

        AvlTree randomDataTree = null;
        try {
            randomDataTree = new AvlTree(new int[]{21,2,42,50,60,11,31,75,65,46});
            System.out.println("Построение АВЛ дерева случайными данными: " + timer.stop());
            AvlTreeChecker.check(randomDataTree.getRoot());
        } catch (Exception e) {
            printData(randomData);
            throw e;
        }


        int[] incData = incrementalDataProvider.getData(DATA_SIZE);
        timer.start();
        AvlTree incDataTree = new AvlTree(incData);
        System.out.println("Построение АВЛ дерева возрастающими данными: " + timer.stop());

        AvlTreeChecker.check(incDataTree.getRoot());

    }

    private static void printData(int[] randomData) {
        String value = Arrays.stream(randomData)
                .boxed()
                .map(t -> Integer.toString(t))
                .collect(Collectors.joining(","));
        System.out.println(value);
    }
}
