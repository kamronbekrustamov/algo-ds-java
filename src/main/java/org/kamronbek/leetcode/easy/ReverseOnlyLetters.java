package org.kamronbek.leetcode.easy;

public class ReverseOnlyLetters {
    public String reverseOnlyLetters(String S) {
        int leftIndex = 0;
        int rightIndex = S.length() - 1;
        char[] chars = S.toCharArray();
        while (rightIndex > leftIndex) {
            if (!Character.isLetter(chars[leftIndex]))
                leftIndex++;
            if (!Character.isLetter(chars[rightIndex]))
                rightIndex--;
            if (Character.isLetter(chars[leftIndex]) && Character.isLetter(chars[rightIndex])) {
                char c = chars[leftIndex];
                chars[leftIndex] = chars[rightIndex];
                chars[rightIndex] = c;
                leftIndex++;
                rightIndex--;
            }
        }
        return String.copyValueOf(chars);
    }
}
