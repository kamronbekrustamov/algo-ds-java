package org.kamronbek.leetcode.medium;

import java.util.Arrays;

public class LargestNumber {
    public String largestNumber(int[] nums) {
        String result = Arrays.stream(nums)
            .mapToObj(String::valueOf)
            .sorted((a, b) -> (b + a).compareTo(a + b))
            .reduce((a, b) -> a + b)
            .get();
        return result.startsWith("0") ? "0": result;
    }
}
