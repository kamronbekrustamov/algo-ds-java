package org.kamronbek.leetcode.easy;

public class PalindromeNumber {
    public boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0))
            return false;
        
        int reverted = 0;
        while (reverted < x) {
            reverted = reverted * 10 + x % 10;
            x /= 10;
        }

        return x == reverted || x == reverted / 10;
    }
}
