package ru.tikskit.hw18prefixtree;

public class Trie {

    private static class Node {
        private Node[] child = new Node['A'];
        private boolean isEnd = false;

        public boolean exists(char c) {
            return child[c] != null;
        }

        public Node next(char c) {
            if (!exists(c)) {
                child[c] = new Node();
            }

            return child[c];
        }

        public boolean isEnd() {
            return isEnd;
        }

        public void setEnd(boolean end) {
            isEnd = end;
        }
    }
    private Node root = new Node();

    public void insert(String word) {
        char[] chars = word.toCharArray();
        Node node = root;
        for (char c : chars) {
            node = node.next(c);
        }
        node.setEnd(true);
    }

    private Node go(String word) {
        char[] chars = word.toCharArray();
        Node node = root;
        for (char c : chars) {
            if (node.exists(c)) {
                node = node.next(c);
            } else {
                return null;
            }
        }
        return node;
    }

    public boolean search(String word) {
        Node node = go(word);
        return node != null && node.isEnd();
    }

    public boolean startsWith(String prefix) {
        return go(prefix) != null;
    }
}
