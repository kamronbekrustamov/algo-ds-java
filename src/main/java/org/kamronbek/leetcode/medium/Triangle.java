package org.kamronbek.leetcode.medium;

import java.util.List;

public class Triangle {

    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle.size() == 0)
            return 0;

        for (int i = triangle.size() - 2; i >= 0; i--) {
            List<Integer> rowAfter = triangle.get(i + 1);
            for (int j = 0; j <= i; j++) {
                int sum = triangle.get(i).get(j) + Math.min(rowAfter.get(j), rowAfter.get(j + 1));
                triangle.get(i).set(j, sum);
            }
        }
        return triangle.get(0).get(0);
    }
}
