package ru.tikskit.hw10avltree;


public class AvlNode {
    private final int key;
    private AvlNode left;
    private AvlNode right;

    public int getHeight() {
        return 1 + Math.max(
                left != null ? left.getHeight() : 0,
                right != null ? right.getHeight() : 0
        );
    }

    public boolean isBalanced() {
        int leftH = left != null ? left.getHeight() + 1 : 0;
        int rightH = right != null ? right.getHeight() + 1 : 0;
        return Math.abs(leftH - rightH) <= 1;
    }


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
