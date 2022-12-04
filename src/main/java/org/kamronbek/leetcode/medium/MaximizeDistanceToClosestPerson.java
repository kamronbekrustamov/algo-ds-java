package org.kamronbek.leetcode.medium;

public class MaximizeDistanceToClosestPerson {

    public int maxDistToClosest(int[] seats) {
        int max = 0;
        int index = -1;
        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == 1) {
                if (index == -1)
                    max = i;
                else
                    max = Math.max(max, (i - index) / 2);
                index = i;
            }
        }
        max = Math.max(max, seats.length - 1 - index);
        return max;
    }
}
