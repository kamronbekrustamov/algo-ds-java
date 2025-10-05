package org.kamronbek.algo.number;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for LastDigitFinder, verifying the last digit calculation
 * for base^exponent based on cyclicity.
 */
public class LastDigitFinderTest {

    // ----------------------------------------------------------------------
    // Test Cases for Cycle Length 4 (e.g., bases ending in 2, 3, 7, 8)
    // ----------------------------------------------------------------------

    /**
     * Testing base ending in 2 (Cycle: 2, 4, 8, 6)
     * 2^1=2, 2^2=4, 2^3=8, 2^4=16(6), 2^5=32(2)
     */
    @Test
    void testBaseEndingInTwo() {
        // Exponent 1 (4k + 1) -> Last digit 2
        assertEquals(2, LastDigitFinder.findTheLastDigit(12, 1));
        assertEquals(2, LastDigitFinder.findTheLastDigit(12, 5));

        // Exponent 2 (4k + 2) -> Last digit 4
        assertEquals(4, LastDigitFinder.findTheLastDigit(22, 2));
        assertEquals(4, LastDigitFinder.findTheLastDigit(32, 6));

        // Exponent 4 (4k, period end) -> Last digit 6
        assertEquals(6, LastDigitFinder.findTheLastDigit(42, 4));
        assertEquals(6, LastDigitFinder.findTheLastDigit(52, 8));
    }

    /**
     * Testing base ending in 7 (Cycle: 7, 9, 3, 1)
     */
    @Test
    void testBaseEndingInSeven() {
        // Exponent 3 (4k + 3) -> Last digit 3
        assertEquals(3, LastDigitFinder.findTheLastDigit(17, 3));
        assertEquals(3, LastDigitFinder.findTheLastDigit(27, 7));
    }

    // ----------------------------------------------------------------------
    // Test Cases for Cycle Length 2 (e.g., bases ending in 4, 9)
    // ----------------------------------------------------------------------

    /**
     * Testing base ending in 4 (Cycle: 4, 6)
     */
    @Test
    void testBaseEndingInFour() {
        // Exponent Odd (2k + 1) -> Last digit 4
        assertEquals(4, LastDigitFinder.findTheLastDigit(14, 1));
        assertEquals(4, LastDigitFinder.findTheLastDigit(14, 3));

        // Exponent Even (2k, period end) -> Last digit 6
        assertEquals(6, LastDigitFinder.findTheLastDigit(14, 2));
        assertEquals(6, LastDigitFinder.findTheLastDigit(14, 4));
    }

    // ----------------------------------------------------------------------
    // Test Cases for Cycle Length 1 (e.g., bases ending in 1, 5, 6)
    // ----------------------------------------------------------------------

    /**
     * Testing base ending in 5 (Always ends in 5)
     */
    @Test
    void testBaseEndingInFive() {
        assertEquals(5, LastDigitFinder.findTheLastDigit(15, 100));
        assertEquals(5, LastDigitFinder.findTheLastDigit(5, 1));
    }

    /**
     * Testing base ending in 6 (Always ends in 6)
     */
    @Test
    void testBaseEndingInSix() {
        assertEquals(6, LastDigitFinder.findTheLastDigit(26, 999));
        assertEquals(6, LastDigitFinder.findTheLastDigit(6, 1));
    }

    // ----------------------------------------------------------------------
    // Edge Cases (Exponent 0, Base 0, Large Numbers)
    // ----------------------------------------------------------------------

    /**
     * Exponent 0 should always return 1 (Math rule: a^0 = 1).
     */
    @Test
    void testExponentZero() {
        assertEquals(1, LastDigitFinder.findTheLastDigit(100, 0));
        assertEquals(1, LastDigitFinder.findTheLastDigit(999, 0));
        assertEquals(1, LastDigitFinder.findTheLastDigit(7, 0));
    }

    /**
     * Base ending in 0 (e.g., 10^b) should return 0 for exponent > 0.
     */
    @Test
    void testBaseEndingInZero() {
        assertEquals(0, LastDigitFinder.findTheLastDigit(10, 1));
        assertEquals(0, LastDigitFinder.findTheLastDigit(10, 1000));
        assertEquals(0, LastDigitFinder.findTheLastDigit(50, 50));
    }

    /**
     * Testing a very large exponent to ensure the modulo operation works correctly.
     */
    @Test
    void testLargeExponent() {
        // 23^123456789. Last digit of 3 (Cycle: 3, 9, 7, 1). Cycle length 4.
        // 123456789 % 4 = 1. Result should be 3^1 = 3.
        assertEquals(3, LastDigitFinder.findTheLastDigit(23, 123456789));

        // 18^123456. Last digit of 8 (Cycle: 8, 4, 2, 6). Cycle length 4.
        // 123456 % 4 = 0. Result should be 8^4 = 4096 (6).
        assertEquals(6, LastDigitFinder.findTheLastDigit(18, 123456));
    }
}