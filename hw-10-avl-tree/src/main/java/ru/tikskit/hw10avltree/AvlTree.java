package ru.tikskit.hw10avltree;

public class AvlTree implements TreeSearch {

    private AvlNode root;

    public AvlTree(int[] keys) {
        if (keys == null || keys.length == 0) {
            return;
        }

        for (int key : keys) {
            insert(key);
        }
    }

    public AvlNode getRoot() {
        return root;
    }

    private void smallRightRotation(AvlNode node) {
        AvlNode parent = node.getParent(); // родитель есть всегда (из контекста вызовов)

        AvlNode nodeRight = node.getRight();
        if (nodeRight != null) {
            nodeRight.setParent(parent);
        }
        parent.setLeft(nodeRight);
        node.setRight(parent);
        node.setParent(parent.getParent());
        if (parent.isLeftChild()) {
            parent.getParent().setLeft(node);
        } else if (parent.isRightChild()) {
            parent.getParent().setRight(node);
        }
        parent.setParent(node);
    }

    private void smallLeftRotation(AvlNode node) {
        AvlNode parent = node.getParent(); // родитель есть всегда (из контекста вызовов)

        AvlNode nodeLeft = node.getLeft();
        if (nodeLeft != null) {
            nodeLeft.setParent(parent);
        }
        parent.setRight(nodeLeft);
        node.setLeft(parent);
        node.setParent(parent.getParent());
        if (parent.isLeftChild()) {
            parent.getParent().setLeft(node);
        } else if (parent.isRightChild()) {
            parent.getParent().setRight(node);
        }
        parent.setParent(node);
    }

    /**
     * Сбалансировать узел дерева, если он разбалансирован
     * @param node Балансируемый узел
     * @return узел, который который занимает место node в результате балансировки
     */
    private AvlNode balanceByRotations(AvlNode node) {
        if (node.isBalanced()) {
            return node; // Узел сбалансирован
        }

        int leftHeight = node.getLeftHeight();
        int rightHeight = node.getRightHeight();


        AvlNode newTopNode;
        if (leftHeight > rightHeight) {
            // делаем правые повороты
            // Если leftHeight > rightHeight, значит у Node точно есть левый элемент
            AvlNode leftNode = node.getLeft();
            if (leftNode.getLeftHeight() >= leftNode.getRightHeight()) {
                // Можно выполнить малый поворот вправо
                smallRightRotation(leftNode);
                newTopNode = leftNode;
            } else {
                // Если высота справа больше, чем высота слева, значит элемент справа точно есть
                AvlNode movingNode = leftNode.getRight();
                smallLeftRotation(movingNode);
                smallRightRotation(movingNode);
                newTopNode = movingNode;
            }
        } else {
            // делаем левые повороты
            // Если leftHeight < rightHeight, значит у Node точно есть левый элемент
            AvlNode rightNode = node.getRight();
            if (rightNode.getRightHeight() >= rightNode.getLeftHeight()) {
                // Можно выполнить малый поворот влево
                smallLeftRotation(rightNode);
                newTopNode = rightNode;
            } else {
                // Если высота слева больше, чем высота справа, значит элемент слева точно есть
                AvlNode movingNode = rightNode.getLeft();
                smallRightRotation(movingNode);
                smallLeftRotation(movingNode);
                newTopNode = movingNode;
            }
        }

        return newTopNode;
    }

    private void balanceNode(AvlNode node) {
        if (node == null) {
            return;
        }
        AvlNode newTopNode = balanceByRotations(node);
        if (root == node) {
            root = newTopNode;
        } else {
            balanceNode(newTopNode.getParent());
        }
    }

    private AvlNode insertNode(int key, AvlNode parent) {
        // Когда key == parent.getKey ничего не делаем
        if (key < parent.getKey()) {
            if (parent.getLeft() == null) {
                AvlNode newNode = new AvlNode(key, parent);
                parent.setLeft(newNode);
                return newNode;
            } else {
                return insertNode(key, parent.getLeft());
            }
        } else if (key > parent.getKey()) {
            if (parent.getRight() == null) {
                AvlNode newNode = new AvlNode(key, parent);
                parent.setRight(newNode);
                return newNode;
            } else {
                return insertNode(key, parent.getRight());
            }
        } else {
            return parent;
        }
    }

    @Override
    public void insert(int key) {
        if (root == null) {
            root = new AvlNode(key, null);
        } else {
            AvlNode node = insertNode(key, root);
            balanceFrom(node);
        }
    }

    private void balanceFrom(AvlNode node) {
        if (node != null) {
            if (!node.isBalanced()) {
                balanceNode(node);
            }
            balanceFrom(node.getParent());
        }
    }

    private AvlNode findNode(int key, AvlNode node) {
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
    private void removeNodeWithNoChild(AvlNode node) {
        if (node.getParent() == null) {
            root = null;
        } else if (node.isLeftChild()) {
            node.getParent().setLeft(null);
        } else if (node.isRightChild()) {
            node.getParent().setRight(null);
        }
    }

    private void removeNodeWithOneChild(AvlNode node) {
        if (node.getParent() == null) {
            root = null;
        } else {
            AvlNode theOnlyChild = node.getLeft() != null ? node.getLeft() : node.getRight();
            if (node.isLeftChild()) {
                node.getParent().setLeft(theOnlyChild);
            } else {
                node.getParent().setRight(theOnlyChild);
            }
        }
    }

    private AvlNode getMostRight(AvlNode node) {
        if (node.getRight() == null) {
            return node;
        } else {
            return getMostRight(node.getRight());
        }
    }
    private AvlNode findMaxOnLeft(AvlNode node) {
        AvlNode leftChildren = node.getLeft();
        return getMostRight(leftChildren);
    }

    private void removeNodeWithBothChildren(AvlNode node) {
        AvlNode maxOnLeft = findMaxOnLeft(node);
        node.swap(maxOnLeft);
        removeNode(node); // удаляем либо с 1 дочерним, либо с 0 дочерними элементами
    }

    private void removeNode(AvlNode node) {
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
        AvlNode node = findNode(key, root);
        if (node == null) {
            return;
        }

        removeNode(node);
        if (node.getParent() != null) {
            balanceNode(node.getParent());
        }
    }
}
