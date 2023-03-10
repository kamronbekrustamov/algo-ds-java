package org.kamronbek.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutRepeatingCharacters {

    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0)
            return 0;
        Map<Character, Integer> charPositions = new HashMap<>();
        int maxLength = 0;
        int firstIndexOfSubstring = 0;
        for (int i = 0; i < s.length(); i++) {
            char character = s.charAt(i);
            int lastCharPos = charPositions.getOrDefault(character, i);
            charPositions.put(character, i);
            if (lastCharPos < i && lastCharPos >= firstIndexOfSubstring) {
                firstIndexOfSubstring = lastCharPos + 1;
            }
            maxLength = Integer.max(maxLength, i + 1 - firstIndexOfSubstring);
        }
        return maxLength;
    }
}
