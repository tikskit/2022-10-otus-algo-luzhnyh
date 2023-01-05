package ru.tikskit.hw10avltree;

/**
 * Двоичное дерево поиска
 */
public class BSTree implements TreeSearch{

    private BstNode root;

    public BSTree(int[] keys) {
        if (keys == null || keys.length == 0) {
            return;
        }

        for (int key : keys) {
            insert(key);
        }
    }

    public BstNode getRoot() {
        return root;
    }

    private void insertNode(int key, BstNode parent) {
        // Когда key == parent.getKey ничего не делаем
        if (key < parent.getKey()) {
            if (parent.getLeft() == null) {
                parent.setLeft(new BstNode(key, parent));
            } else {
                insertNode(key, parent.getLeft());
            }
        } else if (key > parent.getKey()) {
            if (parent.getRight() == null) {
                parent.setRight(new BstNode(key, parent));
            } else {
                insertNode(key, parent.getRight());
            }
        }
    }

    @Override
    public void insert(int key) {
        if (root == null) {
            root = new BstNode(key, null);
        } else {
            insertNode(key, root);
        }
    }

    private BstNode findNode(int key, BstNode node) {
        if (node == null) {
            return null;
        }
        if (node.getKey() == key) {
            return node;
        } else if (key < node.getKey()) {
            return findNode(key, node.getLeft());
        } else {
            return findNode(key, node.getRight());
        }
    }

    @Override
    public boolean search(int key) {
        if (root == null) {
            return false;
        } else {
            return findNode(key, root) != null;
        }
    }

    private void removeNodeWithNoChild(BstNode node) {
        if (node.getParent() == null) {
            root = null;
        } else if (node.isLeftChildren()) {
            node.getParent().setLeft(null);
        } else if (node.isRightChildren()) {
            node.getParent().setRight(null);
        }
    }

    private void removeNodeWithOneChild(BstNode node) {
        if (node.getParent() == null) {
            root = null;
        } else {
            BstNode theOnlyChild = node.getLeft() != null ? node.getLeft() : node.getRight();
            if (node.isLeftChildren()) {
                node.getParent().setLeft(theOnlyChild);
            } else {
                node.getParent().setRight(theOnlyChild);
            }
        }
    }

    private BstNode getMostRight(BstNode node) {
        if (node.getRight() == null) {
            return node;
        } else {
            return getMostRight(node.getRight());
        }
    }
    private BstNode findMaxOnLeft(BstNode node) {
        BstNode leftChildren = node.getLeft();
        return getMostRight(leftChildren);
    }

    private void removeNodeWithBothChildren(BstNode node) {
        BstNode maxOnLeft = findMaxOnLeft(node);
        node.swap(maxOnLeft);
        removeNode(node); // удаляем либо с 1 дочерним, либо с 0 дочерними элементами
    }

    private void removeNode(BstNode node) {
        if (node.getLeft() != null && node.getRight() != null) {
            removeNodeWithBothChildren(node);
        } else if (node.getLeft() != null || node.getRight() != null) {
            removeNodeWithOneChild(node);
        } else {
            removeNodeWithNoChild(node);
        }
    }

    @Override
    public void remove(int key) {
        BstNode node = findNode(key, root);
        if (node == null) {
            return;
        }

        removeNode(node);
    }

}
