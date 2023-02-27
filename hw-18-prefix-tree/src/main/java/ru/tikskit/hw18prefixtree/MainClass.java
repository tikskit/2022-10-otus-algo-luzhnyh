package ru.tikskit.hw18prefixtree;

public class MainClass {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("test");
        System.out.println(trie.search("test1"));

    }
}
