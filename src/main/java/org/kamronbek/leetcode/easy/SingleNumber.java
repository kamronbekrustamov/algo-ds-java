package org.kamronbek.leetcode.easy;

public class SingleNumber {
    public int singleNumber(int[] nums) {
        int answer = 0;
        for (int num: nums)
            answer ^= num;
        return answer;
    }
}
