package org.kamronbek.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

public class PositionsOfLargeGroups {
    public List<List<Integer>> largeGroupPositions(String s) {
        List<List<Integer>> positions = new ArrayList<>();

        char c = s.charAt(0);
        int startIndex = 0;

        for (int i = 1; i < s.length(); i++) {
            if (c != s.charAt(i)) {
                if (i - startIndex >= 3) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(startIndex);
                    temp.add(i - 1);
                    positions.add(temp);
                }
                startIndex = i;
                c = s.charAt(i);
            }
        }
        if (s.length() - startIndex >= 3) {
            List<Integer> temp = new ArrayList<>();
            temp.add(startIndex);
            temp.add(s.length() - 1);
            positions.add(temp);
        }

        return positions;
    }
}
