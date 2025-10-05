package org.kamronbek.algo.number;

import java.util.HashMap;
import java.util.Map;

/**
 * Finds the last (units) digit of a number raised to a power (base^exponent).
 * This utilizes the mathematical property that the last digits of powers repeat
 * in cycles (periodicity). The maximum cycle length is 4.
 * * Time Complexity: O(1) - Constant time complexity.
 */
public final class LastDigitFinder {

    // Map storing the cycle length (periodicity) of the last digit for each possible base last digit (0-9).
    // E.g., for base ending in 3, the last digits are 3, 9, 7, 1 (cycle length 4).
    private static final Map<Integer, Integer> EXPONENT_CYCLES = new HashMap<>();

    static {
        // Cycle Lengths (Periodicity) of last digits for base b:
        // b=0: 0, 0, 0, ... (Cycle 1, but special handling needed)
        EXPONENT_CYCLES.put(0, 1);
        // b=1, 5, 6: Always end in 1, 5, 6 respectively (Cycle 1)
        EXPONENT_CYCLES.put(1, 1);
        EXPONENT_CYCLES.put(5, 1);
        EXPONENT_CYCLES.put(6, 1);
        // b=4, 9: Cycle length 2 (e.g., 4, 6, 4, 6...)
        EXPONENT_CYCLES.put(4, 2);
        EXPONENT_CYCLES.put(9, 2);
        // b=2, 3, 7, 8: Cycle length 4 (e.g., 2, 4, 8, 6, 2, 4...)
        EXPONENT_CYCLES.put(2, 4);
        EXPONENT_CYCLES.put(3, 4);
        EXPONENT_CYCLES.put(7, 4);
        EXPONENT_CYCLES.put(8, 4);
    }

    /**
     * Calculates the last digit of base^exponent.
     * * @param base The base number (a).
     * @param exponent The power (b).
     * @return The last digit of a^b.
     */
    public static int findTheLastDigit(int base, int exponent) {
        // Handle the mathematical edge case: Any base to the power of 0 is 1.
        if (exponent == 0) {
            return 1;
        }

        // 1. Determine the last digit of the base.
        int baseLastDigit = base % 10;

        // Special case for base ending in 0 (e.g., 10^3 = 1000). The last digit is 0 unless exponent=0.
        if (baseLastDigit == 0) {
            return 0;
        }

        // 2. Determine the cycle length for this base's last digit.
        int cycleLength = EXPONENT_CYCLES.get(baseLastDigit);

        // 3. Find the effective exponent using the modulo operator with the cycle length.
        // The cycle length is the divisor.
        int effectiveExponent = exponent % cycleLength;

        // 4. Critical Correction/Optimization:
        // If (exponent % cycleLength) is 0, it means the exponent is a multiple of the cycle length.
        // In modular arithmetic for periodicity, the effective power is NOT 0, but the cycle length itself.
        // E.g., 2^4 = 16 (ends in 6). 4 % 4 = 0. We need to use exponent 4, not 0.
        if (effectiveExponent == 0) {
            effectiveExponent = cycleLength;
        }

        // 5. Calculate the result: (baseLastDigit ^ effectiveExponent) % 10.
        // Note: Using Math.pow and casting is slightly less efficient than a simple loop for small powers,
        // but it is concise and sufficient given effectiveExponent is at most 4.
        // We use Math.pow, but explicitly cast to long before taking the final modulo to
        // be robust against potential overflow, although highly unlikely for powers up to 4.
        long result = 1;
        for (int i = 0; i < effectiveExponent; i++) {
            result *= baseLastDigit;
        }

        return (int) (result % 10);
    }
}