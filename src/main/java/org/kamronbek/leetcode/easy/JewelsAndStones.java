package org.kamronbek.leetcode.easy;

import java.util.HashSet;
import java.util.Set;

public class JewelsAndStones {
    public int numJewelsInStones(String jewels, String stones) {
        Set<Character> jewelsSet = new HashSet<>();
        int count = 0;

        for (int i = 0; i < jewels.length(); i++) {
            jewelsSet.add(jewels.charAt(i));
        }

        for (int i = 0; i < stones.length(); i++) {
            if (jewelsSet.contains(stones.charAt(i)))
                count++;
        }
        return count;
    }
}
