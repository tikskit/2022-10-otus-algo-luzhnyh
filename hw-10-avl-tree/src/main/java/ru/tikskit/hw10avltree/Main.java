package ru.tikskit.hw10avltree;

public class Main {
    private static final int DATA_SIZE = 24_000;

    public static void main(String[] args) {
//        populateTest();
//        searchTest();
        removeTest();
    }

    private static void populateTest() {
        DataProvider randomDataProvider = new RandomDataProvider();
        Timer timer = new Timer();

        // случайные данные
        int[] randomData = randomDataProvider.getData(DATA_SIZE);

        timer.start();
        BSTree randomDataBSTree = new BSTree(randomData);
        System.out.println("Построение BST дерева случайными данными: " + timer.stop());
        BstTreeChecker.check(randomDataBSTree.getRoot());


        timer.start();
        AvlTree randomDataAvlTree = new AvlTree(randomData);
        System.out.println("Построение АВЛ дерева случайными данными: " + timer.stop());
        AvlTreeChecker.check(randomDataAvlTree.getRoot());

        randomDataBSTree = null;
        randomDataAvlTree = null;
        randomData = null;
        System.gc();

        // Упорядочные данные
        DataProvider ascDataProvider = new AscDataProvider();
        int[] ascData = ascDataProvider.getData(DATA_SIZE);
/*        timer.start();
        BSTree incDataBSTree = new BSTree(ascData);
        System.out.println("Построение BST дерева возрастающими данными: " + timer.stop());
        BstTreeChecker.check(incDataBSTree.getRoot());*/

        timer.start();
        AvlTree incDataAvlTree = new AvlTree(ascData);
        System.out.println("Построение АВЛ дерева возрастающими данными: " + timer.stop());
        AvlTreeChecker.check(incDataAvlTree.getRoot());
    }

    public static void searchTest() {
        DataProvider randomDataProvider = new RandomDataProvider();
        DataProvider ascDataProvider = new AscDataProvider();
        NumbersToSearchProvider numbersToSearchProvider = new NumbersToSearchProvider();
        Timer timer = new Timer();

        // случайные данные
        int[] randomData = randomDataProvider.getData(DATA_SIZE);
        int[] randomDataNumbers = numbersToSearchProvider.getNumbers(randomData);

        BSTree randomDataBSTree = new BSTree(randomData);
        timer.start();
        for (int n : randomDataNumbers) {
            randomDataBSTree.search(n);
        }
        System.out.println("Поиск по BST дереву со случайными данными: " + timer.stop());

        AvlTree randomDataAvlTree = new AvlTree(randomData);
        timer.start();
        for (int n : randomDataNumbers) {
            randomDataAvlTree.search(n);
        }
        System.out.println("Поиск по АВЛ дереву со случайными данными: " + timer.stop());

        randomDataBSTree = null;
        randomDataAvlTree = null;
        randomData = null;
        System.gc();

        // Упорядочные данные

        int[] ascData = ascDataProvider.getData(DATA_SIZE);
        int[] ascDataNumbers = numbersToSearchProvider.getNumbers(ascData);

        BSTree ascDataBSTree = new BSTree(ascData);
        timer.start();
        for (int n : ascDataNumbers) {
            ascDataBSTree.search(n);
        }
        System.out.println("Поиск по BST дереву с возрастающими данными: " + timer.stop());

        AvlTree ascDataAvlTree = new AvlTree(ascData);
        timer.start();
        for (int n : ascDataNumbers) {
            ascDataAvlTree.search(n);
        }
        System.out.println("Поиск по АВЛ дереву с возрастающими данными: " + timer.stop());
    }

    public static void removeTest() {
        DataProvider randomDataProvider = new RandomDataProvider();
        DataProvider ascDataProvider = new AscDataProvider();
        NumbersToSearchProvider numbersToSearchProvider = new NumbersToSearchProvider();
        Timer timer = new Timer();

        // случайные данные
        int[] randomData = randomDataProvider.getData(DATA_SIZE);
        int[] randomDataNumbers = numbersToSearchProvider.getNumbers(randomData);

        BSTree randomDataBSTree = new BSTree(randomData);
        timer.start();
        for (int n : randomDataNumbers) {
            randomDataBSTree.remove(n);
        }
        System.out.println("Удаление из BST дереву со случайными данными: " + timer.stop());

        AvlTree randomDataAvlTree = new AvlTree(randomData);
        timer.start();
        for (int n : randomDataNumbers) {
            randomDataAvlTree.remove(n);
        }
        System.out.println("Удаление из АВЛ дереву со случайными данными: " + timer.stop());
        AvlTreeChecker.check(randomDataAvlTree.getRoot());

        randomDataBSTree = null;
        randomDataAvlTree = null;
        randomData = null;
        System.gc();

        // Упорядочные данные

        int[] ascData = ascDataProvider.getData(DATA_SIZE);
        int[] ascDataNumbers = numbersToSearchProvider.getNumbers(ascData);

        BSTree ascDataBSTree = new BSTree(ascData);
        timer.start();
        for (int n : ascDataNumbers) {
            ascDataBSTree.remove(n);
        }
        System.out.println("Удаление из BST дереву с возрастающими данными: " + timer.stop());

        AvlTree ascDataAvlTree = new AvlTree(ascData);
        timer.start();
        for (int n : ascDataNumbers) {
            ascDataAvlTree.remove(n);
        }
        System.out.println("Удаление из АВЛ дереву с возрастающими данными: " + timer.stop());
        AvlTreeChecker.check(ascDataAvlTree.getRoot());
    }

}
