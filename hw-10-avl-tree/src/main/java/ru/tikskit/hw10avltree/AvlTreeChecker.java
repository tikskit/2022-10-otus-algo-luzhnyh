package ru.tikskit.hw10avltree;

public class AvlTreeChecker {
    public static void check(AvlNode node) {
        if (node == null) {
            return;
        }
        if (node.getLeft() != null) {
            if (node.getLeft().getKey() >= node.getKey()) {
                throw new IllegalStateException("Значение элемента слева не меньше, чем значение узла");
            }
            check(node.getLeft());
        }

        if (node.getRight() != null) {
            if (node.getRight().getKey() <= node.getKey()) {
                throw new IllegalStateException("Значение элемента справа не больше, чем значение узла");
            }
            check(node.getRight());
        }

        if (!node.isBalanced()) {
            throw new IllegalStateException("Узел дерева не сбалансирован");
        }
    }
}
