package org.kamronbek.leetcode.medium;

import java.util.HashSet;
import java.util.List;

public class WordBreak {
    HashSet<String> wordsSet;
    public boolean wordBreak(String s, List<String> wordDict) {
        wordsSet = new HashSet<>(wordDict);
        return contains(s);
    }

    public boolean contains(String str) {
        int size = str.length();
        if (size == 0)
            return true;
        for (int i = 1; i <= size; i++) {
            if (wordsSet.contains(str.substring(0, i)) && contains(str.substring(i, size)))
                return true;
        }
        return false;
    }
}
