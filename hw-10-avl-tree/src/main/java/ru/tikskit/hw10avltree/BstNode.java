package ru.tikskit.hw10avltree;

public class BstNode {
    private final int key;
    private BstNode parent;
    private BstNode left;
    private BstNode right;

    public void swap(BstNode node) {

        BstNode tempLeft  = left;
        BstNode tempRight = right;
        BstNode nodeInitParent = node.parent;

        left = node.left;
        right = node.right;

        node.left = tempLeft;
        node.right = tempRight;

        if (parent == node) {
            node.parent = this;
            parent = nodeInitParent;
            return;
        } else {
            node.parent = nodeInitParent;
        }

        if (node.parent == this) {
            BstNode thisInitParent = parent;
            parent = node;
            node.parent = thisInitParent;
        } else {
            parent = node.parent;
        }
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
