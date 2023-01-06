package ru.tikskit.hw10avltree;


public class AvlNode {
    private final int key;
    private AvlNode left;
    private AvlNode right;
    private AvlNode parent;

    public AvlNode(int key, AvlNode parent) {
        this.key = key;
        this.parent = parent;
    }

    public int getHeight() {
        return 1 + Math.max(
                left != null ? left.getHeight() : 0,
                right != null ? right.getHeight() : 0
        );
    }

    public boolean isBalanced() {
        int leftH = left != null ? left.getHeight() : 0;
        int rightH = right != null ? right.getHeight() : 0;
        return Math.abs(leftH - rightH) <= 1;
    }

    public void swap(AvlNode node) {

        AvlNode tempLeft  = left;
        AvlNode tempRight = right;
        AvlNode nodeInitParent = node.parent;

        left = node.left;
        right = node.right;

        node.left = tempLeft;
        node.right = tempRight;

        if (parent == node) {
            node.parent = this;
            parent = nodeInitParent;
            return;
        }

        if (node.parent == this) {
            AvlNode thisInitParent = parent;
            parent = node;
            node.parent = thisInitParent;
        } else {
            parent = node.parent;
        }
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

    public AvlNode getParent() {
        return parent;
    }

    public void setParent(AvlNode parent) {
        this.parent = parent;
    }


    /**
     * Является ли узел левым дочерним для родителя
     * @return True если узел является левым дочерним для родителя, иначе false. False также возвращается, если родителя
     * нет
     */
    public boolean isLeftChild() {
        return parent != null && parent.getLeft() == this;
    }
    /**
     * Является ли узел правым дочерним для родителя
     * @return True если узел является правым дочерним для родителя, иначе false. False также возвращается, если родителя
     * нет
     */
    public boolean isRightChild() {
        return parent != null && parent.getRight() == this;
    }
}
