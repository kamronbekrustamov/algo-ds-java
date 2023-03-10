package org.kamronbek.leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LongestSubstringWithoutRepeatingCharactersTest {
    private final LongestSubstringWithoutRepeatingCharacters solution = new LongestSubstringWithoutRepeatingCharacters();

    @Test
    void test1() {
        String testString = "abcabcbb";
        int expected = 3;
        int actual = solution.lengthOfLongestSubstring(testString);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void test2() {
        String testString = "bbbbb";
        int expected = 1;
        int actual = solution.lengthOfLongestSubstring(testString);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void test3() {
        String testString = "pwwkew";
        int expected = 3;
        int actual = solution.lengthOfLongestSubstring(testString);
        Assertions.assertEquals(expected, actual);
    }
}
