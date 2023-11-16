package org.kamronbek.algo.hanoi;

import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.Stack;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HanoiTest {
    @Test
    void testHanoi() {
        var current = generate();
        var target = new Stack<Integer>();
        int expectedSize = current.size();
        new Hanoi().solve(current, target);
        assertEquals(expectedSize, target.size());
        verify(target, expectedSize - 1);
    }

    private Stack<Integer> generate() {
        var current = new Stack<Integer>();
        int rand = new Random().nextInt(1, 21);
        IntStream.range(0, rand).forEachOrdered(current::push);
        return current;
    }

    private void verify(Stack<Integer> stack, int largest) {
        while(!stack.isEmpty()) {
            assertEquals(stack.pop(), largest);
            largest--;
        }
    }


}
