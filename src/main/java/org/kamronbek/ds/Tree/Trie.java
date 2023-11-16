package org.kamronbek.ds.Tree;

import java.util.HashMap;
import java.util.Map;

public class Trie {
    private Node root;
    public Trie() {
        root = new Node();
    }

    public void insert(String word) {
        if (word.isBlank()) {
            throw new IllegalArgumentException("It is blank");
        }
        Node currentNode = this.root;
        for (char c : word.toCharArray()) {
            if (!currentNode.characterMap.containsKey(c)) {
                currentNode.characterMap.put(c, new Node());
            }
            currentNode = currentNode.characterMap.get(c);
        }
        currentNode.isWord = true;
    }

    public boolean search(String word) {
        if (word.isBlank()) {
            throw new IllegalArgumentException("It is blank");
        }
        Node currentNode = this.root;
        for (char c : word.toCharArray()) {
            if (!currentNode.characterMap.containsKey(c)) {
                return false;
            }
            currentNode = currentNode.characterMap.get(c);
        }
        return currentNode.isWord;
    }

    public boolean startsWith(String prefix) {
        if (prefix.isBlank()) {
            throw new IllegalArgumentException("It is blank");
        }
        Node currentNode = this.root;
        for (char c : prefix.toCharArray()) {
            if (!currentNode.characterMap.containsKey(c)) {
                return false;
            }
            currentNode = currentNode.characterMap.get(c);
        }
        return true;
    }

    private static class Node {
        public Map<Character, Node> characterMap = new HashMap<>(26, 1);
        public boolean isWord = false;
    }
}
