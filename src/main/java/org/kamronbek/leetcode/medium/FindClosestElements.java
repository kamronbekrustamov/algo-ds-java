package org.kamronbek.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

public class FindClosestElements {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int leftIndex = 0;
        int rightIndex = arr.length - 1;
        if (x <= arr[leftIndex]) {
            return getListBetweenIndices(arr,0, k - 1);
        }
        if (x >= arr[rightIndex]) {
            return getListBetweenIndices(arr, rightIndex - k + 1, rightIndex);
        }
        while(rightIndex - leftIndex >= k) {
            if (Math.abs(arr[rightIndex] - x) >= Math.abs(arr[leftIndex] - x)) {
                rightIndex--;
            } else {
                leftIndex++;
            }
        }
        return getListBetweenIndices(arr, leftIndex, rightIndex);
    }

    public List<Integer> getListBetweenIndices(int[] arr, int firstIndex, int secondIndex) {
        List<Integer> list = new ArrayList<>(secondIndex - firstIndex + 1);
        for (int i = firstIndex; i <= secondIndex; i++) {
            list.add(arr[i]);
        }
        return list;
    }
}
