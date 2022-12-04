package org.kamronbek.leetcode.easy;

import java.util.LinkedList;
import java.util.List;

public class BaseballGame {
    public int calPoints(String[] ops) {
        int points = 0;
        List<Integer> pointsList = new LinkedList<>();
        int size;
        for (String op: ops) {
            size = pointsList.size();
            try {
                int p = Integer.parseInt(op);
                pointsList.add(p);
            } catch (NumberFormatException e) {
                switch (op) {
                    case "+" -> pointsList.add(pointsList.get(size - 1) + pointsList.get(size - 2));
                    case "C" -> pointsList.remove(size - 1);
                    case "D" -> pointsList.add(2 * pointsList.get(size - 1));
                }
            }
        }
        for (int point: pointsList)
            points += point;
        return points;
    }
}
