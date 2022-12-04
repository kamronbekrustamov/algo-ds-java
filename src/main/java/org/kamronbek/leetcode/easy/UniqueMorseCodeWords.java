package org.kamronbek.leetcode.easy;

import java.util.HashSet;
import java.util.Set;

public class UniqueMorseCodeWords {
    String[] morse = {
            ".-", "-...", "-.-.", "-..", ".", "..-.", "--.",
            "....", "..", ".---", "-.-", ".-..", "--", "-.",
            "---", ".--.", "--.-", ".-.", "...", "-", "..-",
            "...-", ".--", "-..-", "-.--", "--.."};

    public int uniqueMorseRepresentations(String[] words) {
        Set<String> set = new HashSet<>();
        StringBuilder temp;
        for (String word: words) {
            temp = new StringBuilder();
            for(int i = 0; i < word.length(); i++) {
                temp.append(morse[(int) word.charAt(i) - 97]);
            }
            set.add(temp.toString());
        }
        return set.size();
    }

}