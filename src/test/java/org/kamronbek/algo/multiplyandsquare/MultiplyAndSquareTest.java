package org.kamronbek.algo.multiplyandsquare;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class MultiplyAndSquareTest {

    private final MultiplyAndSquare multiplyAndSquare = new MultiplyAndSquare();

    @Test
    void calculateRemainderTest() {
        long base = 219620;
        long exponent = 420001;
        long divisor = 719651;
        long expectedRemainder = 503; // base^exponent % divisor
        long actualRemainder = multiplyAndSquare.calculateRemainder(base, exponent, divisor);
        assertEquals(expectedRemainder, actualRemainder);
    }
}
