package org.kamronbek.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

public class GrayCode {
    public List<Integer> grayCode(int n) {
        int size = (int) Math.pow(2, n);
        List<Integer> grayCodes = new ArrayList<>(size);
        grayCodes.add(0);
        grayCodes.add(1);
        int count = 2;
        while(count < size) {
            for(int i = 1; i <= count; i++) {
                grayCodes.add(grayCodes.get(count - i) + count);
            }

            count = count * 2;
        }
        return grayCodes;
    }
}