package org.kamronbek.leetcode.easy;

public class SmallestLetterGreaterThanTarget {
    public char nextGreatestLetter(char[] letters, char target) {
        if (letters[letters.length - 1] <= target)
            return letters[0];
        else
            return findChar(letters, target);
    }

    public char findChar(char[] letters, char target) {
        int left = 0, right = letters.length - 1;
        int middle = (left + right) / 2;
        while(right != left) {
            if (letters[middle] <= target)
                left = middle + 1;
            else {
                right = middle;
            }
            middle = (right - left) / 2 + left;
        }
        return letters[right];
    }
}