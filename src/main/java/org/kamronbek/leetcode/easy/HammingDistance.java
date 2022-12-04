package org.kamronbek.leetcode.easy;

public class HammingDistance {
    public int hammingDistance(int x, int y) {
        int dif = 0;

        int xor = x ^ y;
        while (xor != 0) {
            if (xor % 2 == 1)
                dif++;
            xor = xor >> 1;
        }
        return dif;
    }
}
