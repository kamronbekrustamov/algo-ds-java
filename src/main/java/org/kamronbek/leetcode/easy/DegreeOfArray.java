package org.kamronbek.leetcode.easy;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DegreeOfArray {

    public int findShortestSubArray(int[] nums) {
        Map<Integer, Integer> frequencies = new HashMap<>();
        Map<Integer, Integer> right = new HashMap<>();
        Map<Integer, Integer> left = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int val = nums[i];

            if (!left.containsKey(val))
                left.put(val, i);
            right.put(val, i);

            frequencies.put(val, frequencies.getOrDefault(val, 0) + 1);
        }
        int degree = Collections.max(frequencies.values());
        int len = nums.length;
        for (int key: frequencies.keySet()) {
            if (frequencies.get(key) == degree)
                len = Math.min(len, right.get(key) - left.get(key) + 1);
        }

        return len;
    }

}
