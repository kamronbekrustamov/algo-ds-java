package org.kamronbek.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

public class ShortestDistanceToCharacter {

    public int[] shortestToChar(String S, char C) {
        List<Integer> positions = new ArrayList<>();
        int[] distances = new int[S.length()];
        int index = 0;

        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == C)
                positions.add(i);
        }

        int pos1 = positions.get(index);
        int pos2 = pos1;
        for (int i = 0; i < distances.length; i++) {
            distances[i] = Math.min(Math.abs(pos1 - i), Math.abs(pos2 - i));
            if (pos2 == i) {
                index++;
                if (index < positions.size()) {
                    pos1 = pos2;
                    pos2 = positions.get(index);
                }
            }
        }

        return distances;

    }
}
