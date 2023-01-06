package ru.tikskit.hw10avltree;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString(onlyExplicitlyIncluded = true)
@Getter
@Setter
public class AvlNode {
    @ToString.Include
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

    public int getLeftHeight() {
        return left != null ? left.getHeight() : 0;
    }

    public int getRightHeight() {
        return right != null ? right.getHeight() : 0;
    }

    public boolean isBalanced() {
        return Math.abs(getLeftHeight() - getRightHeight()) <= 1;
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
