package ru.tikskit.hw10avltree;


public class AvlNode {
    private final int key;

    private AvlNode left;
    private AvlNode right;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    private int level;


    public AvlNode(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public AvlNode getLeft() {
        return left;
    }

    public void setLeft(AvlNode left) {
        this.left = left;
    }

    public AvlNode getRight() {
        return right;
    }

    public void setRight(AvlNode right) {
        this.right = right;
    }

}
