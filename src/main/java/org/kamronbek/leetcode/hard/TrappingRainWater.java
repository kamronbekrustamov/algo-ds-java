package org.kamronbek.leetcode.hard;

public class TrappingRainWater {
    public int trap(int[] height) {
        int length = height.length;
        int capacity = 0;
        int max = height[0];
        int sum = 0;
        for (int val: height) {
            sum += val;
            if (val > max)
                max = val;
        }
        capacity = max * length;
        capacity -= sum;
        int leftIndex1 = 0, leftIndex2 = 0, rightIndex1 = length - 1, rightIndex2 = length - 1;
        while(true) {
            
            if (height[leftIndex2] > height[leftIndex1]) {
                capacity = capacity - ((leftIndex2 - leftIndex1) * (max - height[leftIndex1]));
                leftIndex1 = leftIndex2;
            }
            
            if (height[rightIndex2] > height[rightIndex1]) {
                capacity = capacity - ((rightIndex1 - rightIndex2) * (max - height[rightIndex1]));
                rightIndex1 = rightIndex2;
            }
            
            if (height[leftIndex1] == max && height[rightIndex1] == max)
                break;
            
            leftIndex2++;
            rightIndex2--;
        }
        return capacity;
    }
}