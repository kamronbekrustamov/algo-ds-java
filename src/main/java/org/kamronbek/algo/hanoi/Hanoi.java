package org.kamronbek.algo.hanoi;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Implements the recursive solution for the **Tower of Hanoi** puzzle,
 * using the Deque interface (implemented by ArrayDeque) instead of the
 * legacy Stack class for better performance and adherence to modern Java collections best practices.
 * * The Deque methods are mapped to stack operations as follows:
 * - push() -> addFirst()
 * - pop()  -> removeFirst()
 * - peek() -> peekFirst()
 *
 * Time Complexity: O(2^n), where n is the number of discs.
 * Space Complexity: O(n) due to the recursive call stack depth.
 */
public class Hanoi {

    /**
     * Initiates the recursive process to solve the Tower of Hanoi puzzle, moving all discs
     * from the source Deque to the target Deque.
     *
     * @param source The starting peg (Deque) containing the discs to be moved (top disc at the front/head).
     * @param target The destination peg (Deque) where the discs should end up.
     * @throws IllegalArgumentException if the source Deque is empty.
     */
    public void solve(Deque<Integer> source, Deque<Integer> target) {
        // Robustness Check: Ensure the source stack is not empty.
        if (source.isEmpty()) {
            throw new IllegalArgumentException("The source stack must contain at least 1 disc to be solved.");
        }

        // The auxiliary (temporary) Deque is initialized here. ArrayDeque is the standard choice
        // for a high-performance, non-blocking stack/queue implementation.
        Deque<Integer> temp = new ArrayDeque<>();

        // Start the recursive movement process.
        int numberOfDiscs = source.size();
        move(source, target, temp, numberOfDiscs);
    }

    /**
     * The core recursive logic for the Tower of Hanoi.
     * The task is to move 'n' discs from the 'source' peg to the 'target' peg using the 'temp' peg.
     *
     * @param source The Deque to move discs *from*.
     * @param target The Deque to move discs *to*.
     * @param temp The auxiliary Deque used for intermediate moves.
     * @param numberOfDiscs The number of discs to move from the top of the source Deque.
     */
    private void move(Deque<Integer> source, Deque<Integer> target, Deque<Integer> temp, int numberOfDiscs) {
        // Base Case 1: If no discs need to be moved, just return.
        if (numberOfDiscs == 0) {
            return;
        }

        // Base Case 2: Move the single largest disc directly.
        if (numberOfDiscs == 1) {
            // Stack operation POP is replaced by DEQUE's removeFirst()
            // Stack operation PUSH is replaced by DEQUE's addFirst()
            target.addFirst(source.removeFirst());
        } else {
            // 1. Move the top (n-1) discs from Source to Temp, using Target as auxiliary.
            move(source, temp, target, numberOfDiscs - 1);

            // 2. Move the nth (largest) disc from Source to Target. (n=1 step)
            target.addFirst(source.removeFirst());

            // 3. Move the (n-1) discs from Temp to Target, using Source as auxiliary.
            move(temp, target, source, numberOfDiscs - 1);
        }
    }
}