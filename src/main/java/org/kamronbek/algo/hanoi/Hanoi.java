package org.kamronbek.algo.hanoi;

import java.util.Stack;

public class Hanoi {
    public void solve(Stack<Integer> current, Stack<Integer> target) {
        if (current.isEmpty()) {
            throw new IllegalArgumentException("The current stack must have at least 1 element");
        }
        move(current, target, new Stack<>(), current.size());
    }

    private void move(Stack<Integer> current, Stack<Integer> target, Stack<Integer> temp, int countOfTowersToMove) {
        if (countOfTowersToMove == 1) {
            target.push(current.pop());
        } else {
            move(current, temp, target, countOfTowersToMove - 1);
            move(current, target, temp, 1);
            move(temp, target, current, countOfTowersToMove - 1);
        }
    }
}
