package org.kamronbek.leetcode.easy;

public class Pangram {
    public static boolean isPangram(String text) {
        boolean[] checks = new boolean[26];
        text = text.toUpperCase();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if ('A' <= c && c <= 'Z')
                checks[(int)c - 65] = true;
        }

        for (boolean b: checks)
            if (!b)
                return false;
        return true;
    }
}
