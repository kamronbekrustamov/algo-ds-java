package org.kamronbek.algo.hanoi;

import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.stream.IntStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HanoiTest {
    @Test
    void testHanoi() {
        var current = generate();
        Deque<Integer> target = new LinkedList<>();
        int expectedSize = current.size();
        new Hanoi().solve(current, target);
        assertEquals(expectedSize, target.size());
        verify(target, expectedSize - 1);
    }

    private Deque<Integer> generate() {
        Deque<Integer> current = new ArrayDeque<>();
        int rand = new Random().nextInt(1, 21);
        IntStream.range(0, rand).forEachOrdered(current::push);
        return current;
    }

    private void verify(Deque<Integer> stack, int largest) {
        while(!stack.isEmpty()) {
            assertEquals(stack.pop(), largest);
            largest--;
        }
    }
}
