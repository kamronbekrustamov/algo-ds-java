package org.kamronbek.leetcode.easy;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MostCommonWord {
    public String mostCommonWord(String paragraph, String[] banned) {
        Map<String, Integer> map = new HashMap<>();
        char[] chars = paragraph.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if ('A' <= c && c <= 'Z')
                chars[i] = Character.toLowerCase(c);
            else if (c == '!' || c == '?' || c == '\'' || c == ',' || c == ';' || c == '.')
                chars[i] = ' ';
        }
        String[] words = String.copyValueOf(chars).split(" ");

        for (String word: words) {
            if (!word.isEmpty())
                map.put(word, map.getOrDefault(word, 0) + 1);
        }

        for (String bWord: banned) {
            map.remove(bWord);
        }
        int most = Collections.max(map.values());
        String common = "";
        for (String key: map.keySet()) {
            if (map.get(key) == most)
                common = key;
        }

        return common;
    }

}
