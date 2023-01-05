package ru.tikskit.hw10avltree;

public class BstNode {
    private final int key;
    private BstNode parent;
    private BstNode left;
    private BstNode right;

    public void swap(BstNode node) {
        BstNode tempParent = parent;
        BstNode tempLeft  = left;
        BstNode tempRight = right;

        parent = node.parent;
        left = node.left;
        right = node.right;

        node.parent = tempParent;
        node.left = tempLeft;
        node.right = tempRight;
    }


    public BstNode getParent() {
        return parent;
    }

    public BstNode(int key, BstNode parent) {
        this.key = key;
        this.parent = parent;
    }

    public int getKey() {
        return key;
    }

    public BstNode getLeft() {
        return left;
    }

    public void setLeft(BstNode left) {
        this.left = left;
    }

    public BstNode getRight() {
        return right;
    }

    public void setRight(BstNode right) {
        this.right = right;
    }

    public boolean isLeftChildren() {
        return parent != null && parent.getLeft() == this;
    }

    public boolean isRightChildren() {
        return parent != null && parent.getRight() == this;
    }
}
