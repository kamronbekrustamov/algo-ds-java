package org.kamronbek.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

public class WordDictionary {
    private Node root;
    public WordDictionary() {
        root = new Node();
    }

    public void addWord(String word) {
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
        return search(word, root , 0);
    }

    private boolean search(String word, Node node, int charIndex) {
        if (word.length() == charIndex) {
            return node.isWord;
        }
        char c = word.charAt(charIndex);
        if (c == '.') {
            return node.characterMap.values().stream().anyMatch(it -> search(word, it, charIndex + 1));
        } else {
            return node.characterMap.containsKey(c)
                    && search(word, node.characterMap.get(c), charIndex + 1);
        }
    }

    private static class Node {
        public Map<Character, Node> characterMap = new HashMap<>(26, 1);
        public boolean isWord = false;
    }
}
