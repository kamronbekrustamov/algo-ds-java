package org.kamronbek.algo.multiplyandsquare;

public class MultiplyAndSquare {
    /**
     * Calculates (base ^ exponent) % mod using the method of exponentiation by squaring.
     * This implementation is robust against integer overflow and handles various edge cases.
     *
     * @param base     the base of the exponentiation
     * @param exponent the exponent of the exponentiation (must be non-negative)
     * @param mod      the modulus (must be greater than 1)
     * @return the value of (base ^ exponent) % mod
     */
    public long calculateRemainder(long base, long exponent, long mod) {
        if (exponent < 0) {
            throw new IllegalArgumentException("Exponent cannot be negative.");
        }
        if (mod <= 1) {
            if (mod == 1) {
                return 0; // The remainder of any division by 1 is 0.
            }
            throw new IllegalArgumentException("Modulus must be greater than 1.");
        }

        long result = 1;
        // Reduce base with mod at the start and handle negative base.
        // The result of % in Java can be negative, so we adjust it to be in [0, mod-1].
        long b = base % mod;
        if (b < 0) {
            b += mod;
        }

        // e.g., for 3^13%7 (13 is "1101"): R=1 -> (1²*3)=3 -> (3²*3)=6 -> (6²)=1 -> (1²*3)=3. All ops are % 7.
        for (char bit : Long.toBinaryString(exponent).toCharArray()) {
            // Square step: (result * result) % mod
            result = (result * result) % mod;
            // Multiply step (if the bit is '1')
            if (bit == '1') {
                // (result * base) % mod
                result = (result * b) % mod;
            }
        }
        return result;
    }

}
